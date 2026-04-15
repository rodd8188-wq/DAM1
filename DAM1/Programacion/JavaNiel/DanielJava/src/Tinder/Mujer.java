package Tinder;

class Mujer extends Persona {
	public Mujer(Tinder tinder, String nombre, String fechaNac, int interes) {
		super(nombre,fechaNac,interes);
		tinder.anyade(this);
	}
	public Mujer(Tinder tinder, String nombre, String fechaNac, int interes, int edadMin, int edadMax) {
		super(nombre,fechaNac,interes,edadMin,edadMax);
		tinder.anyade(this);
	}
}
