package org.bridgelabz.docsigner.service;

import org.bridgelabz.docsigner.model.Document;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentService {

	public void addDocument(Document document);

}
