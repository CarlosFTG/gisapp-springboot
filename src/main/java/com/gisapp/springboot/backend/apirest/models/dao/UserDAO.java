package com.gisapp.springboot.backend.apirest.models.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.gisapp.springboot.backend.apirest.models.entity.LoginEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;

@Repository
public class UserDAO implements IUserDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * {{inherit}}
	 */
	@Override
	public Object login(UserEntity user) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT user FROM UserEntity user " + "WHERE email in (:email) ");

		Query q = em.createQuery(sb.toString());

		q.setParameter("email", user.getEmail());
		
		UserEntity userFound = new UserEntity();

		try {
			 userFound = (UserEntity) q.getSingleResult();
		}catch(NoResultException nre){
		}
		return userFound;
		
	}

	
	
	

}
