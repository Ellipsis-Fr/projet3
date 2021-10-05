package fr.isika.projet3.dao;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Messaging;

@Repository
public class MessagingDao extends AbstractJpaDao<Messaging> implements IMessagingDao {
	
	public MessagingDao() {
		setClazz(Messaging.class);
	}
}
