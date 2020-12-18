package com.proyectosj4sas.app.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectosj4sas.app.modelo.entidad.Obra;
import com.proyectosj4sas.app.modelo.entidad.Obrero;
import com.proyectosj4sas.app.modelo.servicio.implementacion.EmpresaServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObraServicioImpl;

import com.proyectosj4sas.app.modelo.servicio.implementacion.ObreroServicioImpl;
import com.proyectosj4sas.app.reportes.ObreroReporteExcel;

@Controller
public class ObraControlador {
	@Autowired
	private ObreroServicioImpl obreroService;
	@Autowired
	private ObraServicioImpl obraService;
	@Autowired
	private EmpresaServicioImpl empresaService;

	@GetMapping("/_obras")
	public String listadoGeneralObras(Model model){
		int total_obreros = 0;
		List<Obra> obras = obraService.findAll();
		for (Obra obj : obras) {
			total_obreros += obj.getObrero().size();
		}
		model.addAttribute("titulo", "PROYECTOS E INGENIERIA J4 S.A.");
		model.addAttribute("ruta_de_navegacion", "Listado general de obras");
		model.addAttribute("obras", obras);
		model.addAttribute("total_obreros", total_obreros);
		return "/vistas/obras/listado_completo";
	}

	@GetMapping("/obras/registrar/{idEmpresa}")
	public String index(@PathVariable Long idEmpresa, Model model) {
		model.addAttribute("titulo", "PROYECTOS E INGENIERIA J4 S.A.");
		model.addAttribute("ruta_de_navegacion", "Registrar una obra");
		model.addAttribute("obra", new Obra());
		return "/vistas/obras/registrar.html";
	}

	@PostMapping("/obras/guardar")
	public String guardarObra(@ModelAttribute Obra obra, RedirectAttributes flash,Model model,
			@RequestParam("fecha_inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha_inicio,
			@RequestParam("estado") String estado, @RequestParam("id_empresa") Long idEmpresa) {
		
			obra.setEmpresa(empresaService.findById(idEmpresa));
		obra.setFechaInicio(fecha_inicio);	
		obraService.save(obra);

		
		
		
		
		flash.addFlashAttribute("success", "Obra registrada correctamente");

		
		return "redirect:/empresas/" + idEmpresa + "/obras/";
	}

	@GetMapping("/obras")
	public String listar(Model model) {
		model.addAttribute("titulo", "Obras");
		model.addAttribute("ruta_de_navegacion", "Obras");
		return "/vistas/obras/listar";
	}

	@GetMapping("/obras/{id}")
	public String getObrasPorEmpresa(@PathVariable Long id, Model model) {
		Obra obra = obraService.findById(id);

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

		ObreroReporteExcel excelExporter = new ObreroReporteExcel(obrerosSinArl, obrerosSinEps, obrerosSinAfp);

		excelExporter.export(response);
	}

	@PostMapping(value = "/obras/estados/", produces = "application/json")
	public @ResponseBody Map<String, String> estados(@RequestParam(name = "id_obra", required = false) Long idObra,
			@RequestParam(name = "estado", required = false) String estado) {
		HashMap<String, String> map = new HashMap<>();
		Obra obra = obraService.findById(idObra);
		obra.setEstado(estado);
		obraService.save(obra);
		if (obraService.findById(idObra).getEstado().equals(estado)) {
			map.put("transaccion", "true");
			map.put("estado", estado);
		} else {
			map.put("transaccion", "false");
		}
		return map;
	}
	
}
