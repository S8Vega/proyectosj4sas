package com.proyectosj4sas.app.controlador;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyectosj4sas.app.modelo.entidad.Empresa;
import com.proyectosj4sas.app.modelo.entidad.Obra;
import com.proyectosj4sas.app.modelo.servicio.implementacion.EmpresaServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObraServicioImpl;

@Controller
public class EmpresaControlador {

	@Autowired
	private EmpresaServicioImpl empresaService;
	@Autowired
	private ObraServicioImpl ob;

	@GetMapping("/empresas")
	public String listar(Model model) {

		List<Empresa> empresas = empresaService.findAll();
		model.addAttribute("titulo", "Empresas asociadas");
		model.addAttribute("ruta_de_navegacion", "Empresas asociadas");
		model.addAttribute("empresas", empresas);
		return "/vistas/empresas/listar";
	}

	@GetMapping("/empresas/{id}")
	public String getEmpresa(@PathVariable Long id, Model model) {
		Empresa empresa = empresaService.findById(id);
		if (empresa != null) {
			model.addAttribute("titulo", "Empresas asociadas");
			model.addAttribute("ruta_de_navegacion", "Empresa asociada");
			model.addAttribute("total_obreros", empresa.totalObreros());
			model.addAttribute("empresa", empresa);
		}else {
			model.addAttribute("danger", "No existe empresa!");
		}
		return "/vistas/empresas/empresa";
	}
	
	@GetMapping("/empresas/{id_empresa}/obras")
	public String getObrasPorEmpresa(@PathVariable Long id_empresa,Model model) {
		Empresa empresa=empresaService.findById(id_empresa);
		List<Obra> obras_t =ob.findAll();
		List<Obra> obras = new LinkedList<Obra>();
		for (Obra obra : obras_t) {
			if(obra.getEmpresa().getId()==id_empresa) {
				obras.add(obra);
			}
		}
		model.addAttribute("titulo", "Empresas asociadas");
		model.addAttribute("ruta_de_navegacion", "Empresa Asociada");
		model.addAttribute("total_obreros", empresa.totalObreros());
		model.addAttribute("obras", obras);
		return "/vistas/obras/listar";
	}

}
