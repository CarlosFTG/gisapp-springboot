package com.gisapp.springboot.backend.apirest.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Polygon;

/**
 * Class to save temporal polygon to be used in findPoints into Polygon
 * @author cfernandeztejadagarc
 *
 */

@Entity
@Table(name = "temp_polygon")
public class TempPolygonEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "coordinates")
	private Polygon coordinates;

	public Polygon getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Polygon coordinates) {
		this.coordinates = coordinates;
	}
}
