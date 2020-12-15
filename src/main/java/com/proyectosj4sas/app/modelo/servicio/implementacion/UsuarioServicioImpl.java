package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IUsuarioDao;
import com.proyectosj4sas.app.modelo.dao.interfaz.PasswordResetTokenRepository;
import com.proyectosj4sas.app.modelo.entidad.PasswordResetTokenEntity;
import com.proyectosj4sas.app.modelo.entidad.Usuario;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;
import com.proyectosj4sas.app.util.EmailBody;
import com.proyectosj4sas.app.util.EmailPort;
import com.proyectosj4sas.app.util.HudsoftSES;
import com.proyectosj4sas.app.util.ServicioEliminarToken;
import com.proyectosj4sas.app.util.Utils;

@Service
public class UsuarioServicioImpl implements IServicio<Usuario, Long> {
	@Autowired
	private EmailPort emailPort;
	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	ServicioEliminarToken servicioEliminarToken;

	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;
 
	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario t) {
		usuarioDao.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		usuarioDao.deleteById(id);
	}
	
	public boolean requestPasswordReset(String email) {
		
		 SecureRandom random = new SecureRandom(); 
		 byte bytes[] = new byte[20]; 
		 random.nextBytes(bytes); 
		 String token = bytes.toString(); 

		 
		
		
		boolean returnValue = false;
		Usuario usuario = usuarioDao.findByEmail(email);	//verifico si existe el usuario
		if(usuario == null) {
			System.out.println("no existe");
			return returnValue;
		}else {
			PasswordResetTokenEntity tokenEntity = new PasswordResetTokenEntity();
			 tokenEntity.setToken(token);
			 tokenEntity.setUsuario(usuario);
			 passwordResetTokenRepository.save(tokenEntity);
			 CompletableFuture<String> atencion1 = servicioEliminarToken.atender(passwordResetTokenRepository.findByToken(token));
			 CompletableFuture.completedFuture(atencion1);
			 
			 //ServicioEliminarToken sEliminar = new ServicioEliminarToken();
			 //sEliminar.atender(passwordResetTokenRepository.findByToken(tokenEntity.getToken()));
			System.out.println("si existe");
			EmailBody emailBody = new EmailBody();
			emailBody.setEmail(email);
			emailBody.setSubject("hudsoft solicitud de cambio de clave");
			emailBody.setContent("<div style=\"background-color:cyan\">\n"
					+ "  <h4 style=\"color:orange\">\n"
					+ "    Hola Usuario!\n"
					+ "  </h4>\n"
					+ "  <p>\n"
					+ "    un saludo <a href=\"http://localhost:8080/users/password-update-request/"+token+"\">link</a>\n"
					+ "  </p>\n"
					+ "</div>");
			boolean status = emailPort.sendEmail(emailBody);
			if(status) {
				System.out.println("enviado");
				return true;
			}else {
				System.out.println("no se pudo enviar");
			}
		}return false;
	}

}
