package Interfaces;

public interface Jubilada {
	int EDAD_JUBILACION = 67;
	
	// Metodo abstracto (Obliga a todos los que heredan a que exista un metodo asi)
	void cuantoMeFalta();
	
	static void mePuedoJubilar(Persona p) {
		if(p.getEdad() > EDAD_JUBILACION)
			System.out.println("Estas jubilado macho");
		else
			System.out.println("Aun te queda");
	}
	
	default void informacion() {
		System.out.println("Edad corriente de jubilación: " + EDAD_JUBILACION);
	}
}
