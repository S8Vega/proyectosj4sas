package com.proyectosj4sas.app.modelo.dao.interfaz;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.proyectosj4sas.app.modelo.entidad.Obrero;

public interface IObreroDao extends CrudRepository<Obrero, Long> {

	@Query(value = "{call lista_obreros_sin_arl(:idIn)}", nativeQuery = true)
	List<Obrero> listaObrerosSinArl(@Param("idIn") Long idObra);

	@Query(value = "{call lista_obreros_sin_eps(:idIn)}", nativeQuery = true)
	List<Obrero> listaObrerosSinEps(@Param("idIn") Long idObra);

	@Query(value = "{call lista_obreros_sin_afp(:idIn)}", nativeQuery = true)
	List<Obrero> listaObrerosSinAfp(@Param("idIn") Long idObra);

}
