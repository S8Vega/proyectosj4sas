package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Obra implements Serializable {

	private Long id;
	private ObraObrero obraObrero;
	private Representante representante;
	private Siso siso;
	private String estado;
	private Empresa empresa;
	private String nombre;
	private String direccion;
	private static final long serialVersionUID = 1L;

}
