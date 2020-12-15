package com.proyectosj4sas.app.modelo.dao.interfaz;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.proyectosj4sas.app.modelo.entidad.PasswordResetTokenEntity;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenEntity, Long>{
	@Query(value = "{call get_token(:token_name)}", nativeQuery = true)
	PasswordResetTokenEntity findByToken(@Param("token_name") String token);
	
	
}
