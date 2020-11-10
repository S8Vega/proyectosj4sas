package com.proyectosj4sas.app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Trabajador implements Serializable {

	private Long id;
	private Obrero obrero;
	private Siso siso;
	private Arl arl;
	private Eps eps;
	private FondoPension fondoPension;
	private String nombre;
	private String cedula;
	private static final long serialVersionUID = 1L;

}