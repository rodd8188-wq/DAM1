package Boletin28DanielEjercicio2;

public interface Mago {
	
	int[] getAtributo();
	
	public default int Hechizo() {	//Inteligencia(2)
		return (int)(Math.random()*((this.getAtributo()[2])-1+1))+1;
	}
}
