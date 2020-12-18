package com.proyectosj4sas.app.modelo.servicio.implementacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectosj4sas.app.modelo.dao.interfaz.IUsuarioDao;
import com.proyectosj4sas.app.modelo.dao.interfaz.PasswordResetTokenRepository;
import com.proyectosj4sas.app.modelo.entidad.PasswordResetTokenEntity;
import com.proyectosj4sas.app.modelo.entidad.Role;
import com.proyectosj4sas.app.modelo.entidad.Usuario;
import com.proyectosj4sas.app.modelo.servicio.interfaz.IServicio;
import com.proyectosj4sas.app.util.EmailBody;
import com.proyectosj4sas.app.util.EmailPort;
import com.proyectosj4sas.app.util.SecureTokenGenerator;
import com.proyectosj4sas.app.util.ServicioEliminarToken;

@Service
public class UsuarioServicioImpl implements IServicio<Usuario, Long>, UserDetailsService {

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	private String TEXT_BODY = "\n" + "<body style=\"margin: 0; padding: 0;\">\n"
			+ "	<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
			+ "		<tr>\n" + "			<td style=\"padding: 20px 0 0px 0;\">\n" + "\n"
			+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse; border: 1px solid #cccccc;\">\n"
			+ "	<tr>\n" + "		<td align=\"center\" bgcolor=\"#70bbd9\" style=\"padding: 40px 0 30px 0;\">\n"
			+ "			<img src=\"https://i.ibb.co/sgK8wBC/isotipo.png\" alt=\"Creating Email Magic.\" width=\"300\" height=\"230\" style=\"display: block;\" />\n"
			+ "		</td>\n" + "	</tr>\n" + "	<tr>\n"
			+ "		<td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n"
			+ "			<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;\">\n"
			+ "				<tr>\n"
			+ "					<td style=\"color: #153643; font-family: Arial, sans-serif;\">\n"
			+ "						<h1 style=\"font-size: 24px; margin: 0;\">PROJECTOS E INGENIERIA J4 S.A.S</h1>\n"
			+ "					</td>\n" + "				</tr>\n" + "			\n"
			+ "                    <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 20px 0 30px 0;\">\n"
			+ "                        <h3 style=\"font-size: 16px; margin: 0;\">Solicitud de cambio de clave:</h3>\n"
			+ "                        <p style=\"margin: 0;\">\n" + "                            Hola, $username"
			+ "                                  !\n"
			+ "                            Alguien ha solicitado restablecer su clave de acceso a  nuestro sitio web. Si no fue usted, ignórelo. de lo contrario, haga click en el enlace de abajo para establecer una nueva clave:\n"

			+ "    <a href=\"http://$host_to_deploy:8080/proyectosj4sas/users/password-update-request/$tokenValue\">Click Aqui</a>\n"
			+ "\n" + "                            Haga clic en este enlace para restablecer la clave.\n"
			+ "                            Gracias\n" + "                        </p>\n" + "					</td>\n"
			+ "				</tr>\n" + "			</table>\n" + "		</td>\n" + "	</tr>\n" + "	<tr>\n"
			+ "		<td bgcolor=\"#36A4C6\" style=\"padding: 30px 30px;\">\n"
			+ "    		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;\">\n"
			+ "				<tr>\n"
			+ "					<td style=\"color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;\">\n"
			+ "						<p style=\"margin: 0;\">&reg; Hudsoft, Norte de Santander 2020<br/>\n"
			+ "					</td>\n" + "					<td align=\"right\">\n"
			+ "						<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse;\">\n"
			+ "							<tr>\n" + "								<td>\n"
			+ "									<a href=\"#\">\n"
			+ "										<img src=\"https://ww2.ufps.edu.co/public/archivos/elementos_corporativos/logoufps.png\" alt=\"UFPS.\" width=\"38\" height=\"38\" style=\"display: block;\" border=\"0\" />\n"
			+ "									</a>\n" + "								</td>\n"
			+ "								<td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp;</td>\n"
			+ "								<td>\n" + "									<a href=\"#\">\n"
			+ "										<img src=\"https://i.ibb.co/vqjpkSZ/hudsoft.png\" alt=\"HUDSOFT.\" width=\"38\" height=\"38\" style=\"display: block;\" border=\"0\" />\n"
			+ "									</a>\n" + "								</td>\n"
			+ "							</tr>\n" + "						</table>\n" + "					</td>\n"
			+ "				</tr>\n" + "			</table>\n" + "		</td>\n" + "	</tr>\n" + "</table>\n" + "\n"
			+ "			</td>\n" + "		</tr>\n" + "	</table>\n" + "</body>";
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

		String token = SecureTokenGenerator.nextToken();

		boolean returnValue = false;
		Usuario usuario = usuarioDao.findByEmail(email);
		if (usuario == null) {
			return returnValue;
		} else {
			PasswordResetTokenEntity tokenEntity = new PasswordResetTokenEntity();
			tokenEntity.setToken(token);
			tokenEntity.setUsuario(usuario);

			passwordResetTokenRepository.save(tokenEntity);
			CompletableFuture<String> atencion1 = servicioEliminarToken
					.atender(passwordResetTokenRepository.findByToken(token));
			CompletableFuture.completedFuture(atencion1);

			EmailBody emailBody = new EmailBody();

			emailBody.setEmail(usuario.getEmail());
			emailBody.setSubject("hudsoft solicitud de cambio de clave");

			String content = TEXT_BODY.replace("$tokenValue", token);
			content = content.replace("$username", usuario.getUsername());
			content = content.replace("$host_to_deploy", "ec2-54-221-64-103.compute-1.amazonaws.com");
			// content=content.replace("$host_to_deploy", "localhost");
			emailBody.setContent(content);
			return emailPort.sendEmail(emailBody);
		}

	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role rol : usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(rol.getAuthority()));
		}
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnable(), true, true, true,
				authorities);
	}

}
