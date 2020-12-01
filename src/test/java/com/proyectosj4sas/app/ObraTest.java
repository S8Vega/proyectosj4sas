package com.proyectosj4sas.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyectosj4sas.app.modelo.entidad.Obra;
import com.proyectosj4sas.app.modelo.servicio.implementacion.EmpresaServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.ObraServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.RepresentanteServicioImpl;
import com.proyectosj4sas.app.modelo.servicio.implementacion.SisoServicioImpl;

@SpringBootTest
class ObraTest {

	@Autowired
	private ObraServicioImpl servicio;
	@Autowired
	private RepresentanteServicioImpl representanteServicio;
	@Autowired
	private SisoServicioImpl sisoServicio;
	@Autowired
	private EmpresaServicioImpl empresaServicio;

	@Test
	void test() {
		Obra obraExpected, obraActual;
		ArrayList<Obra> listaExpected = (ArrayList<Obra>) servicio.findAll();
		for (int i = 0; i < 10; i++) {
			obraExpected = new Obra(null, null, representanteServicio.findById((long) (i % 10 + 1)),
					sisoServicio.findById((long) (i % 10 + 1)), "activo", empresaServicio.findById((long) (i % 5 + 1)),
					"nombre: " + i, "direccion: " + i, null, null);
			// test: save
			servicio.save(obraExpected);
			obraActual = servicio.findById(obraExpected.getId());
			assertEquals(obraExpected.getNombre(), obraActual.getNombre());
			assertEquals(obraExpected.getDireccion(), obraActual.getDireccion());
			obraExpected.setNombre("nombre: " + (i + 1));
			obraExpected.setDireccion("direccion: " + (i + 1));
			// test: save
			servicio.save(obraExpected);
			obraActual = servicio.findById(obraExpected.getId());
			assertEquals(obraExpected.getNombre(), obraActual.getNombre());
			assertEquals(obraExpected.getDireccion(), obraActual.getDireccion());
			// test: deleteById
			servicio.deleteById(obraExpected.getId());
			obraActual = servicio.findById(obraExpected.getId());
			assertNull(obraActual);
		}
		// test: findAll
		ArrayList<Obra> listaActual = (ArrayList<Obra>) servicio.findAll();
		assertEquals(listaExpected.size(), listaActual.size());
		for (int i = 0; i < listaExpected.size(); i++) {
			obraExpected = listaExpected.get(i);
			obraActual = listaActual.get(i);
			assertEquals(obraExpected.getNombre(), obraActual.getNombre());
			assertEquals(obraExpected.getDireccion(), obraActual.getDireccion());
		}
	}

}
