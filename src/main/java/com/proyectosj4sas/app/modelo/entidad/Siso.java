package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Siso implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "siso", cascade = CascadeType.REMOVE)
	private List<Obra> obra;
	@OneToOne
	@JoinColumn(name = "trabajador", unique = true)
	private Trabajador trabajador;
	private String telefono;
	private String correo;
	private static final long serialVersionUID = 1L;

}
