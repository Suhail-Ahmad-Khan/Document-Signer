package org.bridgelabz.docsigner.service;

import org.bridgelabz.docsigner.model.Token;
import org.bridgelabz.docsigner.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenService {

<<<<<<< HEAD
	public Token addToken(User user, Token token);

	public Token authToken(String accessToken, String refreshToken);
	
	public Token getToken(String accessToken);

	Token getTokenByRefToken(String refToken);
=======
	public void addToken(Token token);
	
	public Token generateToken(User user, Token token);

	public Token authToken(String accessToken, String refreshToken);
>>>>>>> 5441ace8e854ce57ebf0dbd119af7651224fc616

}
