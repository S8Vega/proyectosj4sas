package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FondoPension implements Serializable {

	private Long id;
	private String nombre;
	private String codigo;
	private List<AfiliadoFondoPension> afiliadoFondoPension;
	private static final long serialVersionUID = 1L;

}