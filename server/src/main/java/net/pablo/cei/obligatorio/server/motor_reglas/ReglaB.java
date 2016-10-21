package net.pablo.cei.obligatorio.server.motor_reglas;

import net.pablo.cei.obligatorio.server.motor_reglas.ReglasFactory.Opcion;

public class ReglaB implements Regla {
	 
	 	public void accion(OpcionesParaJugador opcionesParaJugador, Opcion jugadaDelCliente) {
	 		System.out.println("soy la ultima regla con el value: " + jugadaDelCliente);
	 	}
	 }
