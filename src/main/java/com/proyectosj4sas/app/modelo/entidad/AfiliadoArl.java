package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AfiliadoArl implements Serializable {

	private Long id;
	private String codigo;
	private Trabajador trabajador;
	private Arl arl;
	private static final long serialVersionUID = 1L;

}
