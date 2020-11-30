package com.proyectosj4sas.app.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

<<<<<<< HEAD
import com.proyectosj4sas.app.modelo.entidad.Arl;
import com.proyectosj4sas.app.modelo.entidad.Obra;
import com.proyectosj4sas.app.modelo.entidad.Obrero;
import com.proyectosj4sas.app.modelo.entidad.Trabajador;
import com.proyectosj4sas.app.modelo.servicio.implementacion.AfiliadoEpsServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ArlServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObraServicioImpl;
=======
import com.proyectosj4sas.app.modelo.entidad.Obrero;
>>>>>>> 50c40f0db05e456c8081a64922a557f77d5f7781
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObreroServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.TrabajadorServicioImpl;
import com.proyectosj4sas.app.reportes.ObreroReporteExcel;

@Controller
public class ObraControlador {
	@Autowired
	private ObreroServicioImpl obreroService;
	@Autowired
	private ObraServicioImpl obraService;
	@Autowired
	private AfiliadoEpsServicioImpl epsService;
	@Autowired
	private TrabajadorServicioImpl trabajadorService;

	@GetMapping({"/obras"})
	public String listar(Model model) {
		model.addAttribute("titulo", "Obras");
		model.addAttribute("ruta_de_navegacion", "Obras");
		return "/vistas/obras/listar";
	}
	
	@GetMapping("/obras/{id}")
	public String getObrasPorEmpresa(@PathVariable Long id, Model model) {
		Obra obra=obraService.findById(id);
		System.out.println(obra.getRepresentante().getNombre());
//		Trabajador tbj = null;
//		obra.getObrero();
//		for (Obrero obj : obra.getObrero()) {
//			obj.setTrabajador(trabajadorService.findById(obj.getTrabajador().getId()));
//			//obj.getTrabajador().getAfiliadoEps().setEps(epsService.findById(Long.parseLong(obj.getTrabajador().getAfiliadoEps().getCodigo())).getEps());
//			System.out.println(obj.getTrabajador());
//		}
		model.addAttribute("titulo", "Obra");
		model.addAttribute("ruta_de_navegacion", "Obra");
		model.addAttribute("obra", obra);
		model.addAttribute("obreros", obra.getObrero());
		return "/vistas/obras/obra";
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
