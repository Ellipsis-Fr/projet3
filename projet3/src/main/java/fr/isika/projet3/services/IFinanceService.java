package fr.isika.projet3.services;


import java.util.List;

import fr.isika.projet3.entities.Finance;

public interface IFinanceService {

    void create(Finance finance);
  
    List<Finance> getFinancesByPartner(String emailPartner);

}
