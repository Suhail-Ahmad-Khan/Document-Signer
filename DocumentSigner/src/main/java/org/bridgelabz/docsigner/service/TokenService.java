package org.bridgelabz.docsigner.service;

import org.bridgelabz.docsigner.model.Token;
import org.bridgelabz.docsigner.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenService {

	public void addToken(Token token);
	
	public Token generateToken(User user, Token token);

	public Token authToken(String accessToken, String refreshToken);

}
