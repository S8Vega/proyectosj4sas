package com.proyectosj4sas.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyectosj4sas.app.modelo.entidad.Usuario;
import com.proyectosj4sas.app.modelo.servicio.implementacion.UsuarioServicioImpl;

@SpringBootTest
class UsuarioTest {

	@Autowired
	private UsuarioServicioImpl servicio;

	@Test
	void test() {
		Usuario expected, actual;
		ArrayList<Usuario> listaExpected = (ArrayList<Usuario>) servicio.findAll();
		for (int i = 0; i < 10; i++) {
			expected = new Usuario(null, "alias: " + i, "clave: " + i, "email: " + i);
			// test: save
			servicio.save(expected);
			// test: findById
			actual = servicio.findById(expected.getId());
			assertEquals(expected.getUsername(), actual.getUsername());
			assertEquals(expected.getPassword(), actual.getPassword());
			assertEquals(expected.getEmail(), actual.getEmail());
			expected.setUsername("alias: " + (i + 1));
			expected.setPassword("clave: " + (i + 1));
			expected.setEmail("email: " + (i + 1));
			// test: save
			servicio.save(expected);
			actual = servicio.findById(expected.getId());
			assertEquals(expected.getUsername(), actual.getUsername());
			assertEquals(expected.getPassword(), actual.getPassword());
			assertEquals(expected.getEmail(), actual.getEmail());
			// test: deleteById
			servicio.deleteById(expected.getId());
			actual = servicio.findById(expected.getId());
			assertNull(actual);
		}
		// test: findAll
		ArrayList<Usuario> listaActual = (ArrayList<Usuario>) servicio.findAll();
		assertEquals(listaExpected.size(), listaActual.size());
		for (int i = 0; i < listaExpected.size(); i++) {
			expected = listaExpected.get(i);
			actual = listaActual.get(i);
			assertEquals(expected.getUsername(), actual.getUsername());
			assertEquals(expected.getPassword(), actual.getPassword());
			assertEquals(expected.getEmail(), actual.getEmail());
		}
	}

}
