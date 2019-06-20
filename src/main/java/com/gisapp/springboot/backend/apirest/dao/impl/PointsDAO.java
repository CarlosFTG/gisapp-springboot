package com.gisapp.springboot.backend.apirest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.gisapp.springboot.backend.apirest.dao.IGeometriesDAO;
import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.GeometricShapeFactory;

@Repository
public class PointsDAO implements IGeometriesDAO {
	
	@PersistenceContext
    private EntityManager em;

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public Object findPointsByUserId(String userId) {
		
	
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT user FROM UserEntity user " + "WHERE id in (:userId) ");

		Query q = em.createQuery(sb.toString());

		q.setParameter("userId",Long.parseLong(userId));
		
		UserEntity userFound = new UserEntity();

		try {
			 userFound = (UserEntity) q.getSingleResult();
		}catch(NoResultException nre){
		}
		return userFound;
		
	}

	@Override
	public Object findPointsIntoAPolygon() {
		
		
		StoredProcedureQuery query = em
				.createStoredProcedureQuery("box");
				     
				query.execute();
	
		
		return null;
	}
	
	public Geometry createCircle(double x, double y, double radius) {
	    GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
	    shapeFactory.setNumPoints(32);
	    shapeFactory.setCentre(new Coordinate(x, y));
	    shapeFactory.setSize(radius * 2);
	    return shapeFactory.createCircle();
	}

}
