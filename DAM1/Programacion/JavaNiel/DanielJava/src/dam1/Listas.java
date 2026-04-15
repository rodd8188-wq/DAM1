package dam1;

import java.util.ArrayList;
import java.util.List;

public class Listas {
	public static void main(String[] args) {
		//Crear una lista vacia
		ArrayList<String> textos = new ArrayList<>();
		ArrayList<Double> conDecimales = new ArrayList<>();
		ArrayList<Integer> enteros = new ArrayList<>(List.of(1,2,3,4,5));
		
		//Crear una lista con contenido
		ArrayList<Double> precios = new ArrayList<>(List.of(33.5,64.9,175.0));
		ArrayList<String> alumnos = new ArrayList<>(List.of("Jaime","Adrián","Lucía","Óscar","Lucía"));
		
		//Añadir contenido dentro de una lista creada
		textos.add("Hola mundo");
		textos.add("Adios, adios");
		conDecimales.add(9.5);
		precios.add(23.12);
		
		System.out.println(alumnos);
		System.out.println(precios);
		System.out.println(textos);
		System.out.println(conDecimales);
		System.out.println(enteros);
		System.out.println();
		
		//Devuelve el contenido de la posición 1 (La primera es la 0)
		System.out.println(textos.get(1));
		System.out.println(textos);
		
		//Devuelve el tamaño de la lista
		System.out.println(textos.size());
		
		//Ver si un contenido esta dentro de la lista
		if(alumnos.contains("Lucía"))
			System.out.println("Está en la lista");
		else
			System.out.println("No está en la lista");
		
		//Nos devuelve la posición en la que se encuentra dentro de la lista
		System.out.println(alumnos.indexOf("Lucía"));
		//Si no se encuentra en la lista nos devuelve un -1
		System.out.println(alumnos.indexOf("Pepe"));
		//Nos devuelve la última posición en la que se encuentra dentro de la lista
		System.out.println(alumnos.lastIndexOf("Lucía"));
		
		//Elimina la primera posición en la que se encuentra dentro de la lista
		alumnos.remove("Lucía");
		System.out.println(alumnos);
		//Elimina el elemento que se encuentre en esa posición dentro de la lista
		alumnos.remove(0);
		System.out.println(alumnos);
		//Si la operación falla nos devuelve un false
		System.out.println(alumnos.remove("Pepe"));
		//Eliminar un entero en vez del elemento en esa posición
		enteros.remove((Integer)3);
		System.out.println(enteros);
		
		//Vaciar una lista
		enteros.clear();
		if(enteros.isEmpty())
			System.out.println("La lista está vacia");
		
		//Crear una copia exacta a una lista
		ArrayList alumnos2 = (ArrayList) alumnos.clone();
		System.out.println(alumnos2);
		
		//Crear una lista similar a una tupla (Es una lista la cual no se puede modificar)
		List<Integer> tupla = new ArrayList<>(List.of(1,2,3,4,5,6,7));
		
		//Recorrer la lista
		for(int i=0;i<alumnos.size();i++)
			System.out.println(alumnos.get(i));

	}
}
