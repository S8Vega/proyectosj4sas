package com.proyectosj4sas.app.controlador;


import java.util.Date;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectosj4sas.app.modelo.entidad.AfiliadoArl;
import com.proyectosj4sas.app.modelo.entidad.AfiliadoEps;
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
	@Autowired private AfiliadoEpsServicioImpl afiliadoEpsService;
	@Autowired private AfiliadoFondoPensionServicioImpl afiliadoAfpService;
	
	@GetMapping({ "/crear/{idObra}" })
	public String listar(@PathVariable Long idObra,Model model) {
		
	Trabajador trabajador = new Trabajador();
	Obrero obrero = new Obrero();
	trabajador.setAfiliadoFondoPension(new AfiliadoFondoPension());
	trabajador.getAfiliadoFondoPension().setFondoPension(new FondoPension());
//	AfiliadoArl af = new AfiliadoArl();
//	
//	trabajador.setAfiliadoArl(af);
//	trabajador.getAfiliadoArl().setArl(new Arl());
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
	public String guardar( @ModelAttribute Obrero obrero,BindingResult result, Model model,
			@RequestParam(name = "id_obra", required = false) Long idObra,
			@RequestParam(name = "id_arl", required = false) Long idArl,
			@RequestParam(name = "codigo_afiliado_arl", required = false) String codigoAfiliadoArl,
			@RequestParam("fecha_registro_arl")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaRegistroArl
			)
			 {
		Obra obra  = obraService.findById(idObra);
		Arl arl = arlService.findById(idArl);
		System.out.println("Nombre de la obra: " +obra.getNombre());
		System.out.println("Codigo afiliado url: " +codigoAfiliadoArl);
		
		obrero.setObra(obra);
		trabajadorService.save(obrero.getTrabajador());
		obrero.setFechaIngreso(new Date());
		Trabajador trabajador = obrero.getTrabajador();
		if(idArl!=-1) {
			AfiliadoArl afArl = new AfiliadoArl(codigoAfiliadoArl,trabajador, arl, fechaRegistroArl);
			afiliadoArlService.save(afArl);
			System.out.println(afArl.getCodigo()+"ARL:");
		
		}else {System.out.println("no va a guardar nada");}
		if(obrero.getTrabajador().getAfiliadoEps().getEps().getId()!=4) {
			AfiliadoEps afEps = obrero.getTrabajador().getAfiliadoEps();
			afEps.setTrabajador(obrero.getTrabajador());
			afEps.setEps(epsService.findById(obrero.getTrabajador().getAfiliadoEps().getEps().getId()));
			afiliadoEpsService.save(afEps);
			System.out.println(afEps.getCodigo());
		
		}
		if(obrero.getTrabajador().getAfiliadoFondoPension().getFondoPension().getId()!=4) {
			AfiliadoFondoPension afAfp = obrero.getTrabajador().getAfiliadoFondoPension();
			afAfp.setTrabajador(obrero.getTrabajador());
			afAfp.setFondoPension(afpService.findById(obrero.getTrabajador().getAfiliadoFondoPension().getFondoPension().getId()));
			afiliadoAfpService.save(afAfp);
			System.out.println(afAfp.getCodigo());
		
		}
		System.out.println("prueba del envio de parametros");
		System.out.println(idObra);
				obreroService.save(obrero);
				//model.addAttribute("obra", obraService.findById(obrero.getObra().getId()).getNombre());
		return "redirect:/obras/"+obrero.getObra().getId();
	}
	
	@PostMapping("/form")
	public String modificarEstadoConJavascript(Obrero obrero,RedirectAttributes flash,
			@RequestParam(name="codigo_secreto",required = false) String codigo_m
			) {
		System.out.println("##");
		System.out.println(codigo_m);
		return "index";
	}

}
