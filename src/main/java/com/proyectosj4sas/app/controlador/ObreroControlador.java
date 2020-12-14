package com.proyectosj4sas.app.controlador;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@Autowired
	private AfiliadoEpsServicioImpl afiliadoEpsService;
	@Autowired
	private AfiliadoFondoPensionServicioImpl afiliadoAfpService;

	@GetMapping({ "/crear/{idObra}" })
	public String listar(@PathVariable Long idObra, Model model) {
		Obrero obrero = new Obrero();
		model.addAttribute("titulo", "CREAR OBRERO");
		model.addAttribute("ruta_de_navegacion", "REGISTRO DE OBRERO");
		model.addAttribute("obrero", obrero);
		model.addAttribute("idObra", idObra);
		return "/vistas/obreros/registrar";
	}
	@GetMapping({ "/eliminar/{idObrero}" })
	public String eliminar(@PathVariable Long idObrero, Model model, RedirectAttributes flash) {

		Obrero obrero = obreroService.findById(idObrero);
		long idObra = obrero.getObra().getId();
		obreroService.deleteById(idObrero);
		trabajadorService.deleteById(obrero.getTrabajador().getId());
		flash.addFlashAttribute("success", "Obrero eliminado correctamente");
		// model.addAttribute("idObra", idObra);
		return "redirect:/obras/" + idObra;
	}

	@GetMapping({ "/modificar/{idObrero}" })
	public String modificar(@PathVariable Long idObrero, Model model) {

		Obrero obrero = obreroService.findById(idObrero);

		System.out.println(obrero.getTrabajador().getId());
		System.out.println(obrero.getId());
		// System.out.println(afiliadoArlService.findById(trabajadorService.getAfiliacionArl(obrero.getTrabajador().getId())).getCodigo());//no
		// tiene un afiliaco url(buscar y asignar)

		// System.out.println(trabajadorService.getAfiliacionArl(obrero.getTrabajador().getId()));//no
		// tiene un afiliaco url(buscar y asignar)
		// obrero.setTrabajador(obreroService.getMyTrabajador(obrero.getId()));
		// System.out.println(obrero.getTrabajador().getNombre());
		// obrero.setTrabajador(trabajadorService.findById(obrero.getTrabajador().getId()));
		System.out.println(obrero.getCargo());
		model.addAttribute("titulo", "MODIFICAR OBRERO");
		model.addAttribute("ruta_de_navegacion", "MODIFICACION DE OBRERO");
		model.addAttribute("obrero", obrero);
		// model.addAttribute("idObra", idObra);
		return "/vistas/obreros/modificar";
	}
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Obrero obrero, RedirectAttributes flash, Model model,
			@RequestParam(name = "id_obra", required = false) Long idObra) {
		Obra obra = obraService.findById(idObra);
		obrero.setObra(obra);
		trabajadorService.save(obrero.getTrabajador());
		obrero.setFechaIngreso(new Date());
		obreroService.save(obrero);
		flash.addFlashAttribute("success", "Obrero registrado correctamente");
		return "redirect:/obras/" + idObra;
	}

	/**
	 * @PostMapping("/guardar")
	public String guardar(@ModelAttribute Obrero obrero, RedirectAttributes flash, Model model,
			@RequestParam(name = "id_obra", required = false) Long idObra,
			@RequestParam(name = "id_arl", required = false) Long idArl,
			@RequestParam(name = "id_eps", required = false) Long idEps,
			@RequestParam(name = "id_afp", required = false) Long idAfp,
			@RequestParam(name = "codigo_afiliado_arl", required = false) String codigoAfiliadoArl,
			@RequestParam(name = "codigo_afiliado_eps", required = false) String codigoAfiliadoEps,
			@RequestParam(name = "codigo_afiliado_afp", required = false) String codigoAfiliadoAfp,
			@RequestParam("fecha_registro_arl") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaRegistroArl,
			@RequestParam("fecha_registro_eps") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaRegistroEps,
			@RequestParam("fecha_registro_afp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaRegistroAfp) {
		Obra obra = obraService.findById(idObra);
		Arl arl = arlService.findById(idArl);
		Eps eps = epsService.findById(idEps);
		FondoPension afp = afpService.findById(idAfp);

		obrero.setObra(obra);
		trabajadorService.save(obrero.getTrabajador());
		obrero.setFechaIngreso(new Date());
		Trabajador trabajador = obrero.getTrabajador();
		if (idArl != -1) {
			AfiliadoArl afArl = new AfiliadoArl(codigoAfiliadoArl, trabajador, arl, fechaRegistroArl);
			afiliadoArlService.save(afArl);

		}
		if (idEps != -1) {
			AfiliadoEps afEps = new AfiliadoEps(codigoAfiliadoEps, trabajador, eps, fechaRegistroEps);
			afiliadoEpsService.save(afEps);
		}
		if (idAfp != -1) {
			AfiliadoFondoPension afAfp = new AfiliadoFondoPension(codigoAfiliadoAfp, trabajador, afp, fechaRegistroAfp);
			afiliadoAfpService.save(afAfp);
		}
		obreroService.save(obrero);
		flash.addFlashAttribute("success", "Obrero registrado correctamente");
		return "redirect:/obras/" + obra.getId();
	}
	 */

	@PostMapping("/update")
	public String guardarModificado(@ModelAttribute Obrero obrero, RedirectAttributes flash, Model model,
			@RequestParam(name = "id_obra", required = false) Long idObra) {
		System.out.println(idObra);
		System.out.println(obrero.getId());
		System.out.println(obrero);
		Trabajador tr = trabajadorService.findById(obrero.getTrabajador().getId());
		tr.setCedula(obrero.getTrabajador().getCedula());
		tr.setNombre(obrero.getTrabajador().getNombre());
		trabajadorService.save(tr);
		obreroService.save(obrero);
		flash.addFlashAttribute("success", "Obrero modificado correctamente");
		return "redirect:/obras/" + idObra;
	}
	@GetMapping("/create/afiliacion_arl/{idTrabajador}/{idObrero}")
	public String crearAfiliacionArl(@PathVariable Long idTrabajador,@PathVariable Long idObrero,@ModelAttribute AfiliadoArl afiliacionArl, Model model){
		AfiliadoArl af = new AfiliadoArl();
		af.setTrabajador(trabajadorService.findById(idTrabajador));
		model.addAttribute("titulo", "CREAR AFILIACION ARL");
		model.addAttribute("ruta_de_navegacion", "CREACION DE AFILIACION ARL");
		model.addAttribute("arls", arlService.findAll());
		model.addAttribute("idObrero", idObrero);
		model.addAttribute("afiliacionArl",af);
		return "/vistas/obreros/crear_afiliacion_arl";
	}

	@GetMapping("/update/afiliacion_arl/{idAfiliacion}/{idObrero}")
	public String actualizarAfiliacionArl(@PathVariable Long idAfiliacion,@PathVariable Long idObrero,@ModelAttribute AfiliadoArl afiliacionArl, Model model){
	System.out.println(idObrero);
		model.addAttribute("titulo", "MODIFICAR AFILIACION ARL");
		model.addAttribute("ruta_de_navegacion", "MODIFICACION DE AFILIACION ARL");
		model.addAttribute("arls", arlService.findAll());
		model.addAttribute("idObrero", idObrero);
		model.addAttribute("afiliacionArl", afiliadoArlService.findById(idAfiliacion));
		return "/vistas/obreros/modificar_afiliacion_arl";
	}
	@PostMapping("/update/afiliacion_arl")
	public String actualizarAfiliacionArl(@ModelAttribute AfiliadoArl afiliacionArl,RedirectAttributes flash,Model model,
	@RequestParam("idObrero") Long idObrero){
		afiliadoArlService.save(afiliacionArl);
		flash.addFlashAttribute("success", "afiliacion de arl modificada correctamente");
		return "redirect:/vistas/obreros/modificar/"+idObrero;
	}
	@PostMapping("/guardar/afiliacion_arl")
	public String guardarAfiliacionArl(@ModelAttribute AfiliadoArl afiliacionArl,RedirectAttributes flash,Model model,
	@RequestParam("idObrero") Long idObrero){
		afiliadoArlService.save(afiliacionArl);
		flash.addFlashAttribute("success", "afiliacion de arl creada correctamente");
		return "redirect:/vistas/obreros/modificar/"+idObrero;
	}

	/**
	 * @PostMapping(value = "/update/arl", produces = "application/json")
	public @ResponseBody Map<String, String> estados(@RequestParam(name = "id_afiliacion_arl") Long idAfiliacion,
			@RequestParam(name = "id_arl") Long idArl, @RequestParam(name = "codigo_afiliado") String codigoAfiliado,
			@RequestParam("fecha_ingreso") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha_ingreso) {
		HashMap<String, String> map = new HashMap<>();
		AfiliadoArl afiliacion = afiliadoArlService.findById(idAfiliacion);
		afiliacion.setArl(arlService.findById(idArl));
		afiliacion.setCodigo(codigoAfiliado);
		afiliacion.setFechaIngreso(fecha_ingreso);
		afiliadoArlService.save(afiliacion);
		AfiliadoArl test = afiliadoArlService.findById(idAfiliacion);
		if (test.getArl().getId() == idArl && test.getCodigo().equals(codigoAfiliado)) {
			map.put("transaccion", "true");
		} else {
			map.put("transaccion", "false");
		}
		return map;
	}
	 */
}
