package com.proyectosj4sas.app.modelo.dao.interfaz;

import org.springframework.data.repository.CrudRepository;

import com.proyectosj4sas.app.modelo.entidad.Empresa;

public interface IEmpresaDao extends CrudRepository<Empresa, Long> {

}
