package com.gisapp.springboot.backend.apirest.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gisapp.springboot.backend.apirest.converter.PolygonsConverter;
import com.gisapp.springboot.backend.apirest.dao.IPolygonDAO;
import com.gisapp.springboot.backend.apirest.dao.IPolygonsGenericDAO;
import com.gisapp.springboot.backend.apirest.models.bean.BufferBean;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.gisapp.springboot.backend.apirest.services.IPolygonService;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;

@Service
public class PolygonService implements IPolygonService {
	@Autowired
	private IPolygonDAO polygonDAO;
	
	@Autowired
	private IPolygonsGenericDAO polygonGenericDAO;
	
	@Override
	public List<BufferBean> createBuffer(List<NonGeometryEntity> pointList) throws JSONException, ParseException {
				
		List<Polygon> buffersCreatedList= new ArrayList<Polygon>();
		
		
		List<BufferBean> bufferBeanList = new ArrayList<BufferBean>();
		
		buffersCreatedList=polygonDAO.createBuffer(pointList);
				
		bufferBeanList=PolygonsConverter.convertFromBufferListToBufferBeanList(buffersCreatedList);
		
		for(Polygon bufferCreated:buffersCreatedList) {
			
			polygonGenericDAO.save(PolygonsConverter.convertFromBufferBeanToBufferEntity(bufferCreated));
		}
		
		return bufferBeanList;
	}
	
	
}
