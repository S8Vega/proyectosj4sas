package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IAfiliadoFondoPensionDao;
import com.proyectosj4sas.app.modelo.entidad.AfiliadoFondoPension;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class AfiliadoFondoPensionServicioImpl implements IServicio<AfiliadoFondoPension, Long> {

	@Autowired
	private IAfiliadoFondoPensionDao afiliadoFondoPensionDao;

	@Override
	@Transactional(readOnly = true)
	public AfiliadoFondoPension findById(Long id) {
		return afiliadoFondoPensionDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AfiliadoFondoPension> findAll() {
		return (List<AfiliadoFondoPension>) afiliadoFondoPensionDao.findAll();
	}

	@Override
	@Transactional
	public void save(AfiliadoFondoPension t) {
		afiliadoFondoPensionDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		afiliadoFondoPensionDao.deleteById(id);
	}

}
