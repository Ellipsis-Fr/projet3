package fr.isika.projet3.dao;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Activity;


@Repository
public class ActivityDao extends AbstractJpaDao<Activity> implements IActivityDao{


		public ActivityDao() {
			setClazz(Activity.class);
		}
}
