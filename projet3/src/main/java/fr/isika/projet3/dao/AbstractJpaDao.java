package fr.isika.projet3.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractJpaDao<T> {

    private Class<T> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(final long id) {
        return entityManager.find(clazz, id);
    }
    
    @SuppressWarnings("unchecked")
	public T findOneByParameters(String queryString, Object... parameters) {
    	Query query = entityManager.createQuery(queryString);

    	for (int i = 0; i < parameters.length; i++) {
    		query.setParameter(i, parameters[i]);
    	}
    	
    	try {
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
            return null;
        } catch  (NonUniqueResultException e) {
        	return null;
        }	
	}

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> findAllByParameters(HashMap<String, String> parameters) {
    	List<String> conditions = new ArrayList<String>();
    	for (String key: parameters.keySet()) {
    		conditions.add(key + " = " + parameters.get(key));
    	}
        return entityManager.createQuery("from " + clazz.getName() + " where " + String.join(" AND ", conditions)).getResultList();
    }

    public void create(final T entity) {
        entityManager.persist(entity);
    }

    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

}
