package com.proyectosj4sas.app.controlador;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyectosj4sas.app.dto.EpsVO;
import com.proyectosj4sas.app.modelo.entidad.Eps;
import com.proyectosj4sas.app.modelo.servicio.implementacion.EpsServicioImpl;

@Controller
@RequestMapping("/eps")
public class EpsControlador {
	
	@Autowired
	private EpsServicioImpl epsService;
	
	@GetMapping(value = "/", produces = { "application/json" })
	public @ResponseBody List<EpsVO> listar() {
		List<EpsVO> lista = new LinkedList<EpsVO>();
		for (Eps eps : epsService.findAll()) {
			lista.add(new EpsVO(eps.getId(), eps.getNombre(), eps.getCodigo()));
		}
		return lista;
	}

}
