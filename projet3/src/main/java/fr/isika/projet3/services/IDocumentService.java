package fr.isika.projet3.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.entities.Document;

public interface IDocumentService {
	Document findOne(long id);

    List<Document> findAll();

    void create(Document entity);

    Document update(Document entity);

    void delete(Document entity);

    void deleteById(long entityId);
    
    String createNewFolder(String associationFolder, String folder);
    
    String saveFile(MultipartFile file, String folder);

	void deleteFile(String pathHeader);
}
