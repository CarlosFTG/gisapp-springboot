package com.gisapp.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

@Entity
@Table(name = "polygons")
public class PolygonEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "point_name")
	private String pointName;

	@Column(name = "coordinates")
	private Polygon coordinates;

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

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public Polygon getGeom() {
		return coordinates;
	}

	public void setGeom(Polygon polygon) {
		this.coordinates = polygon;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	private static final long serialVersionUID = 1L;

}