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
		Trabajador trabajador = new Trabajador(null, null, null, null, null, null, "hola", "mundo");
		this.servicio.save(trabajador);
		ArrayList<Trabajador> lista = (ArrayList<Trabajador>) servicio.findAll();
		for (Trabajador t: lista) {
			System.out.println(t.toString());
		}
	}

}
