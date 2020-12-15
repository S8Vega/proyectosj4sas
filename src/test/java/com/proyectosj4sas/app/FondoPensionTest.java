package com.proyectosj4sas.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyectosj4sas.app.modelo.entidad.FondoPension;
import com.proyectosj4sas.app.modelo.servicio.implementacion.FondoPensionServicioImpl;

@SpringBootTest
class FondoPensionTest {

	@Autowired
	private FondoPensionServicioImpl servicio;

	@Test
	void test() {
		FondoPension expected, actual;
		// test: findAll
		ArrayList<FondoPension> listaExpected = (ArrayList<FondoPension>) servicio.findAll();
		for (int i = 0; i < 10; i++) {
			expected = new FondoPension(null, "nombre: " + i, "codigo: " + i, null);
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
		ArrayList<FondoPension> listaActual = (ArrayList<FondoPension>) servicio.findAll();
		assertEquals(listaExpected, listaActual);
	}
}
