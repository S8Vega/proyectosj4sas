package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String username;
	private String password;
	@Column(unique = true)
	private String email;
	
	private boolean enable;
	@JoinColumn(name="usuario_id")
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Role> roles;
	private static final long serialVersionUID = 1L;

	public Usuario() {
	}

	public Usuario(Long id, String alias, String clave, String email) {
		this.id = id;
		this.username = alias;
		this.password = clave;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", alias=" + username + ", clave=" + password + ", email=" + email + "]";
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String alias) {
		this.username = alias;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String clave) {
		this.password = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
