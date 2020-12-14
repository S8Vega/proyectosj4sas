package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Contador implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "empresa", unique = true, updatable = true, insertable = true)
	private Empresa empresa;
	private String telefono;
	private String correo;
	private String nombre;
	private static final long serialVersionUID = 1L;

	public Contador() {
	}

	public Contador(Long id, Empresa empresa, String telefono, String correo, String nombre) {
		this.id = id;
		this.empresa = empresa;
		this.telefono = telefono;
		this.correo = correo;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Contador [id=" + id + ", empresa=" + empresa + ", telefono=" + telefono + ", correo=" + correo
				+ ", nombre=" + nombre + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Contador other = (Contador) obj;
		return telefono.equals(other.getTelefono()) && correo.equals(other.getCorreo())
				&& nombre.equals(other.getNombre());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
