package fr.isika.projet3.services;

import java.util.List;

import fr.isika.projet3.entities.Document;

public interface IDocumentService {
	Document findOne(long id);

    List<Document> findAll();

    void create(Document entity);

    Document update(Document entity);

    void delete(Document entity);

    void deleteById(long entityId);
}
