package com.proyectosj4sas.app.controlador;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectosj4sas.app.modelo.entidad.AfiliadoArl;
import com.proyectosj4sas.app.modelo.entidad.AfiliadoFondoPension;
import com.proyectosj4sas.app.modelo.entidad.Arl;
import com.proyectosj4sas.app.modelo.entidad.FondoPension;
import com.proyectosj4sas.app.modelo.entidad.Obra;
import com.proyectosj4sas.app.modelo.entidad.Obrero;
import com.proyectosj4sas.app.modelo.entidad.Trabajador;
import com.proyectosj4sas.app.modelo.servicio.implementacion.AfiliadoArlServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.AfiliadoEpsServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.AfiliadoFondoPensionServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ArlServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.EpsServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.FondoPensionServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObraServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObreroServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.TrabajadorServicioImpl;

@Controller
@RequestMapping("/vistas/obreros")
public class ObreroControlador {
	
	@Autowired
	private ObreroServicioImpl obreroService;
	@Autowired
	private TrabajadorServicioImpl trabajadorService;
	@Autowired
	private ArlServicioImpl arlService;
	@Autowired
	private EpsServicioImpl epsService;
	@Autowired
	private FondoPensionServicioImpl afpService;
	@Autowired
	private ObraServicioImpl obraService;
	@Autowired
	private AfiliadoArlServicioImpl afiliadoArlService;
	
	@GetMapping({ "/crear/{idObra}" })
	public String listar(@PathVariable Long idObra,Model model) {
		
	Trabajador trabajador = new Trabajador();
	Obrero obrero = new Obrero();
	trabajador.setAfiliadoFondoPension(new AfiliadoFondoPension());
	trabajador.getAfiliadoFondoPension().setFondoPension(new FondoPension());
	AfiliadoArl af = new AfiliadoArl();
	
	trabajador.setAfiliadoArl(af);
	trabajador.getAfiliadoArl().setArl(new Arl());
	obrero.setTrabajador(trabajador);
	obrero.setObra(obraService.findById(idObra));
	System.out.println(idObra);
		model.addAttribute("titulo", "CREAR OBRERO");
		model.addAttribute("ruta_de_navegacion", "REGISTRO DE OBRERO");	
		model.addAttribute("obrero", obrero);
		model.addAttribute("idObra", idObra);
		model.addAttribute("listaArl", arlService.findAll());
		model.addAttribute("listaEps", epsService.findAll());
		model.addAttribute("listaAfp", afpService.findAll());
		model.addAttribute("afp", new FondoPension());
		return "/vistas/obreros/registrar";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute Obrero obrero,BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("obrero", obrero);
			model.addAttribute("titulo", "CREAR OBRERO");
			model.addAttribute("ruta_de_navegacion", "REGISTRO DE OBRERO");
			return "vistas/obreros/registrar";
		}
		
		System.out.println("ob + " +obraService.findById(obrero.getObra().getId()).getNombre());
		obrero.setObra(obraService.findById(obrero.getObra().getId()));
		trabajadorService.save(obrero.getTrabajador());
		obrero.setFechaIngreso(new Date());
		
		
		if(obrero.getTrabajador().getAfiliadoArl().getArl().getId()!=4) {
			AfiliadoArl afArl = obrero.getTrabajador().getAfiliadoArl();
			afArl.setTrabajador(obrero.getTrabajador());
			afArl.setArl(arlService.findById(obrero.getTrabajador().getAfiliadoArl().getArl().getId()));
			afiliadoArlService.save(afArl);
			System.out.println(afArl.getCodigo());
		
		}

				obreroService.save(obrero);

		return "index";
	}

}
