package com.gisapp.springboot.backend.apirest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gisapp.springboot.backend.apirest.dao.IGeometriesGenericDao;
import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;
import com.gisapp.springboot.backend.apirest.services.IGeometryService;

@Service
public class GeometryServiceImpl implements IGeometryService {

	@Autowired
	private IGeometriesGenericDao genericDao;

	@Override
	@Transactional(readOnly = true)
	public List<GeometryEntity> findAll() {
		return (List<GeometryEntity>) genericDao.findAll();
	}

	@Override
	@Transactional
	public void save(GeometryEntity geometry) {

		genericDao.save(geometry);
	}

	@Override
	@Transactional
	public void delete(GeometryEntity cliente) {
		genericDao.delete(cliente);

	}

}
