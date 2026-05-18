package Boletin28DanielEjercicio2;

public interface Mago {
	public static int Hechizo(Personaje p) {	//Inteligencia(2)
		return (int)(Math.random()*((p.atributo[2])-1+1))+1;
	}
}
