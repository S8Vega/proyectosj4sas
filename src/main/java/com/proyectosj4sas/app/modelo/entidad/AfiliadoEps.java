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
public class AfiliadoEps implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	@OneToOne
	@JoinColumn(name = "trabajador", unique = true)
	private Trabajador trabajador;
	@ManyToOne
	@JoinColumn(name = "eps")
	private Eps eps;
	private static final long serialVersionUID = 1L;

	public AfiliadoEps() {
	}

	public AfiliadoEps(Long id, String codigo, Trabajador trabajador, Eps eps) {
		this.id = id;
		this.codigo = codigo;
		this.trabajador = trabajador;
		this.eps = eps;
	}

	@Override
	public String toString() {
		return "AfiliadoEps [id=" + id + ", codigo=" + codigo + ", trabajador=" + trabajador + ", eps=" + eps + "]";
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

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
