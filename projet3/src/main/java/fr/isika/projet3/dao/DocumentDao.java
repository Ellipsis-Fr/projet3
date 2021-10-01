package fr.isika.projet3.dao;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Document;

@Repository
public class DocumentDao extends AbstractJpaDao<Document> implements IDocumentDao {
	
	public DocumentDao() {
		setClazz(Document.class);
	}
}
