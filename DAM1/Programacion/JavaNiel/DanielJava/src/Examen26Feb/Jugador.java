package Examen26Feb;

import java.util.ArrayList;

public class Jugador {
	static ArrayList<Jugador> listaJugadores = new ArrayList<>();
	static ArrayList<Jugador> listaJugadoresActivos = new ArrayList<>();
	static int num = 1;
	private int numJugador;
	public boolean activo = true;
	public Jugador() {
		numJugador = num;
		num++;
		this.listaJugadores.add(this);
		this.listaJugadoresActivos.add(this);
	}
	public boolean getActivo() {
		return this.activo;
	}
	public int getNumJugador(){
		return this.numJugador;
	}
	public void eliminarJugador() {
		this.activo=false;
	}
}
