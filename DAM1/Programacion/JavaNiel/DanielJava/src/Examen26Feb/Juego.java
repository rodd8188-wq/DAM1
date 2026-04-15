package Examen26Feb;

import java.util.ArrayList;
import java.util.HashMap;

public class Juego {
	private int jugadoresActivos = Jugador.listaJugadores.size();
	private int numPruebasJugadas = 0;
	private int numParticipantes;
	public Juego(int numParti) {
		numParticipantes=numParti;
		for(int i=1;i<=getNumParticipantes();i++) {
			new Jugador();
		}
	}
	public int getNumParticipantes() {
		return this.numParticipantes;
	}
	public void verJugadores() {
		System.out.println("Número de pruebas Jugadas: "+numPruebasJugadas);
		System.out.println("Número de Jugadores Ativos: "+jugadoresActivos);
		for(int i=0;i<=74;i++)
			System.out.print("-");
		System.out.println();
		int columna = 0;
		for(Jugador j:Jugador.listaJugadores) {
			if(j.getActivo()==false) {
				System.out.print("   ---");
				columna++;
			} else {
				System.out.printf("   %03d",j.getNumJugador());
				columna++;
			}
			if (columna>=12){
				System.out.println();
				columna=0;
			}
		}
		for(int i=0;i<=74;i++)
			System.out.print("-");
		System.out.println();
	}
	public void nuevaPrueba(int expulsar) {
		Prueba.pruebasRealizadas.put(this.numPruebasJugadas,expulsar);
		this.numPruebasJugadas++;
		System.out.println("Empieza la prueba número: "+numPruebasJugadas);
		if(expulsar<0)
			System.out.println("La prueba no puede expulsar a un número negativo de jugadores");
		else if(expulsar==1)
			System.out.println("Vamos a expulsar a "+expulsar+" jugador");
		else
			System.out.println("Vamos a expulsar a "+expulsar+" jugadores");
		this.eliminarJugadores(expulsar);
		
	}
	public void eliminarJugadores(int expulsar) {
		this.jugadoresActivos=numParticipantes-expulsar;
		int maxJugadores=Jugador.listaJugadoresActivos.size();
		for(int i=0;i<expulsar;i++) {
			int jugadorAzar = (int)(Math.random()*(maxJugadores))+1;
			Jugador j = Jugador.listaJugadoresActivos.get(jugadorAzar);
			j.eliminarJugador();
			maxJugadores--;
		}
	}
	public int numJugadoresRestantes() {		
		return Jugador.listaJugadoresActivos.size();
	}
}
