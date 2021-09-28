package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.entities.Activity;
import fr.isika.projet3.entities.Partner;

public interface IPartnerService {
	
	Partner findOne(long id);

    List<Partner> findAll();
    
    Partner init(HttpServletRequest req);
    
    void create(Partner partner);

    Partner update(Partner partner);

    void delete(Partner partner);

    void deleteById(long entityId);
    
	Partner partnerLogIn(String email, String password);
	
}


