package com.proyectosj4sas.app.modelo.dao.interfaz;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.proyectosj4sas.app.modelo.entidad.AfiliadoArl;
import com.proyectosj4sas.app.modelo.entidad.Trabajador;

public interface ITrabajadorDao extends CrudRepository<Trabajador, Long> {

    @Query(value = "{call get_afiliacion_arl(:idIn)}", nativeQuery = true)
	Long getAfiliacionArl(@Param("idIn") Long idTrabajador);
}
