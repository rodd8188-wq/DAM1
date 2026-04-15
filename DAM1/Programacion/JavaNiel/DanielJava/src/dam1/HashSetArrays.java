package dam1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HashSetArrays {

	public static void main(String[] args) {
		HashSet<String> alumnos = new HashSet<>();
		HashSet<String> profesores = new HashSet<>(Arrays.asList("José María Morales","Yago Navarrete"));
		System.out.println(alumnos);
		System.out.println(profesores);
		//Si el elemento no se encontraba en la lista lo añade y devuelve un true
		alumnos.add("Alfonso Literario");
		alumnos.add("Esteban Dolero");
		alumnos.add("Penelope Ligro");
		System.out.println(alumnos);
		//Si el elemento ya se encontraba en la lista no lo añade al array y devuelve false
		alumnos.add("Esteban Dolero");
		System.out.println(alumnos);
		if(alumnos.add("Esteban Dolero")==false) {
			System.out.println("Ya estaba en la lista y no fue añadido");
		}
		if(alumnos.add("Paula Briceño")==true) {
			System.out.println("No estaba en la lista y fue añadido");
		}
		if(alumnos.contains("Esteban Dolero")) {
			System.out.println("Esta en el array");
		} else {
			System.out.println("No esta en el array");
		}
		int i=0;
		for(String alumno:alumnos) {
			if(i!=alumnos.size()-1)
				System.out.print(alumno+", ");
			else
				System.out.println(alumno);
			i++;
		}
		HashSet<Integer> numeros = new HashSet<>(Arrays.asList(1,1,4,5,6,1,2,3,4,5,5,6,7,3,9,2,5,6,1,6,9,0,1));
		System.out.println(numeros);
		//Asi se puede eliminar los elementos repetidos de un ArrayList mediante el HashSet
		ArrayList<Integer> numerosArrayList = new ArrayList<>(List.of(1,1,4,5,6,1,2,3,4,5,5,6,7,3,9,2,5,6,1,6,9,0,1));
		System.out.println("Esto es un ArrayList: "+numerosArrayList);
		HashSet<Integer> numerosHashSet = new HashSet<>(numerosArrayList);
		System.out.println("Esto en un HashSet: "+numerosHashSet);
		numerosArrayList = new ArrayList<>(numerosHashSet);
		System.out.println("Esto es un ArrayList: "+numerosArrayList);
		//Si lo igualas no se crea un array nuevo, se crea una referencia. Si borras un elemento en un array se borra tambien en el otro
		HashSet<Integer>otrosNumeros = numerosHashSet;
		otrosNumeros.remove(5);
		System.out.println(otrosNumeros);
		System.out.println(numerosHashSet);
		//Crea un array que hace referencia al array
		HashSet<Integer>conjunto1 = numerosHashSet;
		//Crea un array nuevo independiente del array copiado
		HashSet<Integer>conjunto2 = new HashSet<>(numerosHashSet);
		
		//
		//conjunto2.retainAll(conjunto1);
		//conjunto2.addAll(conjunto1);
		//
	}

}
