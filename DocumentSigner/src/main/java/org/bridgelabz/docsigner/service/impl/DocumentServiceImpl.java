package org.bridgelabz.docsigner.service.impl;

import org.bridgelabz.docsigner.model.Document;
import org.bridgelabz.docsigner.service.DocumentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

}

/*
 * Session session = sessionFactory.getCurrentSession();
 * 
 * System.out.println("Name:" + document.getName()); System.out.println("Desc:"
 * + document.getDescription()); System.out.println("File:" + file.getName());
 * System.out.println("ContentType:" + file.getContentType());
 * 
 * try { byte[] fileBytes = file.getBytes(); Blob blob =
 * Hibernate.getLobCreator(session).createBlob(fileBytes);
 * 
 * document.setFilename(file.getOriginalFilename()); document.setContent(blob);
 * document.setContentType(file.getContentType()); session.save(document); }
 * catch (IOException e) { e.printStackTrace(); }
 * 
 * try { documentService.addDocument(document); } catch (Exception e) {
 * e.printStackTrace(); }
 * 
 * return "addDocuments";
 */