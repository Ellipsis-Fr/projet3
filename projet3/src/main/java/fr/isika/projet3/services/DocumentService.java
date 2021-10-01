package fr.isika.projet3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.projet3.dao.IDocumentDao;
import fr.isika.projet3.entities.Document;

@Service
@Transactional
public class DocumentService implements IDocumentService {

	@Autowired
	private IDocumentDao dao;
	
	@Override
	public Document findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Document entity) {
		dao.create(entity);
		
	}

	@Override
	public Document update(Document entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Document entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}

}
