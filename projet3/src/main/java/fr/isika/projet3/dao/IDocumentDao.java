package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Document;

public interface IDocumentDao {
	Document findOne(long id);
	
	Document findOneByParameters(String queryString, Object...parameters);

    List<Document> findAll();

    void create(Document entity);

    Document update(Document entity);

    void delete(Document entity);

    void deleteById(long entityId);
}
