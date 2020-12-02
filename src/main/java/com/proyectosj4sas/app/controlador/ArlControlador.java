package com.proyectosj4sas.app.controlador;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.proyectosj4sas.app.modelo.entidad.Arl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ArlServicioImpl;
import com.proyectosj4sas.app.util.ArlResponse;

@Controller
@RequestMapping("/arl")
@SessionAttributes("arl")
public class ArlControlador {
	
	@Autowired
	private ArlServicioImpl arlService;
	@GetMapping(value = "/", produces = { "application/json" })
	public @ResponseBody List<ArlResponse> cargarProductos() {
		List<Arl> lista  =  arlService.findAll();
		List<ArlResponse> json = new LinkedList<ArlResponse>();
		for (Arl arl : lista) {
			json.add(new ArlResponse(arl.getId(), arl.getNombre(), arl.getCodigo()));
		}
		return json;
	}
}
