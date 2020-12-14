package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IRepresentanteDao;
import com.proyectosj4sas.app.modelo.entidad.Representante;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class RepresentanteServicioImpl implements IServicio<Representante, Long> {

	@Autowired
	private IRepresentanteDao representanteDao;

	@Override
	@Transactional(readOnly = true)
	public Representante findById(Long id) {
		return representanteDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Representante> findAll() {
		return (List<Representante>) representanteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Representante t) {
		representanteDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		representanteDao.deleteById(id);
	}
	public List<Representante> nuevoRepresentante(){
		return representanteDao.nuevoRepresentante();		
	}
}
