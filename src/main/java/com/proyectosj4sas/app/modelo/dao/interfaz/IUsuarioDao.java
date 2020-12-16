package com.proyectosj4sas.app.modelo.dao.interfaz;

import org.springframework.data.repository.CrudRepository;

import com.proyectosj4sas.app.modelo.entidad.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	public Usuario findByEmail(String email);
	public Usuario findByUsername(String username);
	

}
