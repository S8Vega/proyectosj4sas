package com.proyectosj4sas.app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpresaControlador {
	
	@GetMapping("/")
	public String listar() {
		return "/vistas/empresas/listar";
	}

}
