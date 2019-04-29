package com.gisapp.springboot.backend.apirest.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;

@Repository
public class GeometriesDAO implements IGeometriesDAO {
	
	@PersistenceContext
    private EntityManager em;

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<GeometryEntity> findPointsByUserId(String userId) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT geometry FROM GeometryEntity geometry WHERE user_id in (:userId) ");
		
		Query q = em.createQuery(sb.toString());
		
		q.setParameter("userId",userId);
		
		List<GeometryEntity> geometriesList = q.getResultList();
		
		return geometriesList;
	}

}
