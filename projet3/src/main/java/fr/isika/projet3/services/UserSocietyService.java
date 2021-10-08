package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.projet3.dao.IUserSocietyDao;
import fr.isika.projet3.entities.User;
import fr.isika.projet3.entities.UserSociety;

@Service
@Transactional
public class UserSocietyService implements IUserSocietyService {
	private static final String FIELD_FIRSTNAME = "firstname";
	private static final String FIELD_LASTNAME = "lastname";
	private static final String FIELD_TELEPHONE = "telephone";
	private static final String FIELD_ADDRESS = "address";
	private static final String FIELD_EMAIL = "email";
	private static final String CHECK_CONTACT = "contact";
	private static final String FIELD_COMPANY_NAME = "companyName";
	private static final String FIELD_SIRET = "siret";
	
	@Autowired
	private IUserSocietyDao dao;

	@Override
	public UserSociety findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public List<UserSociety> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UserSociety init(HttpServletRequest req) {
		UserSociety userSociety = new UserSociety();
		
		userSociety.setFirstname(req.getParameter(FIELD_FIRSTNAME));
		userSociety.setLastname(req.getParameter(FIELD_LASTNAME));
		userSociety.setTelephone(req.getParameter(FIELD_TELEPHONE));
		userSociety.setAddress(req.getParameter(FIELD_ADDRESS));
		userSociety.setEmail(req.getParameter(FIELD_EMAIL));
		userSociety.setRecontactable(req.getParameter(CHECK_CONTACT) != null);
		userSociety.setCompanyName(req.getParameter(FIELD_COMPANY_NAME));
		userSociety.setSiret(req.getParameter(FIELD_SIRET));
		
		return userSociety;
	}

	@Override
	public void create(UserSociety entity) {
		dao.create(entity);
	}
	
	@Override
	public UserSociety updateByFields(UserSociety userUpdated, UserSociety userInDataBase) {
		
		userInDataBase.setFirstname(userUpdated.getFirstname());
		userInDataBase.setLastname(userUpdated.getLastname());
		userInDataBase.setTelephone(userUpdated.getTelephone());
		userInDataBase.setAddress(userUpdated.getAddress());
		userInDataBase.setEmail(userUpdated.getEmail());
		userInDataBase.setRecontactable(userUpdated.isRecontactable());
		userInDataBase.setCompanyName(userUpdated.getCompanyName());
		
		return userInDataBase;
	}

	@Override
	public UserSociety update(UserSociety entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(UserSociety entity) {
		dao.delete(entity);
	}

	@Override
	public void deleteById(long entityId) {
		dao.deleteById(entityId);
	}

	
	@Override
	public UserSociety findDouble(String siret) {
		String query = "FROM Association a WHERE a.rna=?";
		return dao.findOneByParameters(query, siret);
	}

	
}
