package com.proyectosj4sas.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyectosj4sas.app.modelo.entidad.Obrero;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObreroServicioImpl;

@SpringBootTest
class ObreroTest {

	@Autowired
	private ObreroServicioImpl servicio;

	@Test
	void test() {
		Obrero expected, actual;
		// test: findAll
		ArrayList<Obrero> listaExpected = (ArrayList<Obrero>) servicio.findAll();
		for (int i = 0; i < 10; i++) {
			expected = new Obrero(null, null, "estado: " + i, null, "cargo: " + i, null, null);
			// test: save
			servicio.save(expected);
			// test: findById
			actual = servicio.findById(expected.getId());
			assertEquals(expected, actual);
			expected.setEstado("estado: " + (i + 1));
			expected.setCargo("cargo: " + (i + 1));
			// test: save
			servicio.save(expected);
			// test: findById
			actual = servicio.findById(expected.getId());
			assertEquals(expected, actual);
			// test: deleteById
			servicio.deleteById(expected.getId());
			// test: findById
			actual = servicio.findById(expected.getId());
			assertNull(actual);
		}
		// test: findAll
		ArrayList<Obrero> listaActual = (ArrayList<Obrero>) servicio.findAll();
		assertEquals(listaExpected, listaActual);
	}
}
