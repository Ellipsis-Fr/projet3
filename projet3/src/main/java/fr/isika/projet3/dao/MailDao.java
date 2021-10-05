package fr.isika.projet3.dao;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Mail;

@Repository
public class MailDao extends AbstractJpaDao<Mail> implements IMailDao {

	public MailDao() {
		setClazz(Mail.class);
	}
}
