package ProyectoFut;

import java.util.HashSet;

public class Equipo {
	private String nombre;
	private Competicion competicion;
	private HashSet<Jugador> jugadores = new HashSet<>();
	public Equipo(String nom, Competicion com) {
		nombre=nom;
		competicion=com;
		this.competicion.anyadeEquipo(this);
	}
	public void anyadeJugador(Jugador jug) {
		this.jugadores.add(jug);
	}
	public void listarJugadores() {
		System.out.println("Jugadores del equipo "+this.nombre+":");
		for(Jugador jugador:jugadores) {
			System.out.println(jugador.getNombre());
		}
	}
}
