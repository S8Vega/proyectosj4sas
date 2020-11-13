package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IObreroDao;
import com.proyectosj4sas.app.modelo.entidad.Obrero;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class ObreroServicioImpl implements IServicio<Obrero, Long> {

	@Autowired
	private IObreroDao obreroDao;

	@Override
	@Transactional(readOnly = true)
	public Obrero findById(Long id) {
		return obreroDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Obrero> findAll() {
		return (List<Obrero>) obreroDao.findAll();
	}

	@Override
	@Transactional
	public void save(Obrero t) {
		obreroDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		obreroDao.deleteById(id);
	}

}
