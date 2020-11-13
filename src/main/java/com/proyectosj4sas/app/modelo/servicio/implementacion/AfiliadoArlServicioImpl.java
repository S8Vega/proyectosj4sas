package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IAfiliadoArlDao;
import com.proyectosj4sas.app.modelo.entidad.AfiliadoArl;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class AfiliadoArlServicioImpl implements IServicio<AfiliadoArl, Long> {

	@Autowired
	private IAfiliadoArlDao afiliadoArlDao;

	@Override
	@Transactional(readOnly = true)
	public AfiliadoArl findById(Long id) {
		return afiliadoArlDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AfiliadoArl> findAll() {
		return (List<AfiliadoArl>) afiliadoArlDao.findAll();
	}

	@Override
	@Transactional
	public void save(AfiliadoArl t) {
		afiliadoArlDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		afiliadoArlDao.deleteById(id);
	}

}
