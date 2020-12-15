package com.proyectosj4sas.app.modelo.dao.interfaz;

import org.springframework.data.repository.CrudRepository;

import com.proyectosj4sas.app.modelo.entidad.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	//public boolean requestPasswordReset(String email);
	
	public Usuario findByEmail(String email);

}
