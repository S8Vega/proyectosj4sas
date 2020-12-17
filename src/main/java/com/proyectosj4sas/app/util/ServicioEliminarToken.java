package com.proyectosj4sas.app.util;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.proyectosj4sas.app.modelo.dao.interfaz.PasswordResetTokenRepository;
import com.proyectosj4sas.app.modelo.entidad.PasswordResetTokenEntity;

@Service
public class ServicioEliminarToken {
	private Logger logger = LoggerFactory.getLogger(ServicioEliminarToken.class);
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;

	@Async("threadPoolExecutor")
	public CompletableFuture<String> atender(PasswordResetTokenEntity tokenEntity) {
		logger.info("eliminando token");

		ejecutarTiempoAtencion();
		logger.info("token eliminado");
		passwordResetTokenRepository.deleteById(tokenEntity.getId());
		return CompletableFuture.completedFuture("se ha eliminado el token");
	}

	public void ejecutarTiempoAtencion() {

		try {

			Thread.sleep(600000);// 10 minutos
		} catch (InterruptedException ex) {
			logger.error("Ha ocurrido un error ", ex);
		}

	}
}
