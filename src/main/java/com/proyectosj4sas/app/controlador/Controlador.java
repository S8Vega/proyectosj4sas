package com.proyectosj4sas.app.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controlador {

	@RequestMapping
	public String listar() {
		return "conectado";
	}
}
