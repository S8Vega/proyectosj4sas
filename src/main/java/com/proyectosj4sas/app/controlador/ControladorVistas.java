package com.proyectosj4sas.app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorVistas {
    @GetMapping("/joimar/registrar_obra")
	public String index(Model model) {
		model.addAttribute("titulo", "PROYECTOS E INGENIERIA J4 S.A.");
		model.addAttribute("ruta_de_navegacion", "Registrar una obra");
		return "/vistas/obras/registrar.html";
	}

	@GetMapping("/joimar/registrar_obrero")
	public String obreroNuevo(Model model) {
		model.addAttribute("titulo", "PROYECTOS E INGENIERIA J4 S.A.");
		model.addAttribute("ruta_de_navegacion", "Registrar un obrero");
		return "/vistas/obras/obreros/registrar.html";
	}

	
}
