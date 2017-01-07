package org.bridgelabz.docsigner.service.impl;

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

	@SuppressWarnings("deprecation")
	public Token authToken(String accessToken, String refreshToken) {
		Session session = sessionFactory.getCurrentSession();
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

	public Token addToken(User user, Token token) {
		Session session = sessionFactory.getCurrentSession();

		try {
			session.save(token);
		} catch (Exception e) {
			// tr.rollback();
			e.printStackTrace();
		}
		return token;

	}

	@Override
	public Token getToken(String accToken) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria cr = session.createCriteria(Token.class);
			Token token = (Token) cr.add(Restrictions.conjunction().add(Restrictions.eq("accessToken", accToken))
					).uniqueResult();
			return token;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Token getTokenByRefToken(String refToken) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria cr = session.createCriteria(Token.class);
			Token token = (Token) cr.add(Restrictions.conjunction().add(Restrictions.eq("refreshToken", refToken))
					).uniqueResult();
			return token;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
