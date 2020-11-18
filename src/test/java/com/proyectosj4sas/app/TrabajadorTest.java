package com.proyectosj4sas.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyectosj4sas.app.modelo.entidad.Trabajador;
import com.proyectosj4sas.app.modelo.servicio.implementacion.TrabajadorServicioImpl;

@SpringBootTest
class TrabajadorTest {

	@Autowired
	private TrabajadorServicioImpl servicio;

	@Test
	void test() {
		Trabajador expected = new Trabajador();
		Trabajador actual = new Trabajador();
		for (int i = 0; i < 10; i++) {
			expected = new Trabajador(null, null, null, null, null, null, "nombre: " + i, "cedula: " + i);
			// test: save
			servicio.save(expected);
			// test: findById
			actual = servicio.findById(expected.getId());
			assertEquals(expected.getNombre(), actual.getNombre());
			assertEquals(expected.getCedula(), actual.getCedula());
			expected.setNombre("nombre: " + (i + 1));
			expected.setCedula("cedula: " + (i + 1));
			// test: save
			servicio.save(expected);
			// test: findById
			actual = servicio.findById(expected.getId());
			assertEquals(expected.getNombre(), actual.getNombre());
			assertEquals(expected.getCedula(), actual.getCedula());
			// test: deleteById
			servicio.deleteById(expected.getId());
			actual = servicio.findById(expected.getId());
			assertNull(actual);
		}
	}

}
