package ProyectoFut;

import java.util.HashSet;

public class Competicion {
	private String nombre;
	private HashSet<Equipo> equipos = new HashSet<>();
	public Competicion(String nombre) {
		this.nombre=nombre;
	}
	public void anyadeEquipo(Equipo equipo) {
		this.equipos.add(equipo);
	}
}
