package examen;

import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) {

		//ASI SE COMIENZA EL EJERCICIO, DANDO PREMIOS ALETORIOS A LOS ELEMENTOS DEL ARRAY.
/*	  Scanner teclado = new Scanner(System.in);
	  System.out.print("Cuantos premios vas a repartir?");
	  int numPremios = teclado.nextInt();
	  teclado.close();
	  String[] clientes = {"Diego Norrea", "Ines Perado", "Demetrio Imedio", "Roberto Rija", "Ruben Tosidad", "Armando Adistancia", "German Tequilla"};
	  int numClientes = clientes.length;
	  for(int i=0; i<numPremios; i++) {
		  int premio = (int)(Math.random()*numClientes);
		  System.out.println(clientes[premio]);  */
		  
       //AHORA SE HACE LO "COMPLICADO" PARA QUE NO SE REPITA: 
		
/*		  Scanner teclado = new Scanner(System.in);
		  System.out.print("Cuantos premios vas a repartir?");
		  int numPremios = teclado.nextInt();
		  teclado.close();
		  String[] clientes = {"Diego Norrea", "Ines Perado", "Demetrio Imedio", "Roberto Rija", "Ruben Tosidad", "Armando Adistancia", "German Tequilla"};
		  int numClientes = clientes.length;
		  int premiosRepartidos = 0;
		  while(premiosRepartidos < numPremios) {
			  int premio = (int)(Math.random()*numClientes);
			  if(clientes[premio] != null) {
			  System.out.println(clientes[premio]);
			  clientes[premio] = null;
			  premiosRepartidos++;}
	  } */
		  
	  //SEGUIMOS MODIFICANDO PARA ACABAR EL EJERCICIO :
		
		Scanner teclado = new Scanner(System.in);
		  System.out.print("Cuantos premios vas a repartir?");
		  int numPremios = teclado.nextInt();
		  teclado.close();
		  String[] clientes = {"Diego Norrea", "Ines Perado", "Demetrio Imedio", "Roberto Rija", "Ruben Tosidad", "Armando Adistancia", "German Tequilla"};
		  int numClientes = clientes.length;
		  if(numClientes==numPremios)
			  System.out.println("Tienes solo " + numClientes + "clientes..");
		  else if(numClientes < numPremios)
			  System.out.println("Tienes solo " + numClientes + "clientes..");
		      System.out.println("Te sobran "+ (numClientes-numPremios) + "clientes..");
		      
		  int premiosRepartidos = 0;
		  while(premiosRepartidos < numPremios) {
			  int premio = (int)(Math.random()*numClientes);
			  if(clientes[premio] != null) {
			  int posicion = clientes[premio].indexOf(" ");
			  String nombre = clientes[premio].substring(0,posicion);
			  String apellido = clientes[premio].substring(posicion+1);
			  System.out.println(apellido );
			  clientes[premio] = null;
			  premiosRepartidos++;
	}
			  }

}
	}
 