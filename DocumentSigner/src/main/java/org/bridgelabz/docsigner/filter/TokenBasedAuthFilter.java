package org.bridgelabz.docsigner.filter;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bridgelabz.docsigner.json.ErrorResponse;
import org.bridgelabz.docsigner.model.Token;
import org.bridgelabz.docsigner.service.TokenService;
import org.bridgelabz.docsigner.service.impl.TokenServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TokenBasedAuthFilter implements Filter   {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		Date currentDate = new Date();
		
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
		
		TokenService tokenService = (TokenService) applicationContext.getBean("tokenService");
		
		/*Calendar now = Calendar.getInstance();*/
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		/*
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("loginPage");
			return;
		}
		*/
		
		String accessToken = request.getHeader("access_token");// request.getHeader("Authorization");
		String clientType = request.getHeader("client_type");

		if (accessToken == null || accessToken.trim().isEmpty()) {
			Cookie[] cks = request.getCookies();
			if (cks != null) {
				for (Cookie cookie : cks) {
					if (cookie.getName().equals("access_token")) {
						accessToken = cookie.getValue();
					}
				}
			}
		}

		if (accessToken == null || accessToken.trim().isEmpty()) {
			if( clientType == null || clientType.trim().isEmpty()) // browser
			{
				response.sendRedirect("loginPage");
				return;
			}
			ErrorResponse er = new ErrorResponse();
			er.setStatus(-1);
			er.setDisplayMessage("Invalid credential");
			er.setErrorMessage("user not found");

			response.setContentType("application/json");
			String jsonResp = "{\"status\":\"-1\",\"errorMessage\":\"Invalid access token\"}";
			response.getWriter().write(jsonResp);
			return;
		}

		// get token from request header or cookie
		// get token from db
		Token token = tokenService.getToken(accessToken);
		if( token == null )
		{
			if( clientType == null || clientType.trim().isEmpty()) // browser
			{
				response.sendRedirect("loginPage");
				return;
			}
			
			response.setContentType("application/json");
			String jsonResp = "{\"status\":\"-1\",\"errorMessage\":\"Invalid access token\"}";
			response.getWriter().write(jsonResp);
			return;
		}
		
		Date date = token.getCreatedOn(); 
		
		//in milliseconds
		long diff = currentDate.getTime() - date.getTime();

		long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(diff);
		
		if(diffInSeconds > 60*60) // 60min
		{
			if( clientType == null || clientType.trim().isEmpty()) // browser
			{
				response.sendRedirect("loginPage");
				return;
			}
			
			// generate json error response - access token is expired
			response.setContentType("application/json");
			String jsonResp = "{\"status\":\"-1\",\"errorMessage\":\"Access token is expired\"}";
			response.getWriter().write(jsonResp);
			return;
		}

		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
