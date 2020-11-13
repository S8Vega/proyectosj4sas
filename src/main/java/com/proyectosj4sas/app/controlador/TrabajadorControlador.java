package com.proyectosj4sas.app.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosj4sas.app.modelo.entidad.Trabajador;
import com.proyectosj4sas.app.modelo.servicio.implementacion.TrabajadorServicioImpl;

@RestController
@RequestMapping("/api/trabajador")
public class TrabajadorControlador {

	@Autowired
	private TrabajadorServicioImpl trabajadorServicio;
	
	@GetMapping
	public List<Trabajador> listar() {
		return this.trabajadorServicio.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void crear(@RequestBody Trabajador trabajador) {
		this.trabajadorServicio.save(trabajador);
	}

	@RequestMapping("/{id}")
	public Trabajador buscar(@PathVariable Long id) {
		return this.trabajadorServicio.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void actualizar(@PathVariable Long id, @RequestBody Trabajador trabajador) {
		trabajador.setId(id);
		this.trabajadorServicio.save(trabajador);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void eliminar(@PathVariable Long id) {
		this.trabajadorServicio.deleteById(id);
	}
}
