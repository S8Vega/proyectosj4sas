package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IArlDao;
import com.proyectosj4sas.app.modelo.entidad.Arl;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class ArlServicioImpl implements IServicio<Arl, Long> {

	@Autowired
	private IArlDao arlDao;

	@Override
	@Transactional(readOnly = true)
	public Arl findById(Long id) {
		return arlDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Arl> findAll() {
		return (List<Arl>) arlDao.findAll();
	}

	@Override
	@Transactional
	public void save(Arl t) {
		arlDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		arlDao.deleteById(id);
	}

}
