package com.proyectosj4sas.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyectosj4sas.app.modelo.entidad.Empresa;
import com.proyectosj4sas.app.modelo.servicio.implementacion.EmpresaServicioImpl;

@SpringBootTest
class EmpresaTest {

	@Autowired
	private EmpresaServicioImpl servicio;

	@Test
	void test() {
		Empresa expected, actual;
		ArrayList<Empresa> listaExpected = (ArrayList<Empresa>) servicio.findAll();
		for (int i = 0; i < 10; i++) {
			expected = new Empresa(null, "nombre: " + i, "nit: " + i, "direccion: " + i, "telefono: " + i, null, null);
			// test: save
			servicio.save(expected);
			// test: findById
			actual = servicio.findById(expected.getId());
			assertEquals(expected, actual);
			expected.setNombre("nombre: " + (i + 1));
			expected.setNit("nit: " + (i + 1));
			expected.setDireccion("direccion: " + (i + 1));
			expected.setTelefono("telefono: " + (i + 1));
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
		ArrayList<Empresa> listaActual = (ArrayList<Empresa>) servicio.findAll();
		assertEquals(listaExpected, listaActual);
	}
}
