package net.pablo.cei.obligatorio.server.motor_reglas;

import net.pablo.cei.obligatorio.server.motor_reglas.ReglasFactory.Opcion;

public class ReglaA implements Regla {
	 
	 	private Regla siguienteRegla;
	 
	 	public ReglaA(Regla siguienteRegla) {
	 		this.siguienteRegla = siguienteRegla;
	 	}
	 
	 	public void accion(OpcionesParaJugador opcionesParaJugador, Opcion jugadaDelCliente) {
	 		System.out.println("Soy regla A");
	 		if(jugadaDelCliente.equals("a")) {
	 			opcionesParaJugador.getOpciones().add("a");
	 		}
	 		if(siguienteRegla != null) {
	 			siguienteRegla.accion(opcionesParaJugador, jugadaDelCliente);
	 		}
	 		System.out.println("Terminaron las reglas");
	 	}
	 }