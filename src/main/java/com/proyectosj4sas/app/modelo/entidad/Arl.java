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
public class Arl implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String codigo;
	@OneToMany(mappedBy = "arl", cascade = CascadeType.REMOVE)
	private Set<AfiliadoArl> afiliadoArl;
	private static final long serialVersionUID = 1L;

	public Arl() {
	}
	public Arl(Long id, String nombre, String codigo) {
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
	
	}
	public Arl(Long id, String nombre, String codigo, Set<AfiliadoArl> afiliadoArl) {
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.afiliadoArl = afiliadoArl;
	}

//	@Override
//	public String toString() {
//		return "Arl [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", afiliadoArl=" + afiliadoArl + "]";
//	}
	@Override
	public String toString() {
		return "Arl [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + "]";
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

	public Set<AfiliadoArl> getAfiliadoArl() {
		return afiliadoArl;
	}

	public void setAfiliadoArl(Set<AfiliadoArl> afiliadoArl) {
		this.afiliadoArl = afiliadoArl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
