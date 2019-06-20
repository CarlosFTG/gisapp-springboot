package com.gisapp.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.LineString;

@Entity
@Table(name = "lines")
public class LineEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "line_name")
	private String lineName;

	@Column(name = "coordinates")
	private LineString coordinates;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String pointName) {
		this.lineName = pointName;
	}

	public LineString getGeom() {
		return coordinates;
	}

	public void setGeom(LineString geom) {
		this.coordinates = geom;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	private static final long serialVersionUID = 1L;

}