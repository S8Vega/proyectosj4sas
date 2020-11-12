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
public class Trabajador implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "trabajador", cascade = CascadeType.REMOVE)
	private List<Obrero> obrero;
	@OneToOne(mappedBy = "trabajador", cascade = CascadeType.REMOVE)
	private Siso siso;
	@OneToOne(mappedBy = "trabajador", cascade = CascadeType.REMOVE)
	private AfiliadoArl afiliadoArl;
	@OneToOne(mappedBy = "trabajador", cascade = CascadeType.REMOVE)
	private AfiliadoEps afiliadoEps;
	@OneToOne(mappedBy = "trabajador", cascade = CascadeType.REMOVE)
	private AfiliadoFondoPension afiliadoFondoPension;
	private String nombre;
	private String cedula;
	private static final long serialVersionUID = 1L;

}
