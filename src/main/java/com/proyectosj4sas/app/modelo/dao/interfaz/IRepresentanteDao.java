package com.proyectosj4sas.app.modelo.dao.interfaz;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.proyectosj4sas.app.modelo.entidad.Representante;

public interface IRepresentanteDao extends CrudRepository<Representante, Long> {
    @Query(value = "{call nuevo_representante()}", nativeQuery = true)
	List<Representante> nuevoRepresentante();
}
