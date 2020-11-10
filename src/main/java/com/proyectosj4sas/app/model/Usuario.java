package com.proyectosj4sas.app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Usuario implements Serializable {

	private Long id;
	private String alias;
	private String clave;
	private String email;
	private static final long serialVersionUID = 1L;

}
