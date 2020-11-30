package com.proyectosj4sas.app.modelo.dao.interfaz;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.proyectosj4sas.app.modelo.entidad.Empresa;
import com.proyectosj4sas.app.modelo.entidad.Obra;

public interface IEmpresaDao extends CrudRepository<Empresa, Long> {

	
	@Query(value = "{call lista_obras_por_empresa(:idIn)}", nativeQuery = true)
	List<Obra> listaObrasPorEmpresa(@Param("idIn") Long id);
}
