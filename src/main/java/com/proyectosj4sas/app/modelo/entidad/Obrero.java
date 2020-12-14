package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Obrero implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "trabajador")
	private Trabajador trabajador;
	private String estado;
	@ManyToOne
	@JoinColumn(name = "obra")
	private Obra obra;
	private String cargo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaIngreso;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaRetiro;
	private static final long serialVersionUID = 1L;

	public Obrero() {
	}

	public Obrero(Long id, Trabajador trabajador, String estado, Obra obra, String cargo, Date fechaIngreso,
			Date fechaRetiro) {
		super();
		this.id = id;
		this.trabajador = trabajador;
		this.estado = estado;
		this.obra = obra;
		this.cargo = cargo;
		this.fechaIngreso = fechaIngreso;
		this.fechaRetiro = fechaRetiro;
	}

	@Override
	public String toString() {
		return "Obrero [id=" + id + ", trabajador=" + trabajador + ", estado=" + estado + ", obra=" + obra + ", cargo="
				+ cargo + ", fechaIngreso=" + fechaIngreso + ", fechaRetiro=" + fechaRetiro + "]";
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

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
