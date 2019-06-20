package com.gisapp.springboot.backend.apirest.models.bean;

import java.util.List;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userName;
	
	private String surname;
	
	private String email;
	
	private String userPassword;
	
	private boolean appAdmin;
	
	private String token;
	
	private List<Map<String, String>> pointsList;
	
	private List<Map<String, String>> polygonsList;

	private List<Map<String, String>> linesList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isAppAdmin() {
		return appAdmin;
	}

	public void setAppAdmin(boolean appAdmin) {
		this.appAdmin = appAdmin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Map<String, String>> getPointsList() {
		return pointsList;
	}

	public void setPointsList(List<Map<String, String>> geometriesList) {
		this.pointsList = geometriesList;
	}

	public List<Map<String, String>> getPolygonsList() {
		return polygonsList;
	}

	public void setPolygonsList(List<Map<String, String>> polygonsList) {
		this.polygonsList = polygonsList;
	}

	public List<Map<String, String>> getLinesList() {
		return linesList;
	}

	public void setLinesList(List<Map<String, String>> linesList) {
		this.linesList = linesList;
	}
	
	
}
