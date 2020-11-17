package com.proyectosj4sas.app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpresaControlador {
	
	@GetMapping({"/","/empresas"})
	public String listar(Model model) {
		model.addAttribute("titulo", "Empresas asociadas");
		model.addAttribute("ruta_de_navegacion", "Empresas asociadas");
		return "/vistas/empresas/listar";
	}

}
