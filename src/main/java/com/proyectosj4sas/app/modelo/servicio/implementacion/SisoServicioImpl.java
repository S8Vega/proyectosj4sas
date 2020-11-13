package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.ISisoDao;
import com.proyectosj4sas.app.modelo.entidad.Siso;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class SisoServicioImpl implements IServicio<Siso, Long> {

	@Autowired
	private ISisoDao sisoDao;

	@Override
	@Transactional(readOnly = true)
	public Siso findById(Long id) {
		return sisoDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Siso> findAll() {
		return (List<Siso>) sisoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Siso t) {
		sisoDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		sisoDao.deleteById(id);
	}

}
