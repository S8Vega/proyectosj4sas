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
		Trabajador t1= new Trabajador(null, null, null, null, null, null, "hola", "mundo");
		servicio.save(t1);
		ArrayList<Trabajador> lista = (ArrayList<Trabajador>) servicio.findAll();
		Trabajador t2 = lista.get(lista.size() - 1);
		assertEquals(t1.getNombre(), t2.getNombre());
		assertEquals(t1.getCedula(), t2.getCedula());
	}

}
