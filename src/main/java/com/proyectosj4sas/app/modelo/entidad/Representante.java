package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Representante implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "representante", cascade = CascadeType.REMOVE)
	private Set<Obra> obra;
	private String telefono;
	private String correo;
	private String nombre;
	private static final long serialVersionUID = 1L;

	public Representante() {
	}

	public Representante(Long id, Set<Obra> obra, String telefono, String correo, String nombre) {
		this.id = id;
		this.obra = obra;
		this.telefono = telefono;
		this.correo = correo;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Representante [id=" + id + ", obra=" + obra + ", telefono=" + telefono + ", correo=" + correo
				+ ", nombre=" + nombre + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Obra> getObra() {
		return obra;
	}

	public void setObra(Set<Obra> obra) {
		this.obra = obra;
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
