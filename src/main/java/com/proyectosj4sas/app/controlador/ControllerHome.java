package com.proyectosj4sas.app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerHome {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("titulo", "PROYECTOS E INGENIERIA J4 S.A.");
		model.addAttribute("ruta_de_navegacion", "Vista Principal");
		return "index";
	}
	
	@GetMapping("/registrar")
	public String registro(Model model) {
		model.addAttribute("titulo", "PROYECTOS E INGENIERIA J4 S.A.");
		model.addAttribute("ruta_de_navegacion", "Registrar");
		return "/vistas/registro/index";
	}
	@GetMapping("/test")
	public String index2(Model model) {
		return "pruebas";
	}
}
