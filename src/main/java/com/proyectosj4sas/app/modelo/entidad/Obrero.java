package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Obrero implements Serializable {

	private Long id;
	private Trabajador trabajador;
	private String estado;
	private Obra obra;
	private String cargo;
	private static final long serialVersionUID = 1L;

}