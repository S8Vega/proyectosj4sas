package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Siso implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "siso", cascade = CascadeType.REMOVE)
	private Set<Obra> obra;
	@OneToOne
	@JoinColumn(name = "trabajador", unique = true)
	private Trabajador trabajador;
	private String telefono;
	private String correo;
	private static final long serialVersionUID = 1L;

	public Siso() {
	}

	public Siso(Long id, Set<Obra> obra, Trabajador trabajador, String telefono, String correo) {
		this.id = id;
		this.obra = obra;
		this.trabajador = trabajador;
		this.telefono = telefono;
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "Siso [id=" + id + ", obra=" + obra + ", trabajador=" + trabajador + ", telefono=" + telefono
				+ ", correo=" + correo + "]";
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

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
