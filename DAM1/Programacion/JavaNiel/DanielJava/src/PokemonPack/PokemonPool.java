package PokemonPack;

import java.util.Scanner;

public class PokemonPool {

	public static void main(String[] args) {
		Pokemon p1 = new Pokemon("Bulbasaur", "Planta",1);
		Pokemon p6 = new Pokemon("Charizard", "Fuego", "Volador",6);
		Pokemon p2 = new Pokemon("Ivysaur", "Planta",2);
		Pokemon p25 = new Pokemon("Pikachu", "Electrico",25);
		Pokemon p11 = new Pokemon("Metapod", "Bicho",11);
		Pokemon p7 = new Pokemon("Squirtle", "Agua",7);
		
		Equipo equipo1 = new Equipo("José María",p1,p6,p25);
		Equipo equipo2 = new Equipo("Ash Ketchum",p2,p11,p7);
		
		
		
		///Combate 1 contra 1, eliges a un pokemon y el contrincante se selecciona al azar.
		/*
		System.out.println("Elige a tu pokemon");
		Scanner teclado = new Scanner(System.in);
			p1.mostrar();
			p6.mostrar();
			p2.mostrar();
			p25.mostrar();
			p11.mostrar();
			p7.mostrar();
		System.out.print("Excribe su número de pokedex: ");
		int tuPok = teclado.nextInt();
		teclado.close();
		
		Pokemon arrayPok[]= {p1,p6,p2,p25,p11,p7};
		
		int random = (int)(Math.random()*5)+1;
		System.out.println(random);
		
		if(tuPok==1)
			p1.combateContra(arrayPok[random]);
		if(tuPok==6)
			p6.combateContra(arrayPok[random]);
		if(tuPok==2)
			p2.combateContra(arrayPok[random]);
		if(tuPok==25)
			p25.combateContra(arrayPok[random]);
		if(tuPok==11)
			p11.combateContra(arrayPok[random]);
		if(tuPok==7)
			p7.combateContra(arrayPok[random]);
		*/
		///Fin del combate 1 contra 1 eligiendo al atacante
	}

}