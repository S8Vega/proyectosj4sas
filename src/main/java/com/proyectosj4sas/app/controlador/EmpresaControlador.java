package com.proyectosj4sas.app.controlador;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectosj4sas.app.modelo.entidad.Empresa;
import com.proyectosj4sas.app.modelo.entidad.Obra;
import com.proyectosj4sas.app.modelo.servicio.implementacion.EmpresaServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObraServicioImpl;

@Controller
@RequestMapping("/empresas")
public class EmpresaControlador {

	@Autowired
	private EmpresaServicioImpl empresaService;
	@Autowired
	private ObraServicioImpl ob;

	@GetMapping("/")
	public String listar(Model model) {
		List<Empresa> empresas = empresaService.findAll();
		model.addAttribute("titulo", "Empresas asociadas");
		model.addAttribute("ruta_de_navegacion", "Empresas asociadas");
		model.addAttribute("empresas", empresas);
		return "/vistas/empresas/listar";
	}

	@GetMapping("/{id}")
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
	
	@GetMapping("/{id_empresa}/obras")
	public String getObrasPorEmpresa(@PathVariable Long id_empresa,Model model) {
		Empresa empresa=empresaService.findById(id_empresa);
		List<Obra> obras = new LinkedList<Obra>();
		for (Obra obra : ob.findAll()) {
			if(obra.getEmpresa().getId()==id_empresa) {
				obras.add(obra);
			}
		}
		model.addAttribute("titulo", "Empresas asociadas");
		model.addAttribute("ruta_de_navegacion", "Empresa Asociada");
		model.addAttribute("total_obreros", empresa.totalObreros());
		model.addAttribute("idEmpresa", id_empresa);
		model.addAttribute("obras", obras);
		return "/vistas/obras/listar";
	}
	
	@GetMapping("/registrar")
	public String registrar(Model model) {
		model.addAttribute("titulo", "Registro Empresa");
		model.addAttribute("ruta_de_navegacion", "Registro Empresa");
		model.addAttribute("empresa", new Empresa());
		return "/vistas/empresas/registrar";
	}
	@GetMapping("/modificar/{idEmpresa}")
	public String modificar(@PathVariable Long idEmpresa,Model model) {
		model.addAttribute("titulo", "Modificar Empresa");
		model.addAttribute("ruta_de_navegacion", "Modificar Empresa");
		model.addAttribute("empresa", empresaService.findById(idEmpresa));
		return "/vistas/empresas/modificar";
	}
	@PostMapping("/guardar_modificado")
	public String guardarModificado(@ModelAttribute Empresa empresa, RedirectAttributes flash) {
		empresaService.save(empresa);
		flash.addFlashAttribute("success", "Empresa Modificada correctamente");
		return "redirect:/empresas/"+empresa.getId();
	}
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Empresa empresa, RedirectAttributes flash) {
		empresaService.save(empresa);
		flash.addFlashAttribute("success", "Empresa registrada correctamente");
		return "redirect:/empresas/";
	}

}
