package com.proyectosj4sas.app.controlador;

import com.proyectosj4sas.app.modelo.entidad.Contador;
import com.proyectosj4sas.app.modelo.entidad.Empresa;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ContadorServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.EmpresaServicioImpl;

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
@RequestMapping("/contador")
public class ContadorControlador {

    @Autowired
    private ContadorServicioImpl contadorService;
    @Autowired
private EmpresaServicioImpl empresaService;


    @GetMapping("crear/{idEmpresa}")
    public String agregar(@PathVariable Long idEmpresa, Model model){
        Contador contador = new Contador();
		model.addAttribute("titulo", "CREAR Contador");
        model.addAttribute("ruta_de_navegacion", "REGISTRO DE CONTADOR");
        model.addAttribute("idEmpresa", idEmpresa);
		model.addAttribute("contador", contador);
        return "/vistas/contador/registrar";
    }
    @GetMapping("modificar/{idEmpresa}")
    public String modificar(@PathVariable Long idEmpresa, Model model){
        Empresa empresa = empresaService.findById(idEmpresa);
        
        Contador contador = empresa.getContador();
		model.addAttribute("titulo", "CREAR Contador");
        model.addAttribute("ruta_de_navegacion", "REGISTRO DE CONTADOR");
        model.addAttribute("idEmpresa", idEmpresa);
		model.addAttribute("contador", contador);
        return "/vistas/contador/modificar";
    }
    @PostMapping("/guardar_modificacion")
    public String guardarModificado(@ModelAttribute Contador contador,
    @RequestParam(name = "idEmpresa", required = false) Long idEmpresa,
     RedirectAttributes flash, Model model) {
        contadorService.save(contador);
		flash.addFlashAttribute("success", "Contador modificado correctamente");
		return "redirect:/empresas/" + idEmpresa;
	}

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Contador contador,
    @RequestParam(name = "idEmpresa", required = false) Long idEmpresa,
     RedirectAttributes flash, Model model) {
        contador.setEmpresa(empresaService.findById(idEmpresa));
		contadorService.save(contador);
		flash.addFlashAttribute("success", "Contador registrado correctamente");
		return "redirect:/empresas/" + contador.getEmpresa().getId();
	}
    
}
