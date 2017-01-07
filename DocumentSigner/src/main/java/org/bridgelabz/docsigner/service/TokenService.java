package org.bridgelabz.docsigner.service;

import org.bridgelabz.docsigner.model.Token;
import org.bridgelabz.docsigner.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenService {

	public Token addToken(User user, Token token);

	public Token authToken(String accessToken, String refreshToken);
	
	public Token getToken(String accessToken);

	Token getTokenByRefToken(String refToken);

}
