package org.bridgelabz.docsigner.service.impl;

import java.util.Date;
import java.util.UUID;

import org.bridgelabz.docsigner.model.Token;
import org.bridgelabz.docsigner.model.User;
import org.bridgelabz.docsigner.service.TokenService;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TokenServiceImpl implements TokenService {

	@Autowired
	private SessionFactory sessionFactory;

	public void addToken(Token token) {
		Session session = sessionFactory.getCurrentSession();
		// Transaction tr = ses.beginTransaction();
		try {
			token.setCreatedOn(new Date());
			session.save(token);
			// tr.commit();
		} catch (Exception e) {
			// tr.rollback();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public Token authToken(String accessToken, String refreshToken) {
		Session session = sessionFactory.getCurrentSession();
		// select * from users where userName = '' and password = '';
		try {
			Criteria cr = session.createCriteria(Token.class);
			Token token = (Token) cr.add(Restrictions.conjunction().add(Restrictions.eq("accessToken", accessToken))
					.add(Restrictions.eq("refreshToken", refreshToken))).uniqueResult();
			return token;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Token generateToken(User user, Token token) {

		String accessToken = UUID.randomUUID().toString().replaceAll("-", "");
		String refreshToken = UUID.randomUUID().toString().replaceAll("-", "");
		token.setAccessToken(accessToken);
		token.setRefreshToken(refreshToken);
		token.setUserId(user.getId());
		return token;
	}

}
