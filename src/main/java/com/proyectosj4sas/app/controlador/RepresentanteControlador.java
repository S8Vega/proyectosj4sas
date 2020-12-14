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
    @Autowired
    ObraServicioImpl obraService;

    @GetMapping("/registrar/{idObra}")
    public String registrar(@PathVariable Long idObra, Model model) {

        Representante representante = new Representante();
        model.addAttribute("titulo", "CREAR Representante");
        model.addAttribute("ruta_de_navegacion", "REGISTRO DE REPRESENTANTE");
        model.addAttribute("idObra", idObra);
        model.addAttribute("representante", representante);
        return "/vistas/representante/registrar";
    }

    @GetMapping("/modificar/{idObra}")
    public String modificar(@PathVariable Long idObra, Model model) {
        Representante representante = obraService.findById(idObra).getRepresentante();
        model.addAttribute("titulo", "MODIFICAR REPRESENTANTE");
        model.addAttribute("ruta_de_navegacion", "MODIFICACION DE REPRESENTANTE");
        model.addAttribute("idObra", idObra);
        model.addAttribute("representante", representante);
        return "/vistas/representante/modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Representante representante, RedirectAttributes flash,
            @RequestParam(name = "idObra", required = false) Long idObra) {
        Obra obra = obraService.findById(idObra);
        representanteService.save(representante);
        obra.setRepresentante(representante);
        obraService.save(obra);
        flash.addFlashAttribute("success", "Representante registrado correctamente");
        return "redirect:/obras/" + idObra;
    }

    @PostMapping("/guardar_modificado")
    public String guardarModificado(@ModelAttribute Representante representante, RedirectAttributes flash,
            @RequestParam(name = "idObra", required = false) Long idObra) {
        representanteService.save(representante);
        flash.addFlashAttribute("success", "Representante modificado correctamente");
        return "redirect:/obras/" + idObra;
    }

    @PostMapping("/eliminar/{idObra}")
    public String eliminar(@PathVariable Long idObra,Model model,RedirectAttributes flash) {
        System.out.println("eliminando"+idObra);
        Obra obra = obraService.findById(idObra);
        Representante r = obra.getRepresentante();
        obra.setRepresentante(null);
        obraService.save(obra);
        
        representanteService.deleteById(r.getId());
        flash.addFlashAttribute("success", "Representante eliminado correctamente");
        return "redirect:/obras/" + idObra;
    }
}
