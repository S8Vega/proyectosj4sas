package com.proyectosj4sas.app.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyectosj4sas.app.modelo.entidad.Obrero;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObreroServicioImpl;

import com.proyectosj4sas.app.reportes.ObreroReporteExcel;

@Controller
public class ObraControlador {
	@Autowired
	private ObreroServicioImpl obreroService;

	@GetMapping({"/obras"})
	public String listar(Model model) {
		model.addAttribute("titulo", "Obras");
		model.addAttribute("ruta_de_navegacion", "Obras");
		return "/vistas/obras/listar";
	}
	
	@GetMapping("/constructoras/obras/{id}")
	public void reporteArl(@PathVariable Long id, HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
 
		String headerValue = "attachment; filename=constructura_xxx_fecha_xxx.xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<Obrero> obrerosSinArl = obreroService.listaObrerosSinArl(id);
		List<Obrero> obrerosSinEps = obreroService.listaObrerosSinEps(id);
		List<Obrero> obrerosSinAfp = obreroService.listaObrerosSinAfp(id);

		ObreroReporteExcel excelExporter = new ObreroReporteExcel(obrerosSinArl,obrerosSinEps,obrerosSinAfp);

		excelExporter.export(response);
	}
	

}
