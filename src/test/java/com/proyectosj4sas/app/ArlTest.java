package com.proyectosj4sas.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyectosj4sas.app.modelo.entidad.Arl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ArlServicioImpl;

@SpringBootTest
class ArlTest {

	@Autowired
	private ArlServicioImpl servicio;

	@Test
	void test() {
		Arl expected, actual;
		// test: findAll
		ArrayList<Arl> listaExpected = (ArrayList<Arl>) servicio.findAll();
		for (int i = 0; i < 10; i++) {
			expected = new Arl(null, "nombre: " + i, "codigo: " + i);
			// test: save
			servicio.save(expected);
			// test: findById
			actual = servicio.findById(expected.getId());
			assertEquals(expected, actual);
			expected.setNombre("nombre: " + (i + 1));
			expected.setCodigo("codigo: " + (i + 1));
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
		ArrayList<Arl> listaActual = (ArrayList<Arl>) servicio.findAll();
		assertEquals(listaExpected, listaActual);
	}
}
