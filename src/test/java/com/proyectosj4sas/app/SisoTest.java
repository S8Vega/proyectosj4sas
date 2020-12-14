package com.proyectosj4sas.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyectosj4sas.app.modelo.entidad.Siso;
import com.proyectosj4sas.app.modelo.servicio.implementacion.SisoServicioImpl;

@SpringBootTest
class SisoTest {

	@Autowired
	private SisoServicioImpl servicio;

	@Test
	void test() {
		Siso expected, actual;
		// test: findAll
		ArrayList<Siso> listaExpected = (ArrayList<Siso>) servicio.findAll();
		for (int i = 0; i < 10; i++) {
			expected = new Siso(null, null, null, "telefono: " + i, "correo: " + i);
			// test: save
			servicio.save(expected);
			// test: findById
			actual = servicio.findById(expected.getId());
			assertEquals(expected, actual);
			expected.setTelefono("telefono: " + (i + 1));
			expected.setCorreo("correo: " + (i + 1));
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
		ArrayList<Siso> listaActual = (ArrayList<Siso>) servicio.findAll();
		assertEquals(listaExpected, listaActual);
	}
}
