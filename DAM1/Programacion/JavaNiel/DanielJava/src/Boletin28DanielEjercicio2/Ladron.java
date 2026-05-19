package Boletin28DanielEjercicio2;

public interface Ladron {
	
	int[] getAtributo();
	
	public default boolean Sigilo() {		//Destreza(1)
		int contador = 0;
		for(int i = 0; i < this.getAtributo()[1]; i++) {
			int dado = (int)(Math.random()*(6-1+1))+1;
			if (dado == 6)
				contador++;
		}
		if (contador >= 4)
			return true;
		return false;
	}
}
