package com.proyectosj4sas.app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectosj4sas.app.modelo.dao.interfaz.PasswordResetTokenRepository;
import com.proyectosj4sas.app.modelo.entidad.PasswordResetTokenEntity;
import com.proyectosj4sas.app.modelo.entidad.Usuario;
import com.proyectosj4sas.app.modelo.servicio.implementacion.UsuarioServicioImpl;
import com.proyectosj4sas.app.util.EmailBody;
import com.proyectosj4sas.app.util.EmailPort;
import com.proyectosj4sas.app.util.OperationStatusModel;
import com.proyectosj4sas.app.util.PasswordResetRequestModel;
import com.proyectosj4sas.app.util.RequestOperationName;
import com.proyectosj4sas.app.util.RequestOperationStatus;
import com.proyectosj4sas.app.util.ServicioEliminarToken;

@Controller
@RequestMapping("/users")
public class UserControler {
	@Autowired
	ServicioEliminarToken servicioEliminarToken;
	@Autowired
	public UsuarioServicioImpl userService;
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;

	@PostMapping("/password-reset-request")
	public String requestReset(@ModelAttribute PasswordResetRequestModel passwordResetRequestModel, Model model,
			RedirectAttributes flash) {
		boolean status = userService.requestPasswordReset(passwordResetRequestModel.getEmail());

		if (status) {
			model.addAttribute("msg", "se ha enviado un email con un enlace para efectuar tu cambio de clave!");
			return "msg";
		}
		model.addAttribute("msg", "el email no se encuemtra registrado!");
		return "msg";

	}

	@GetMapping("/password-reset-request")
	public String changePasword(Model model, RedirectAttributes flash) {
		model.addAttribute("password_reset_request", new PasswordResetRequestModel());
		flash.addFlashAttribute("info", "ingresa tus datos para actualizar tu clave de acceso!");
		model.addAttribute("msg", "ingresa email para enviarte un enlace de cambio de clave!");
		return "reset_password";
	}

	@GetMapping("/password-update-request/{token}")
	public String updatePasword(@PathVariable String token, Model model, RedirectAttributes flash) {
		System.out.println(token + "mytoken");
		// servicioEliminarToken.atender(passwordResetTokenRepository.findByToken(token));
		flash.addFlashAttribute("info", "ingresa tus datos para actualizar tu clave de acceso!");
		model.addAttribute("msg", "ingresa tu nueva clave!");
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
				model.addAttribute("msg", "el tiempo ha expirado!");
				return "msg";
			}
			System.out.println(tokenEntity);
			Usuario usuario = userService.findById(tokenEntity.getUsuario().getId());
			System.out.println("id de my usuario");
			System.out.println(usuario.getEmail());
			usuario.setClave(password);
			userService.save(usuario);
			passwordResetTokenRepository.deleteById(tokenEntity.getId());
		} else {
			model.addAttribute("msg", "las claves no coincidieron!");
			return "msg";
		}

		model.addAttribute("info", "las claves se han cambiado correctamente!");
		return "login";

	}

}
