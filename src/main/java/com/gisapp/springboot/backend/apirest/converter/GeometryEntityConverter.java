package com.gisapp.springboot.backend.apirest.converter;

import java.util.ArrayList;
import java.util.List;

import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class GeometryEntityConverter {
	
	public static GeometryEntity convertToGeometryEntity(NonGeometryEntity nonGeometry) throws ParseException {
		
		GeometryEntity geometryEntity = new GeometryEntity();
		
		geometryEntity.setUserId(nonGeometry.getUserId());
		geometryEntity.setPointName(nonGeometry.getPointName());
		geometryEntity.setGeom(wktToGeometry(nonGeometry.getGeom()));
		return geometryEntity;
		
	}
	
	public static List<NonGeometryEntity> convertToNonGeometryEntity(List<GeometryEntity> geometryEntity)
			throws ParseException {

		List<NonGeometryEntity> nonGeometryEntityList = new ArrayList<>();

		for (GeometryEntity geometry : geometryEntity) {

			NonGeometryEntity nonGeometryEntity = new NonGeometryEntity();

			nonGeometryEntity.setUserId(geometry.getUserId());
			nonGeometryEntity.setPointName(geometry.getPointName());
			nonGeometryEntity.setGeom(geometry.getGeom().toString());
			
			nonGeometryEntityList.add(nonGeometryEntity);
		}

		return nonGeometryEntityList;

	}
	
	public static Geometry wktToGeometry(String wellKnownText) 
			  throws ParseException {
			  
			    return new WKTReader().read(wellKnownText);
			}

}
