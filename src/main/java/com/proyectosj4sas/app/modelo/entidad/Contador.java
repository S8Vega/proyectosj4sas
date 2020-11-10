package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contador implements Serializable {

	private Long id;
	private Empresa empresa;
	private String telefono;
	private String correo;
	private String nombre;
	private static final long serialVersionUID = 1L;

}
