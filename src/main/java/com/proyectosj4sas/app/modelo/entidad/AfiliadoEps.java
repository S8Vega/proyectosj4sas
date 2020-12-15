package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaIngreso;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaRetiro;
	private static final long serialVersionUID = 1L;

	public AfiliadoEps() {
	}

	public AfiliadoEps(String codigo, Trabajador trabajador, Eps eps, Date fechaIngreso) {
		super();
		this.codigo = codigo;
		this.trabajador = trabajador;
		this.eps = eps;
		this.fechaIngreso = fechaIngreso;
	}

	public AfiliadoEps(Long id, String codigo, Trabajador trabajador, Eps eps, Date fechaIngreso, Date fechaRetiro) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.trabajador = trabajador;
		this.eps = eps;
		this.fechaIngreso = fechaIngreso;
		this.fechaRetiro = fechaRetiro;
	}

	@Override
	public String toString() {
		return "AfiliadoEps [id=" + id + ", codigo=" + codigo + ", trabajador=" + trabajador + ", eps=" + eps
				+ ", fechaIngreso=" + fechaIngreso + ", fechaRetiro=" + fechaRetiro + "]";
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
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
