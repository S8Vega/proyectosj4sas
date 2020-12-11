package com.proyectosj4sas.app.controlador;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyectosj4sas.app.dto.AfpVO;
import com.proyectosj4sas.app.modelo.entidad.FondoPension;
import com.proyectosj4sas.app.modelo.servicio.implementacion.FondoPensionServicioImpl;

@Controller
@RequestMapping("/afp")
public class AfpControlador {
	
	@Autowired
	private FondoPensionServicioImpl afpService;
	
	@GetMapping(value = "/", produces = { "application/json" })
	public @ResponseBody List<AfpVO> listar(){
		List<AfpVO> lista = new LinkedList<AfpVO>();
		for (FondoPension afp : afpService.findAll()) {
			lista.add(new AfpVO(afp.getId(), afp.getNombre(), afp.getCodigo()));
		}
		return lista;
	}

}
