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
public class AfiliadoArl implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	@OneToOne
	@JoinColumn(name = "trabajador", unique = true)
	private Trabajador trabajador;
	@ManyToOne
	@JoinColumn(name = "arl")
	private Arl arl;
	private static final long serialVersionUID = 1L;

	public AfiliadoArl() {
	}

	public AfiliadoArl(Long id, String codigo, Trabajador trabajador, Arl arl) {
		this.id = id;
		this.codigo = codigo;
		this.trabajador = trabajador;
		this.arl = arl;
	}

	@Override
	public String toString() {
		return "AfiliadoArl [id=" + id + ", codigo=" + codigo + ", trabajador=" + trabajador + ", arl=" + arl + "]";
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

	public Arl getArl() {
		return arl;
	}

	public void setArl(Arl arl) {
		this.arl = arl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
