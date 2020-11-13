package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IContadorDao;
import com.proyectosj4sas.app.modelo.entidad.Contador;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class ContadorServicioImpl implements IServicio<Contador, Long> {

	@Autowired
	private IContadorDao contadorDao;

	@Override
	@Transactional(readOnly = true)
	public Contador findById(Long id) {
		return contadorDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Contador> findAll() {
		return (List<Contador>) contadorDao.findAll();
	}

	@Override
	@Transactional
	public void save(Contador t) {
		contadorDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		contadorDao.deleteById(id);
	}

}
