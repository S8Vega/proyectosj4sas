package com.proyectosj4sas.app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AfiliadoEps implements Serializable {

	private Long id;
	private String codigo;
	private String trabajador;
	private Eps eps;
	private static final long serialVersionUID = 1L;

}
