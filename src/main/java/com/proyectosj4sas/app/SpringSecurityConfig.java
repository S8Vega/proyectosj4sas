package com.proyectosj4sas.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.proyectosj4sas.app.auth.handler.LoginSuccessHandler;
import com.proyectosj4sas.app.modelo.servicio.implementacion.UsuarioServicioImpl;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccessHandler successHandler;
	@Autowired
	public UsuarioServicioImpl userService;
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/img/**", "/users/password-reset-request/**",
						"/users/password-update-request/**")
				.permitAll()
				.antMatchers("/", "/arl/**", "/empresas/**", "/empresas/{id}", "/constructoras/obras/{id}",
						"/views/constructoras/listar/**", "/obras/estados/**", "/vistas/obreros/form")
				.authenticated().and().formLogin().successHandler(successHandler).loginPage("/login").permitAll().and()
				.logout().permitAll().and().exceptionHandling().accessDeniedPage("/error_403");
	}

	@Autowired
	public void ConfigurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

}
