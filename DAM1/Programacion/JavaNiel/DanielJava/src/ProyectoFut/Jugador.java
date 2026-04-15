package ProyectoFut;

class Jugador extends Persona {
	private int numDorsal;
	private Equipo equipo;
	public Jugador(String nombre, int num, Equipo equipo) {
		super(nombre);
		numDorsal=num;
		this.equipo=equipo;
		this.equipo.anyadeJugador(this);
	}
	public String getNombre() {
		return this.nombre;
	}
}
