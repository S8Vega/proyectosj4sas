package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Obra implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "obra", cascade = CascadeType.REMOVE)
	private List<Obrero> obrero;
	@ManyToOne
	@JoinColumn(name = "representante")
	private Representante representante;
	@ManyToOne
	@JoinColumn(name = "siso")
	private Siso siso;
	private String estado;
	@ManyToOne
	@JoinColumn(name = "empresa")
	private Empresa empresa;
	private String nombre;
	private String direccion;
	private static final long serialVersionUID = 1L;

}
