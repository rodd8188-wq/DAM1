package poo5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Pokemon pikachu = new Pokemon(25, "Pikachu", "Eléctrico");
		Pokemon butterfree = new Pokemon(12, "Butterfree", "Bicho", "Volador");
		Pokemon pikachu2 = new Pokemon(25, "Pikachu", "Eléctrico");
		Pokemon pikachu3 = pikachu2;
		Pokemon ekans = new Pokemon(23, "Ekans", "Veneno");
		Pokemon arkanine = new Pokemon(59, "Arcanine", "Fuego");
		
		ArrayList<Pokemon> lista = new ArrayList<>(List.of(pikachu,ekans,butterfree,arkanine,pikachu2,pikachu3));
		Collections.sort(lista);
		for(Pokemon p : lista)
			System.out.println(p);
		
		System.out.println(pikachu);
		System.out.println(butterfree);
		
		if(pikachu.equals(pikachu2))
			System.out.println("Son iguales");
		else
			System.out.println("No son iguales");
		
		if(pikachu2.equals(pikachu3))
			System.out.println("Son iguales");
		else
			System.out.println("No son iguales");
	}

}
