package com.proyectosj4sas.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proyectosj4sas.app.auth.handler.LoginSuccessHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccessHandler successHandler;
	
	
	  @Bean public BCryptPasswordEncoder passwordEncoder() { return new
	  BCryptPasswordEncoder(); } 

	
	  @Override protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests().antMatchers("/css/**", "/js/**",
	  "/img/**").permitAll().antMatchers("/","/empresas","/constructoras/obras/{id}")
	  .hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin().successHandler(successHandler).loginPage
	  ("/login").permitAll()
	  .and().logout().permitAll().and().exceptionHandling().accessDeniedPage(
	  "/error_403"); }
	 


	  @Autowired
	  public void ConfigurerGlobal(AuthenticationManagerBuilder builder) throws
	  Exception { PasswordEncoder encoder = passwordEncoder(); UserBuilder users =
	  User.builder().passwordEncoder(password -> { return encoder.encode(password);
	  });
	  
	  builder.inMemoryAuthentication()
	  .withUser(users.username("adminGloria").password("adminBotello123").roles(
	  "ADMIN")); }
	 
	 

}
