package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IAfiliadoEpsDao;
import com.proyectosj4sas.app.modelo.entidad.AfiliadoEps;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class AfiliadoEpsServicioImpl implements IServicio<AfiliadoEps, Long> {

	@Autowired
	private IAfiliadoEpsDao afiliadoEpsDao;

	@Override
	@Transactional(readOnly = true)
	public AfiliadoEps findById(Long id) {
		return afiliadoEpsDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AfiliadoEps> findAll() {
		return (List<AfiliadoEps>) afiliadoEpsDao.findAll();
	}

	@Override
	@Transactional
	public void save(AfiliadoEps t) {
		 afiliadoEpsDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		afiliadoEpsDao.deleteById(id);
	}
	
	public AfiliadoEps saveF(AfiliadoEps t) {
		return afiliadoEpsDao.save(t);
	}

}
