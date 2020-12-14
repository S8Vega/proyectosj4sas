package com.proyectosj4sas.app.controlador;

import com.proyectosj4sas.app.modelo.entidad.Obra;
import com.proyectosj4sas.app.modelo.entidad.Siso;
import com.proyectosj4sas.app.modelo.entidad.Trabajador;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObraServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.SisoServicioImpl;
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
@RequestMapping("/sisos")
public class SisoControlador {
    @Autowired
    public SisoServicioImpl sisoService;

    @Autowired
    public TrabajadorServicioImpl trabajadorService;

    @Autowired
    ObraServicioImpl obraService;
        @GetMapping("/registrar/{idObra}")
    public String registrar(@PathVariable Long idObra, Model model) {

        Siso representante = new Siso();
        model.addAttribute("titulo", "CREAR SISO");
        model.addAttribute("ruta_de_navegacion", "REGISTRO DE SISO");
        model.addAttribute("idObra", idObra);
        model.addAttribute("siso", representante);
        return "/vistas/siso/registrar";
    }
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Siso siso, RedirectAttributes flash,
            @RequestParam(name = "idObra", required = false) Long idObra) {
        Obra obra = obraService.findById(idObra);
        //Trabajador tr = new Trabajador();
        //tr.setCedula(siso.getTrabajador().getCedula());
        //tr.setNombre(siso.getTrabajador().getNombre());
        trabajadorService.save(siso.getTrabajador());
        sisoService.save(siso);
        obra.setSiso(siso);
       obraService.save(obra);
        flash.addFlashAttribute("success", "Siso registrado correctamente");
        return "redirect:/obras/" + idObra;
    }

    @GetMapping("/modificar/{idObra}")
    public String modificar(@PathVariable Long idObra, Model model) {
        Obra ob = obraService.findById(idObra);
        Siso siso =sisoService.findById(ob.getSiso().getId());
        System.out.println(siso.getTrabajador().getId());
        siso.setTrabajador(trabajadorService.findById(siso.getTrabajador().getId()));
        model.addAttribute("titulo", "MODIFICAR SISO");
        model.addAttribute("ruta_de_navegacion", "MODIFICACION DE SISO");
        model.addAttribute("idObra", idObra);
        model.addAttribute("siso", siso);
        return "/vistas/siso/modificar";
    }
    @PostMapping("/guardar_modificado")
    public String guardarModificado(@ModelAttribute Siso siso, RedirectAttributes flash,
            @RequestParam(name = "idObra", required = false) Long idObra) {
                trabajadorService.save(siso.getTrabajador());
        sisoService.save(siso);
        flash.addFlashAttribute("success", "Siso modificado correctamente");
        return "redirect:/obras/" + idObra;
    }

    @PostMapping("/eliminar/{idObra}")
    public String eliminar(@PathVariable Long idObra,Model model,RedirectAttributes flash) {
        System.out.println("eliminando"+idObra);
        Obra obra = obraService.findById(idObra);
        Siso r = obra.getSiso();
        obra.setSiso(null);
        obraService.save(obra);

        
       Trabajador tr = r.getTrabajador();
        r.setTrabajador(null);
        sisoService.deleteById(r.getId());
        trabajadorService.deleteById(tr.getId());
        
        flash.addFlashAttribute("success", "Siso eliminado correctamente");
        return "redirect:/obras/" + idObra;
    }
}
