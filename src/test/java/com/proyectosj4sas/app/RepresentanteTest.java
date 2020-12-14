package com.proyectosj4sas.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyectosj4sas.app.modelo.entidad.Representante;
import com.proyectosj4sas.app.modelo.servicio.implementacion.RepresentanteServicioImpl;

@SpringBootTest
class RepresentanteTest {

	@Autowired
	private RepresentanteServicioImpl servicio;

	@Test
	void test() {
		Representante expected, actual;
		// test: findAll
		ArrayList<Representante> listaExpected = (ArrayList<Representante>) servicio.findAll();
		for (int i = 0; i < 10; i++) {
			expected = new Representante(null, null, "telefono: " + i, "correo: " + i, "nombre: " + i);
			// test: save
			servicio.save(expected);
			// test: findById
			actual = servicio.findById(expected.getId());
			assertEquals(expected, actual);
			expected.setTelefono("telefono: " + (i + 1));
			expected.setCorreo("correo: " + (i + 1));
			expected.setNombre("nombre: " + (i + 1));
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
		ArrayList<Representante> listaActual = (ArrayList<Representante>) servicio.findAll();
		assertEquals(listaExpected, listaActual);
	}

}
