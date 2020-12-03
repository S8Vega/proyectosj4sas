package com.proyectosj4sas.app.controlador;

<<<<<<< HEAD

import java.util.Date;



=======
import java.util.Date;
>>>>>>> e92b8adce9df55b3c7196887699eaeb8befcc1a9

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.proyectosj4sas.app.modelo.entidad.Eps;
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
<<<<<<< HEAD
	public String guardar( @ModelAttribute Obrero obrero,BindingResult result, Model model,
=======
	public String guardar(@ModelAttribute Obrero obrero,BindingResult result, Model model,
>>>>>>> e92b8adce9df55b3c7196887699eaeb8befcc1a9
			@RequestParam(name = "id_obra", required = false) Long idObra,
			@RequestParam(name = "id_arl", required = false) Long idArl,
			@RequestParam(name = "id_eps", required = false) Long idEps,
			@RequestParam(name = "id_afp", required = false) Long idAfp,
			@RequestParam(name = "codigo_afiliado_arl", required = false) String codigoAfiliadoArl,
			@RequestParam(name = "codigo_afiliado_eps", required = false) String codigoAfiliadoEps,
			@RequestParam(name = "codigo_afiliado_afp", required = false) String codigoAfiliadoAfp,
			@RequestParam("fecha_registro_arl")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaRegistroArl,
			@RequestParam("fecha_registro_eps")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaRegistroEps,
			@RequestParam("fecha_registro_afp")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaRegistroAfp
			)
			 {
		Obra obra  = obraService.findById(idObra);
		Arl arl = arlService.findById(idArl);
		Eps eps = epsService.findById(idEps);
		FondoPension afp = afpService.findById(idAfp);
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
		
		}
		if(idEps!=-1) {
			AfiliadoEps afEps = new AfiliadoEps(codigoAfiliadoEps, trabajador, eps, fechaRegistroEps);
			afiliadoEpsService.save(afEps);
				}
		if(idAfp!=-1) {
			AfiliadoFondoPension afAfp = new AfiliadoFondoPension(codigoAfiliadoAfp, trabajador, afp, fechaRegistroAfp);
			afiliadoAfpService.save(afAfp);		
		}
				obreroService.save(obrero);
		return "redirect:/obras/"+obra.getId();
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
