package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.ITrabajadorDao;
import com.proyectosj4sas.app.modelo.entidad.AfiliadoArl;
import com.proyectosj4sas.app.modelo.entidad.Trabajador;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class TrabajadorServicioImpl implements IServicio<Trabajador, Long> {

	@Autowired
	private ITrabajadorDao trabajadorDao;

	@Override
	@Transactional(readOnly = true)
	public Trabajador findById(Long id) {
		return trabajadorDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Trabajador> findAll() {
		return (List<Trabajador>) trabajadorDao.findAll();
	}

	@Override
	@Transactional
	public void save(Trabajador t) {
		trabajadorDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		trabajadorDao.deleteById(id);
	}

	public Long getAfiliacionArl(Long idTrabajador){
		return trabajadorDao.getAfiliacionArl(idTrabajador);
	}

}
