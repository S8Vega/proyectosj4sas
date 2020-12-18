package com.proyectosj4sas.app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectosj4sas.app.modelo.dao.interfaz.PasswordResetTokenRepository;
import com.proyectosj4sas.app.modelo.entidad.PasswordResetTokenEntity;
import com.proyectosj4sas.app.modelo.entidad.Usuario;
import com.proyectosj4sas.app.modelo.servicio.implementacion.UsuarioServicioImpl;
import com.proyectosj4sas.app.util.PasswordResetRequestModel;
import com.proyectosj4sas.app.util.ServicioEliminarToken;

@Controller
@RequestMapping("/users")
public class UsuarioControlador {

	@Autowired
	ServicioEliminarToken servicioEliminarToken;

	@Autowired
	public UsuarioServicioImpl userService;

	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/password-reset-request")
	public String requestReset(@ModelAttribute PasswordResetRequestModel passwordResetRequestModel, Model model,
			RedirectAttributes flash) {
		boolean status = userService.requestPasswordReset(passwordResetRequestModel.getEmail());

		if (status) {
			model.addAttribute("msg", "Se ha enviado un email con un enlace para efectuar tu cambio de clave!");
		} else {
			model.addAttribute("msg", "El email proporcionado no se encuentra registrado!");
		}

		return "msg";

	}

	@GetMapping("/password-reset-request")
	public String changePasword(Model model, RedirectAttributes flash) {
		model.addAttribute("password_reset_request", new PasswordResetRequestModel());
		flash.addFlashAttribute("info", "Ingresa tus datos para actualizar tu clave de acceso!");
		model.addAttribute("msg", "Ingresa un email para enviarte un enlace para el reset de clave!");
		return "reset_password";
	}

	@GetMapping("/password-update-request/{token}")
	public String updatePasword(@PathVariable String token, Model model, RedirectAttributes flash) {
		flash.addFlashAttribute("info", "Ingresa tus datos para actualizar tu clave de acceso!");
		model.addAttribute("msg", "Ingresa tu nueva clave!");
		model.addAttribute("token", token);
		return "update_password";
	}

	@PostMapping("/password-update-request")
	public String requestReset(Model model, @RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "password_confirm", required = true) String password_confirm,
			@RequestParam(name = "token", required = true) String token, RedirectAttributes flash) {

		if (password.equals(password_confirm)) {
			PasswordResetTokenEntity tokenEntity = passwordResetTokenRepository.findByToken(token);
			if (tokenEntity == null) {
				model.addAttribute("msg", "El tiempo ha expirado!");
				return "msg";
			}

			Usuario u = userService.findById(tokenEntity.getUsuario().getId());
			u.setPassword(passwordEncoder.encode(password));
			userService.save(u);
			passwordResetTokenRepository.deleteById(tokenEntity.getId());
		} else {
			model.addAttribute("msg", "Las claves no coincidieron!");
			return "msg";
		}

		model.addAttribute("info", "Las claves se han cambiado correctamente!");
		return "login";

	}

}
