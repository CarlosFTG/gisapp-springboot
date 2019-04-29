package com.gisapp.springboot.backend.apirest.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gisapp.springboot.backend.apirest.converter.GeometryEntityConverter;
import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.gisapp.springboot.backend.apirest.models.services.IClienteService;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/clientes")
	public List<GeometryEntity> index() {
		return clienteService.findAll();
	}

	@GetMapping("geometries/findByUserId/{id}")
	public NonGeometryEntity show(@PathVariable Long id) throws ParseException {
		return this.clienteService.findById(id);
	}
	
	@PostMapping("geometries/findPointsByUserId")
	@ResponseStatus(HttpStatus.FOUND)
	public List<NonGeometryEntity> findPointsByUserId(@RequestBody String body) throws ParseException {
		
		String[] bodyToSplit=body.split("=");
		String userId=bodyToSplit[1];
		
		return this.clienteService.findPointsByUserId(userId);
	}

	@PostMapping("/geometries/insertGeometry")
	@ResponseStatus(HttpStatus.CREATED)
	public GeometryEntity create(@RequestBody NonGeometryEntity geometry) throws ParseException {

		GeometryEntity geomToSave = new GeometryEntity();
		this.clienteService.save(GeometryEntityConverter.convertToGeometryEntity(geometry));
		return geomToSave;
	}

	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public GeometryEntity update(@RequestBody GeometryEntity cliente, @PathVariable Long id) {
		
		return null;
	}

	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		//GeometryEntity currentCliente = this.clienteService.findById(id);
		//this.clienteService.delete(currentCliente);
	}

//	private GeometryEntity convertToGeometryEntity(NonGeometryEntity nonGeometry) throws ParseException {
//
//		GeometryEntity geometryEntity = new GeometryEntity();
//
//		geometryEntity.setUserId(nonGeometry.getUserId());
//		geometryEntity.setPointName(nonGeometry.getPointName());
//		geometryEntity.setGeom(wktToGeometry(nonGeometry.getGeom()));
//		return geometryEntity;
//
//	}

	/**
	 * Method to convert the String geoinfo to Geometry
	 * 
	 * @param wellKnownText
	 * @return Geometry geometry
	 * @throws ParseException
	 */
	public Geometry wktToGeometry(String wellKnownText) throws ParseException {

		return new WKTReader().read(wellKnownText);
	}
}
