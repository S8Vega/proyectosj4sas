package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IFondoPensionDao;
import com.proyectosj4sas.app.modelo.entidad.FondoPension;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class FondoPensionServicioImpl implements IServicio<FondoPension, Long> {

	@Autowired
	private IFondoPensionDao fondoPensionDao;

	@Override
	@Transactional(readOnly = true)
	public FondoPension findById(Long id) {
		return fondoPensionDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<FondoPension> findAll() {
		return (List<FondoPension>) fondoPensionDao.findAll();
	}

	@Override
	@Transactional
	public void save(FondoPension t) {
		fondoPensionDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		fondoPensionDao.deleteById(id);
	}

}
