package com.proyectosj4sas.app.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Empresa implements Serializable {

	private Long id;
	private String nombre;
	private String nit;
	private String direccion;
	private String telefono;
	private Contador contador;
	private List<Obra> obras;
	private static final long serialVersionUID = 1L;

}
