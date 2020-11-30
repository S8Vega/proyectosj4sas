package com.proyectosj4sas.app.modelo.dao.interfaz;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.proyectosj4sas.app.modelo.entidad.Empresa;
import com.proyectosj4sas.app.modelo.entidad.Obra;

public interface IObraDao extends CrudRepository<Obra, Long> {

}
