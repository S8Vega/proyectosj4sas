package com.proyectosj4sas.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyectosj4sas.app.modelo.entidad.Contador;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ContadorServicioImpl;

@SpringBootTest
class ContadorTest {

	@Autowired
	private ContadorServicioImpl servicio;

	@Test
	void test() {
		Contador expected, actual;
		ArrayList<Contador> listaExpected = (ArrayList<Contador>) servicio.findAll();
		for (int i = 0; i < 10; i++) {
			expected = new Contador(null, null, "telefono: " + i, "correo: " + i, "nombre: " + i);
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
		ArrayList<Contador> listaActual = (ArrayList<Contador>) servicio.findAll();
	}
}
