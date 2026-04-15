package dam1;

import java.util.ArrayList;
import java.util.List;

public class MetodoDeSeleccion {

	public static void main(String[] args) {

		ArrayList<Integer> numeros = new ArrayList<>(List.of(7,1,3,5,4,6));
		
		ArrayList<Integer> numerosPorSeleccion = new ArrayList<>();
		
		System.out.println(numeros);
		do {
		int mayor=-1;
		for(int i=0;i<=numeros.size()-1;i++)
			if(mayor<numeros.get(i))
				mayor=numeros.get(i);
		numeros.remove((Integer)mayor);
		numerosPorSeleccion.add(mayor);
		} while(numeros.isEmpty()==false);
		
		System.out.println(numerosPorSeleccion);
	}
}
