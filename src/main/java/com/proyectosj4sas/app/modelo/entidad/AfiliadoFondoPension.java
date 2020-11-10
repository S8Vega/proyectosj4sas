package com.proyectosj4sas.app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AfiliadoFondoPension implements Serializable {

	private Long id;
	private String codigo;
	private Trabajador trabajador;
	private FondoPension fondoPension;
	private static final long serialVersionUID = 1L;
}
