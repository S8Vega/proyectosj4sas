package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IEpsDao;
import com.proyectosj4sas.app.modelo.entidad.Eps;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class EpsServicioImpl implements IServicio<Eps, Long> {

	@Autowired
	private IEpsDao epsDao;

	@Override
	@Transactional(readOnly = true)
	public Eps findById(Long id) {
		return epsDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Eps> findAll() {
		return (List<Eps>) epsDao.findAll();
	}

	@Override
	@Transactional
	public void save(Eps t) {
		epsDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		epsDao.deleteById(id);
	}

}
