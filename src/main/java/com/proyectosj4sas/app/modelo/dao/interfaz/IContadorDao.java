package com.proyectosj4sas.app.modelo.dao.interfaz;

import org.springframework.data.repository.CrudRepository;

import com.proyectosj4sas.app.modelo.entidad.Contador;

public interface IContadorDao extends CrudRepository<Contador, Long> {

}
