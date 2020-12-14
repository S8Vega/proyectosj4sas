package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Empresa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String nit;
	private String direccion;
	private String telefono;
	@OneToOne(mappedBy = "empresa", cascade = CascadeType.REMOVE)
	private Contador contador;
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.REMOVE)
	private Set<Obra> obra;
	private static final long serialVersionUID = 1L;

	public Empresa() {
	}

	public Empresa(Long id, String nombre, String nit, String direccion, String telefono, Contador contador,
			Set<Obra> obra) {
		this.id = id;
		this.nombre = nombre;
		this.nit = nit;
		this.direccion = direccion;
		this.telefono = telefono;
		this.contador = contador;
		this.obra = obra;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nombre=" + nombre + ", nit=" + nit + ", direccion=" + direccion + ", telefono="
				+ telefono + ", contador=" + contador + ", obra=" + obra + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Empresa other = (Empresa) obj;
		return nombre.equals(other.getNombre()) && nit.equals(other.getNit()) && direccion.equals(other.getDireccion())
				&& telefono.equals(other.getTelefono());
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

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Contador getContador() {
		return contador;
	}

	public void setContador(Contador contador) {
		this.contador = contador;
	}

	public Set<Obra> getObra() {
		return obra;
	}

	public void setObra(Set<Obra> obra) {
		this.obra = obra;
	}

	public int totalObreros() {
		int total = 0;
		for (Obra obra2 : obra) {
			total += obra2.getObrero() == null ? 0 : obra2.getObrero().size();
		}
		return total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
