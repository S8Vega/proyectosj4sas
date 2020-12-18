package com.proyectosj4sas.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.proyectosj4sas.app.modelo.entidad.Usuario;
import com.proyectosj4sas.app.modelo.servicio.implementacion.UsuarioServicioImpl;

@SpringBootApplication
@EnableAsync
public class Proyectosj4sasApplication implements CommandLineRunner {
	@Autowired
	public UsuarioServicioImpl userService;
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Bean("threadPoolExecutor")
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("Async-");
		executor.initialize();
		return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(Proyectosj4sasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	  
//		  Usuario u = new Usuario(); 
//		  u.setEmail("asimplemailmore@gmail.com");
//		  u.setPassword(passwordEncoder.encode("amanecer")); 
//		  u.setUsername("antares");
//		  u.setEnable(true);		  
//		  
//		  userService.save(u);

	}
}
