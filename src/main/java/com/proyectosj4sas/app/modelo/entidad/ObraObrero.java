package com.proyectosj4sas.app.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObraObrero implements Serializable {

	private Long id;
	private List<Obrero> obrero;
	private String estado;
	private List<Obra> obra;
	private String cargo;
	private static final long serialVersionUID = 1L;
}
