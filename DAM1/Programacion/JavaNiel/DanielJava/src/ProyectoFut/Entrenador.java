package ProyectoFut;

class Entrenador extends Persona {
	private Equipo equipo;
	public Entrenador(String nombre, Equipo equipo) {
		super(nombre);
		this.equipo=equipo;
	}
}
