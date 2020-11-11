package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Empresa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String nit;
	private String direccion;
	private String telefono;
	@OneToOne(mappedBy = "empresa", cascade = CascadeType.REMOVE)
	private Contador contador;
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.REMOVE)
	private List<Obra> obra;
	private static final long serialVersionUID = 1L;

}
