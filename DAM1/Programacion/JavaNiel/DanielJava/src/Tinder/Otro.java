package Tinder;

class Otro extends Persona {
	public Otro(Tinder tinder, String nombre, String fechaNac, int interes) {
		super(nombre,fechaNac,interes);
		tinder.anyade(this);
	}
	public Otro(Tinder tinder, String nombre, String fechaNac, int interes, int edadMin, int edadMax) {
		super(nombre,fechaNac,interes,edadMin,edadMax);
		tinder.anyade(this);
	}
}