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
public class Trabajador implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "trabajador", cascade = CascadeType.ALL)
	private Set<Obrero> obrero;
	@OneToOne(mappedBy = "trabajador", cascade = CascadeType.ALL)
	private Siso siso;
	@OneToOne(mappedBy = "trabajador", cascade = CascadeType.ALL)
	private AfiliadoArl afiliadoArl;
	@OneToOne(mappedBy = "trabajador", cascade = CascadeType.ALL)
	private AfiliadoEps afiliadoEps;
	@OneToOne(mappedBy = "trabajador", cascade = CascadeType.ALL)
	private AfiliadoFondoPension afiliadoFondoPension;
	private String nombre;
	private String cedula;
	private static final long serialVersionUID = 1L;

	public Trabajador() {
	}

	public Trabajador(Long id, Set<Obrero> obrero, Siso siso, AfiliadoArl afiliadoArl, AfiliadoEps afiliadoEps,
			AfiliadoFondoPension afiliadoFondoPension, String nombre, String cedula) {
		this.id = id;
		this.obrero = obrero;
		this.siso = siso;
		this.afiliadoArl = afiliadoArl;
		this.afiliadoEps = afiliadoEps;
		this.afiliadoFondoPension = afiliadoFondoPension;
		this.nombre = nombre;
		this.cedula = cedula;
	}

	@Override
	public String toString() {
		return "Trabajador [id=" + id + ", obrero=" + obrero + ", siso=" + siso + ", afiliadoArl=" + afiliadoArl
				+ ", afiliadoEps=" + afiliadoEps + ", afiliadoFondoPension=" + afiliadoFondoPension + ", nombre="
				+ nombre + ", cedula=" + cedula + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Obrero> getObrero() {
		return obrero;
	}

	public void setObrero(Set<Obrero> obrero) {
		this.obrero = obrero;
	}

	public Siso getSiso() {
		return siso;
	}

	public void setSiso(Siso siso) {
		this.siso = siso;
	}

	public AfiliadoArl getAfiliadoArl() {
		return afiliadoArl;
	}

	public void setAfiliadoArl(AfiliadoArl afiliadoArl) {
		this.afiliadoArl = afiliadoArl;
	}

	public AfiliadoEps getAfiliadoEps() {
		return afiliadoEps;
	}

	public void setAfiliadoEps(AfiliadoEps afiliadoEps) {
		this.afiliadoEps = afiliadoEps;
	}

	public AfiliadoFondoPension getAfiliadoFondoPension() {
		return afiliadoFondoPension;
	}

	public void setAfiliadoFondoPension(AfiliadoFondoPension afiliadoFondoPension) {
		this.afiliadoFondoPension = afiliadoFondoPension;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
