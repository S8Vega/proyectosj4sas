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
public class Eps implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String codigo;
	@OneToMany(mappedBy = "eps", cascade = CascadeType.REMOVE)
	private Set<AfiliadoEps> afiliadoEps;
	private static final long serialVersionUID = 1L;

	public Eps() {
	}

	public Eps(Long id, String nombre, String codigo, Set<AfiliadoEps> afiliadoEps) {
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.afiliadoEps = afiliadoEps;
	}

	@Override
	public String toString() {
		return "Eps [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", afiliadoEps=" + afiliadoEps + "]";
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

	public Set<AfiliadoEps> getAfiliadoEps() {
		return afiliadoEps;
	}

	public void setAfiliadoEps(Set<AfiliadoEps> afiliadoEps) {
		this.afiliadoEps = afiliadoEps;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
