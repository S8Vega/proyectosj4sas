package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IUsuarioDao;
import com.proyectosj4sas.app.modelo.entidad.Usuario;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;

@Service
public class UsuarioServicioImpl implements IServicio<Usuario, Long> {

	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario t) {
		usuarioDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		usuarioDao.deleteById(id);
	}

}
