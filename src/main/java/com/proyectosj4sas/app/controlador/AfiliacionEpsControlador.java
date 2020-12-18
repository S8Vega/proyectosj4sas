package com.proyectosj4sas.app.controlador;

import com.proyectosj4sas.app.modelo.entidad.AfiliadoEps;
import com.proyectosj4sas.app.modelo.servicio.implementacion.AfiliadoEpsServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.EpsServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.TrabajadorServicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/afiliaciones/eps")
public class AfiliacionEpsControlador {

	@Autowired
	private TrabajadorServicioImpl trabajadorService;
	@Autowired
	private EpsServicioImpl epsService;
	@Autowired
	private AfiliadoEpsServicioImpl afiliadoEpsService;

	@GetMapping("/create/{idTrabajador}/{idObrero}")
	public String crearAfiliacion(@PathVariable Long idTrabajador, @PathVariable Long idObrero, Model model) {
		AfiliadoEps afiliacion = new AfiliadoEps();
		afiliacion.setTrabajador(trabajadorService.findById(idTrabajador));
		model.addAttribute("titulo", "CREAR AFILIACION EPS");
		model.addAttribute("ruta_de_navegacion", "CREACION DE AFILIACION EPS");
		model.addAttribute("epss", epsService.findAll());
		model.addAttribute("idObrero", idObrero);
		model.addAttribute("afiliacionEps", afiliacion);
		return "/vistas/afiliaciones/eps/crear";
	}

	@PostMapping("/save")
	public String guardarAfiliacion(@ModelAttribute AfiliadoEps afiliado, RedirectAttributes flash, Model model,
			@RequestParam("idObrero") Long idObrero) {
		if (afiliado.getEps().getId() == 133) {

		} else {
			afiliadoEpsService.save(afiliado);
		}
		afiliadoEpsService.save(afiliado);
		flash.addFlashAttribute("success", "afiliacion de eps creada correctamente");
		return "redirect:/vistas/obreros/modificar/" + idObrero;
	}

	@GetMapping("/update/{afiliado}/{idObrero}")
	public String actualizarAfiliacion(@PathVariable Long afiliado, @PathVariable Long idObrero, Model model) {
		model.addAttribute("titulo", "MODIFICAR AFILIACION EPS");
		model.addAttribute("ruta_de_navegacion", "MODIFICACION DE AFILIACION EPS");
		model.addAttribute("epss", epsService.findAll());
		model.addAttribute("idObrero", idObrero);
		model.addAttribute("afiliacionEps", afiliadoEpsService.findById(afiliado));
		return "/vistas/afiliaciones/eps/modificar";
	}

	@PostMapping("/save/update/")
	public String actualizarAfiliacion(@ModelAttribute AfiliadoEps afiliado, RedirectAttributes flash, Model model,
			@RequestParam("idObrero") Long idObrero) {
		if (afiliado.getEps().getId() == 133) {System.out.println("borrando");
			AfiliadoEps af = afiliadoEpsService.findById(afiliado.getId());
			af.setEps(null);
			af.setTrabajador(null);
			afiliadoEpsService.save(af);
		} else {
			afiliadoEpsService.save(afiliado);
		}
		//afiliadoEpsService.save(afiliado);
		flash.addFlashAttribute("success", "afiliacion de eps modificada correctamente");
		return "redirect:/vistas/obreros/modificar/" + idObrero;
	}
}
