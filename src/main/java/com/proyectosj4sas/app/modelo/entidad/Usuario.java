package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String alias;
	private String clave;
	private String email;
	private static final long serialVersionUID = 1L;

	public Usuario() {
	}

	public Usuario(Long id, String alias, String clave, String email) {
		this.id = id;
		this.alias = alias;
		this.clave = clave;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", alias=" + alias + ", clave=" + clave + ", email=" + email + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
