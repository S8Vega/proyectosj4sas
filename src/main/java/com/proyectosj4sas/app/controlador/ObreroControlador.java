package com.proyectosj4sas.app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ObreroControlador {
	
	@GetMapping({ "/obreros/crear" })
	public String listar(Model model) {
		model.addAttribute("titulo", "CREAR OBRERO");
		model.addAttribute("ruta_de_navegacion", "REGISTRO DE OBRERO");
		return "/vistas/obras/obreros/registrar";
	}

}
