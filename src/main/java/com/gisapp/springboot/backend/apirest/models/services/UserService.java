package com.gisapp.springboot.backend.apirest.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gisapp.springboot.backend.apirest.models.dao.IGeometriesGenericDao;
import com.gisapp.springboot.backend.apirest.models.dao.IUserDAO;
import com.gisapp.springboot.backend.apirest.models.dao.IUserGenericDAO;
import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.LoginEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IUserGenericDAO genericDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String login(LoginEntity userLogin) {
		
		UserEntity user = new UserEntity();
		
		String passwordMatch = " ";
		
		user.setUserName(userLogin.getUserName());
		user.setUserPassword(passwordEncoder.encode(userLogin.getPassword()));
		
		UserEntity userFound=(UserEntity) userDAO.login(user);
		
		if(passwordEncoder.matches(userLogin.getPassword(), userFound.getUserPassword())) {
			
			passwordMatch ="login success ";
		}else {
			passwordMatch="login failed";
		}
		
		return passwordMatch;
	}
	
	@Override
	@Transactional
	public void save(UserEntity user) {
		
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

		genericDAO.save(user);
	}

}
