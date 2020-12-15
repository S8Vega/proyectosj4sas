package com.proyectosj4sas.app.util;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Utils {

	public String generatePasswordResetToken(String idUsuario) {
		String token = Jwts.builder().setSubject(idUsuario)
				.setExpiration(new Date(
						System.currentTimeMillis() + com.proyectosj4sas.app.security.SecurityConstants.PASSWORD_RESET_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, com.proyectosj4sas.app.security.SecurityConstants.getTokenSecret())
				.compact();
		return token;
	}
}
