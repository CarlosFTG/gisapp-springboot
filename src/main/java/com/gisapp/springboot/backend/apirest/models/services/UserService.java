package com.gisapp.springboot.backend.apirest.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gisapp.springboot.backend.apirest.models.dao.IUserDAO;
import com.gisapp.springboot.backend.apirest.models.dao.IUserGenericDAO;
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
	public UserEntity login(LoginEntity userLogin) {

		UserEntity user = new UserEntity();

		UserEntity userChecked = new UserEntity();

		user.setEmail(userLogin.getEmail());
		user.setUserPassword(passwordEncoder.encode(userLogin.getPassword()));

		UserEntity userFound = (UserEntity) userDAO.login(user);

		if (passwordEncoder.matches(userLogin.getPassword(), userFound.getUserPassword())) {

			userChecked.setUserName(userFound.getUserName());
			userChecked.setEmail(userFound.getEmail());

		} else {
		}
		return userChecked;
	}

	@Override
	@Transactional
	public void save(UserEntity user) {
		
		
		if(user.getUserPassword()!=null) {
			user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		}

		//uses login method to check if the user already exists
		UserEntity userFound=(UserEntity) userDAO.login(user);
		
		//if the email is already registered, the user is not saved
		if(userFound.getEmail()==null) {
			genericDAO.save(user);

		}
	}

}
