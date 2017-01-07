package org.bridgelabz.docsigner.service.impl;

<<<<<<< HEAD
=======
import java.util.Date;
import java.util.UUID;

>>>>>>> 5441ace8e854ce57ebf0dbd119af7651224fc616
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

<<<<<<< HEAD
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
=======
	public void addToken(Token token) {
		Session session = sessionFactory.getCurrentSession();
		// Transaction tr = ses.beginTransaction();
		try {
			token.setCreatedOn(new Date());
			session.save(token);
			// tr.commit();
>>>>>>> 5441ace8e854ce57ebf0dbd119af7651224fc616
		} catch (Exception e) {
			// tr.rollback();
			e.printStackTrace();
		}
<<<<<<< HEAD
		return token;

	}

	@Override
	public Token getToken(String accToken) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria cr = session.createCriteria(Token.class);
			Token token = (Token) cr.add(Restrictions.conjunction().add(Restrictions.eq("accessToken", accToken))
					).uniqueResult();
=======
	}

	@SuppressWarnings("deprecation")
	public Token authToken(String accessToken, String refreshToken) {
		Session session = sessionFactory.getCurrentSession();
		// select * from users where userName = '' and password = '';
		try {
			Criteria cr = session.createCriteria(Token.class);
			Token token = (Token) cr.add(Restrictions.conjunction().add(Restrictions.eq("accessToken", accessToken))
					.add(Restrictions.eq("refreshToken", refreshToken))).uniqueResult();
>>>>>>> 5441ace8e854ce57ebf0dbd119af7651224fc616
			return token;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

<<<<<<< HEAD
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
=======
	public Token generateToken(User user, Token token) {

		String accessToken = UUID.randomUUID().toString().replaceAll("-", "");
		String refreshToken = UUID.randomUUID().toString().replaceAll("-", "");
		token.setAccessToken(accessToken);
		token.setRefreshToken(refreshToken);
		token.setUserId(user.getId());
		return token;
	}

>>>>>>> 5441ace8e854ce57ebf0dbd119af7651224fc616
}
