package com.proyectosj4sas.app.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Eps implements Serializable {

	private Long id;
	private String nombre;
	private String codigo;
	private List<AfiliadoFondoPension> afiliadoEps;
	private static final long serialVersionUID = 1L;
}
