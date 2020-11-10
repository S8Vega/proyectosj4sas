package com.proyectosj4sas.app.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Siso implements Serializable {

	private Long id;
	private List<Obra> obras;
	private Trabajador trabajador;
	private String telefono;
	private String correo;
	private static final long serialVersionUID = 1L;

}
