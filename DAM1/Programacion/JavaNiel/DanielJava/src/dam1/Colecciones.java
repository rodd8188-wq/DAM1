package dam1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Colecciones {


	public static void main(String[] args) {
		
		///Colecciones
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		ArrayList<String> alumnos = new ArrayList<String>();
		
		Collections.addAll(numeros, 44, 56, 1, 2, 55, 7 ,3 ,3 ,44, 2, 89, 120, 45, 6 );
		Collections.addAll(alumnos, "Lucía", "Marcos", "Sara", "Alejandro");
		
		System.out.println("\nListas:");
		System.out.println(numeros);
		System.out.println(alumnos);
		//Ordena de menos a mayor
		System.out.println("\nLista ordenada de mayor a menor:");
		Collections.sort(numeros);
		Collections.sort(alumnos);
		
		System.out.println(numeros);
		System.out.println(alumnos);
		//Le da la vuelta a la lista
		System.out.println("\nLa lista del reves");
		Collections.reverse(numeros);
		Collections.reverse(alumnos);
		
		System.out.println(numeros);
		System.out.println(alumnos);
		//Mezcla la lista de forma aleatorio
		System.out.println("\nLa lista ordenada aleatoriamente:");
		Collections.shuffle(numeros);
		Collections.shuffle(alumnos);
		
		System.out.println(numeros);
		System.out.println(alumnos);
		//Devuelve el maximo de la lista
		System.out.println("\nEl valor máximo de la lista");
		System.out.println(Collections.max(numeros));
		
		//Devuelve el minimo de la lista
		System.out.println("\nEl valor mínimo de la lista");
		System.out.println(Collections.min(numeros));
		
		//Devuelve la frecuencia con la que sale el valor
		System.out.println("\nEl número de veces que sale en la lista:");
		System.out.println(Collections.frequency(numeros, 3));
		
		//Buscar un elemento en la lista	(Devuelve un negativo si no se encuentra en la lista)		IMPORTANTE: Solo funciona si la lista esta ordenada
		System.out.println("La posición del valor:");
		Collections.sort(numeros);
		System.out.println(Collections.binarySearch(numeros, 7));
		
	}

}
