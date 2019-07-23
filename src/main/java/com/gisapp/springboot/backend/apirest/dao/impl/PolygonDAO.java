package com.gisapp.springboot.backend.apirest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.gisapp.springboot.backend.apirest.dao.IPolygonDAO;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.vividsolutions.jts.geom.Polygon;

@Repository
public class PolygonDAO implements IPolygonDAO {
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<Polygon> createBuffer(NonGeometryEntity buffer){
				
		List<Polygon> bufferList= new ArrayList<Polygon>();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select buffer(p.coordinates, "
				+ "(:radio)) from PointsEntity p "
				+ "where userId in (:userId) "
				+ "and facility in (:facility)");
				
		Query q = em.createQuery(sb.toString());
		
		List<Polygon> buffersFound = new ArrayList<Polygon>();
		
		q.setParameter("radio", Double.parseDouble(buffer.getRadioBuffer()) );
		q.setParameter("userId",Long.parseLong(buffer.getUserId()));
		q.setParameter("facility",buffer.getFacility());
		buffersFound=q.getResultList();
		
		return buffersFound;
	}
}
