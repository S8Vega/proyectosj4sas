package com.proyectosj4sas.app.dto;

public class AfpVO {
	private Long id;
	private String nombre;
	private String codigo;
	public AfpVO(Long id, String nombre, String codigo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
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
	
	
}
