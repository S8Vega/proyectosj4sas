package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IObraDao;
import com.proyectosj4sas.app.modelo.entidad.Obra;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class ObraServicioImpl implements IServicio<Obra, Long> {

	@Autowired
	private IObraDao obraDao;

	@Override
	@Transactional(readOnly = true)
	public Obra findById(Long id) {
		return obraDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Obra> findAll() {
		return (List<Obra>) obraDao.findAll();
	}

	@Override
	@Transactional
	public void save(Obra t) {
		obraDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		obraDao.deleteById(id);
	}

}
