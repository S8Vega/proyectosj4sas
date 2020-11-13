package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class AfiliadoFondoPension implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	@OneToOne
	@JoinColumn(name = "trabajador", unique = true)
	private Trabajador trabajador;
	@ManyToOne
	@JoinColumn(name = "fondoPension")
	private FondoPension fondoPension;
	private static final long serialVersionUID = 1L;

	public AfiliadoFondoPension() {
	}

	public AfiliadoFondoPension(Long id, String codigo, Trabajador trabajador, FondoPension fondoPension) {
		this.id = id;
		this.codigo = codigo;
		this.trabajador = trabajador;
		this.fondoPension = fondoPension;
	}

	@Override
	public String toString() {
		return "AfiliadoFondoPension [id=" + id + ", codigo=" + codigo + ", trabajador=" + trabajador
				+ ", fondoPension=" + fondoPension + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public FondoPension getFondoPension() {
		return fondoPension;
	}

	public void setFondoPension(FondoPension fondoPension) {
		this.fondoPension = fondoPension;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
