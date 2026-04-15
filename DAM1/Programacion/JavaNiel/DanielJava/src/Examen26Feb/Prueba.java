package Examen26Feb;

import java.util.HashMap;

public class Prueba {
	static HashMap<Integer,Integer> pruebasRealizadas = new HashMap<>();
	static int numPrueba = 0;
	private int numEliminados;
	public Prueba(int numEliminados) {
		this.numEliminados=numEliminados;
		numPrueba++;
	}
	public int getNumPrueba() {
		return this.numPrueba;
	}
	
}
