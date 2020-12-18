package com.proyectosj4sas.app.controlador;


import com.proyectosj4sas.app.modelo.entidad.AfiliadoFondoPension;
import com.proyectosj4sas.app.modelo.servicio.implementacion.AfiliadoFondoPensionServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.FondoPensionServicioImpl;
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
@RequestMapping("/afiliaciones/afp")
public class AfiliacionAfpControlador {

	@Autowired
	private TrabajadorServicioImpl trabajadorService;
	@Autowired
	private AfiliadoFondoPensionServicioImpl afiliadoAfpService;
	@Autowired
	private FondoPensionServicioImpl afpService;

	@GetMapping("/create/{idTrabajador}/{idObrero}")
	public String crearAfiliacion(@PathVariable Long idTrabajador, @PathVariable Long idObrero, Model model) {
		AfiliadoFondoPension afiliacion = new AfiliadoFondoPension();
		afiliacion.setTrabajador(trabajadorService.findById(idTrabajador));
		model.addAttribute("titulo", "CREAR AFILIACION AFP");
		model.addAttribute("ruta_de_navegacion", "CREACION DE AFILIACION AFP");
		model.addAttribute("afps", afpService.findAll());
		model.addAttribute("idObrero", idObrero);
		model.addAttribute("afiliacionAfp", afiliacion);
		return "/vistas/afiliaciones/afp/crear";
	}

	@PostMapping("/save")
	public String guardarAfiliacion(@ModelAttribute AfiliadoFondoPension afiliado, RedirectAttributes flash,
			Model model, @RequestParam("idObrero") Long idObrero) {
		afiliadoAfpService.save(afiliado);
		flash.addFlashAttribute("success", "afiliacion de afp creada correctamente");
		return "redirect:/vistas/obreros/modificar/" + idObrero;
	}

	@GetMapping("/update/{afiliado}/{idObrero}")
	public String actualizarAfiliacion(@PathVariable Long afiliado, @PathVariable Long idObrero, Model model) {
		model.addAttribute("titulo", "MODIFICAR AFILIACION ARL");
		model.addAttribute("ruta_de_navegacion", "MODIFICACION DE AFILIACION ARL");
		model.addAttribute("afps", afpService.findAll());
		model.addAttribute("idObrero", idObrero);
		model.addAttribute("afiliacionAfp", afiliadoAfpService.findById(afiliado));
		return "/vistas/afiliaciones/afp/modificar";
	}

	@PostMapping("/save/update/")
	public String actualizarAfiliacion(@ModelAttribute AfiliadoFondoPension afiliado, RedirectAttributes flash,
			Model model, @RequestParam("idObrero") Long idObrero) {
		if (afiliado.getFondoPension().getId() == 16) {
			afiliado.setTrabajador(null);
			afiliado.setFondoPension(null);
			afiliadoAfpService.save(afiliado);
		} else {
			afiliadoAfpService.save(afiliado);
		}

		flash.addFlashAttribute("success", "afiliacion de afp modificada correctamente");
		return "redirect:/vistas/obreros/modificar/" + idObrero;
	}
}
