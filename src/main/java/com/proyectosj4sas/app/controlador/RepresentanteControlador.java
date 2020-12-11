package com.proyectosj4sas.app.controlador;

import com.proyectosj4sas.app.modelo.entidad.Obra;
import com.proyectosj4sas.app.modelo.entidad.Representante;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObraServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.RepresentanteServicioImpl;

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
@RequestMapping("/representantes")
public class RepresentanteControlador {

    @Autowired
    private RepresentanteServicioImpl representanteService;
    @Autowired ObraServicioImpl obraService;

    @GetMapping("/registrar/{idObra}")
    public String registrar(@PathVariable Long idObra, Model model){
        Representante representante = new Representante();
		model.addAttribute("titulo", "CREAR Representante");
        model.addAttribute("ruta_de_navegacion", "REGISTRO DE REPRESENTANTE");
        model.addAttribute("idObra", idObra);
		model.addAttribute("representante", representante);
        return "/vistas/representante/registrar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Representante representante, RedirectAttributes flash,@RequestParam(name = "idObra", required = false) Long idObra){
        Obra obra = obraService.findById(idObra);
        representanteService.save(representante);
        obra.setRepresentante(representante);
		
		flash.addFlashAttribute("success", "Representante registrado correctamente");
		return "redirect:/obras/" + idObra;
    }
}
