package com.proyectosj4sas.app.modelo.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Eps implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String codigo;
	@OneToMany(mappedBy = "eps", cascade = CascadeType.REMOVE)
	private List<AfiliadoEps> afiliadoEps;
	private static final long serialVersionUID = 1L;
}
