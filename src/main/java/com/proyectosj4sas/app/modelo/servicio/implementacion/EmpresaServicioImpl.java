package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IEmpresaDao;
import com.proyectosj4sas.app.modelo.entidad.Empresa;
import com.proyectosj4sas.app.modelo.entidad.Obra;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class EmpresaServicioImpl implements IServicio<Empresa, Long> {

	@Autowired
	private IEmpresaDao empresaDao;

	@Override
	@Transactional(readOnly = true)
	public Empresa findById(Long id) {
		return empresaDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Empresa> findAll() {
		return (List<Empresa>) empresaDao.findAll();
	}

	@Override
	@Transactional
	public void save(Empresa t) {
		empresaDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		empresaDao.deleteById(id);
	}
	
	public List<Obra> listaObrasPorEmpresa(Long idEmpresa){
		return empresaDao.listaObrasPorEmpresa(idEmpresa);		
	}
}
