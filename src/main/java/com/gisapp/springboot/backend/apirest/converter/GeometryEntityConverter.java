package com.gisapp.springboot.backend.apirest.converter;

import java.util.ArrayList;
import java.util.List;

import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class GeometryEntityConverter {

	public List<GeometryEntity> convertToGeometryEntityList(List<NonGeometryEntity> nonGeometryList)
			throws ParseException {

		List<GeometryEntity> geometryEntityList = new ArrayList<>();

		GeometryEntity geometryEntity = new GeometryEntity();

		for (NonGeometryEntity nonGeometry : nonGeometryList) {

			geometryEntity = convertToGeometryEntity(nonGeometry);
			geometryEntityList.add(geometryEntity);
		}

		return geometryEntityList;
	}

	// public List<NonGeometryEntity>
	// convertToNonGeometryEntityList(List<GeometryEntity> geometryList){
	//
	// List<NonGeometryEntity> nonGeometryEntityList = new ArrayList<>();
	//
	// NonGeometryEntity nonGeometryEntity = new NonGeometryEntity();
	//
	// for(GeometryEntity geometry:geometryList) {
	//
	// nonGeometryEntity =convertToNonGeometryEntity(geometry);
	// }
	//
	// return null;
	// }

	public static GeometryEntity convertToGeometryEntity(NonGeometryEntity nonGeometry) throws ParseException {

		GeometryEntity geometryEntity = new GeometryEntity();

		UserEntity userEntity;

		geometryEntity.setUserId(Long.parseLong(nonGeometry.getUserId()));

		geometryEntity.setPointName(nonGeometry.getPointName());
		geometryEntity.setGeom(wktToGeometry(nonGeometry.getGeom()));

		geometryEntity.getGeom().setSRID(3857);

		return geometryEntity;

	}

	public static List<NonGeometryEntity> convertToNonGeometryEntity(List<GeometryEntity> geometryEntity)
			throws ParseException {

		List<NonGeometryEntity> nonGeometryEntityList = new ArrayList<>();

		for (GeometryEntity geometry : geometryEntity) {

			NonGeometryEntity nonGeometryEntity = new NonGeometryEntity();

			nonGeometryEntity.setUserId(geometry.getUserId().toString());
			nonGeometryEntity.setPointName(geometry.getPointName());
			nonGeometryEntity.setGeom(geometry.getGeom().toString());

			nonGeometryEntityList.add(nonGeometryEntity);
		}

		return nonGeometryEntityList;

	}

	public static Geometry wktToGeometry(String wellKnownText) throws ParseException {

		return new WKTReader().read(wellKnownText);
	}

}
