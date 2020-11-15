package com.proyectosj4sas.app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ObraControlador {

	@GetMapping({"/obras"})
	public String listar(Model model) {
		model.addAttribute("titulo", "Obras");
		model.addAttribute("ruta_de_navegacion", "Obras");
		return "/vistas/obras/listar";
	}
}
