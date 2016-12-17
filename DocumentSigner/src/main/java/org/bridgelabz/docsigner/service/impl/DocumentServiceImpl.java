package org.bridgelabz.docsigner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bridgelabz.docsigner.model.Document;
import org.bridgelabz.docsigner.service.DocumentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private SessionFactory sessionFactory;

	public void addDocument(Document document) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Document> listDocuments(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Document> query = session.createQuery("from Document");
		query.setParameter("userId", userId);
		List<Document> documentList = query.getResultList();

		List<Document> myDocuments = new ArrayList<Document>();
		for (Document document : documentList) {
			if (document.getUserId() == userId) {
				myDocuments.add(document);
			}
		}
		return myDocuments;
	}

	public List<Document> listDocumentDetails(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Document> query = session.createQuery("from Document where id=:id");
		query.setParameter("id", id);
		List<Document> documentDetails = query.getResultList();
		return documentDetails;
	}

}