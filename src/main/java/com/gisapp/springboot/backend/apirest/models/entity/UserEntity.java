package com.gisapp.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8583242131533000424L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "user_password")
	private String userPassword;
	
	@Column(name = "app_admin")
	private boolean appAdmin;
	
	private String token;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<PointsEntity> pointsList;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<PolygonEntity> polygonsList;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<LineEntity> linesList;

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

	public List<PointsEntity> getPointsList() {
		return pointsList;
	}

	public void setPointsList(List<PointsEntity> geometriesList) {
		this.pointsList = geometriesList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public List<PolygonEntity> getPolygonsList() {
		return polygonsList;
	}

	public void setPolygonsList(List<PolygonEntity> polygonsList) {
		this.polygonsList = polygonsList;
	}

	public List<LineEntity> getLinesList() {
		return linesList;
	}

	public void setLinesList(List<LineEntity> linesList) {
		this.linesList = linesList;
	}
	
}
