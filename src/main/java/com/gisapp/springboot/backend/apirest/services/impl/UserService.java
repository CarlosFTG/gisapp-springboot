package com.gisapp.springboot.backend.apirest.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gisapp.springboot.backend.apirest.converter.UserConverter;
import com.gisapp.springboot.backend.apirest.dao.IUserDAO;
import com.gisapp.springboot.backend.apirest.dao.IUserGenericDAO;
import com.gisapp.springboot.backend.apirest.models.bean.UserBean;
import com.gisapp.springboot.backend.apirest.models.entity.LoginEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;
import com.gisapp.springboot.backend.apirest.services.IUserService;
import com.vividsolutions.jts.io.ParseException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IUserGenericDAO genericDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserBean login(LoginEntity userLogin) throws ParseException, JSONException {

		final Logger logger = Logger.getLogger(PointServiceImpl.class);

		
		UserEntity user = new UserEntity();

		UserBean loginUser = new UserBean();

		user.setEmail(userLogin.getEmail());
		user.setUserPassword(passwordEncoder.encode(userLogin.getPassword()));

		UserEntity userFound = (UserEntity) userDAO.login(user);

		if (passwordEncoder.matches(userLogin.getPassword(), userFound.getUserPassword())) {

			UserBean userToConvert = UserConverter.convertToUserBean(userFound);
			loginUser.setUserName(userFound.getUserName());
			loginUser.setEmail(userFound.getEmail());
			loginUser.setToken(getJWTToken(userFound.getEmail()));
			userToConvert.setToken(getJWTToken(userFound.getEmail()));
			userToConvert.setUserPassword(userFound.getUserPassword());
			return userToConvert;

		} else {
			UserBean userToConvert = new UserBean();
			return userToConvert;

		}

	}

	@Override
	@Transactional
	public void save(UserEntity user) {

		if (user.getUserPassword() != null) {
			user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		}

		// uses login method to check if the user already exists
		UserEntity userFound = (UserEntity) userDAO.login(user);

		// if the email is already registered, the user is not saved
		if (userFound.getEmail() == null) {
			genericDAO.save(user);

		}
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return token;
	}

}
