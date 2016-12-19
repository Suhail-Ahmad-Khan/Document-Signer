package org.bridgelabz.docsigner.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bridgelabz.docsigner.json.ErrorResponse;
import org.bridgelabz.docsigner.json.Response;
import org.bridgelabz.docsigner.json.SuccessResponse;
import org.bridgelabz.docsigner.model.User;
import org.bridgelabz.docsigner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping("/loginPage")
	public String init(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			return "success";
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Response login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request) {
		User user = userService.authUser(email, password);
		if (user == null) {
			ErrorResponse er = new ErrorResponse();
			er.setStatus(-1);
			er.setDisplayMessage("Invalid credentil");
			er.setErrorMessage("user not found");
			return er;
			// return "login";
		} else {
			HttpSession session = request.getSession();
			session.invalidate(); // invalidate existing session
			session = request.getSession();
			session.setAttribute("user", user);
			SuccessResponse er = new SuccessResponse();
			er.setStatus(1);
			er.setMessage("successfully logged in");
			session.setMaxInactiveInterval(5);
			return er;
			// return "success";
		}
	}

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession();
		return "index";

	}

}
