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
public class FondoPension implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String codigo;
	@OneToMany(mappedBy = "fondoPension", cascade = CascadeType.REMOVE)
	private Set<AfiliadoFondoPension> afiliadoFondoPension;
	private static final long serialVersionUID = 1L;

	public FondoPension() {
	}

	public FondoPension(Long id, String nombre, String codigo, Set<AfiliadoFondoPension> afiliadoFondoPension) {
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.afiliadoFondoPension = afiliadoFondoPension;
	}

	@Override
	public String toString() {
		return "FondoPension [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", afiliadoFondoPension="
				+ afiliadoFondoPension + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Set<AfiliadoFondoPension> getAfiliadoFondoPension() {
		return afiliadoFondoPension;
	}

	public void setAfiliadoFondoPension(Set<AfiliadoFondoPension> afiliadoFondoPension) {
		this.afiliadoFondoPension = afiliadoFondoPension;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
