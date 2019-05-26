package com.gisapp.springboot.backend.apirest.models.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.gisapp.springboot.backend.apirest.models.entity.LoginEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;

@Repository
public class UserDAO implements IUserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Object login(UserEntity user) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT user FROM UserEntity user " + "WHERE user_name in (:userName) ");

		Query q = em.createQuery(sb.toString());

		q.setParameter("userName", user.getUserName());

		UserEntity userFound = (UserEntity) q.getSingleResult();

		return userFound;
	}
	
	

}
