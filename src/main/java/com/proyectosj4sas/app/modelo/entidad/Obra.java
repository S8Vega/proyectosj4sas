package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Obra implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "obra", cascade = CascadeType.REMOVE)
	private Set<Obrero> obrero;
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
	private Date fechaInicio;
	private Date fechaFin;
	private static final long serialVersionUID = 1L;

	public Obra() {
	}

	public Obra(Long id, Set<Obrero> obrero, Representante representante, Siso siso, String estado, Empresa empresa,
			String nombre, String direccion, Date fechaInicio, Date fechaFin) {
		super();
		this.id = id;
		this.obrero = obrero;
		this.representante = representante;
		this.siso = siso;
		this.estado = estado;
		this.empresa = empresa;
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "Obra [id=" + id + ", obrero=" + obrero + ", representante=" + representante + ", siso=" + siso
				+ ", estado=" + estado + ", empresa=" + empresa + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Obrero> getObrero() {
		return obrero;
	}

	public void setObrero(Set<Obrero> obrero) {
		this.obrero = obrero;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public Siso getSiso() {
		return siso;
	}

	public void setSiso(Siso siso) {
		this.siso = siso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
