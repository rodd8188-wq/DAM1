package examen;

import java.util.Scanner;

public class Ejercicio3 {

/*	public static void main(String[] args) {

		int numero1 = 8;
		int numero2 = 15;

		if (capicua(numero1, numero2))
		    System.out.println();
		else
			System.out.println("No hay ningun numero capicua");

	}

	public static boolean capicua(int a, int b) {
		int contadorDeCapicuas = 0;
		boolean comprobar = true;
		System.out.println("Números capicuas entre " + a + " y " + b + ":");
		for (int n = a; n <= b; n++) {
			String numeroCadena = String.valueOf(n);
			String cadenaInvertida = "";
			for (int i = numeroCadena.length() - 1; i >= 0; i--) {
				char c = numeroCadena.charAt(i);
				cadenaInvertida = cadenaInvertida + c;
			}
			if (numeroCadena.equals(cadenaInvertida)) {
				contadorDeCapicuas++;
				System.out.println(n);
			}
		}
		if (contadorDeCapicuas > 0) {
			comprobar = true;
			System.out.println("Hay un total de " + contadorDeCapicuas + " numeros capicuas");
		}

		else
			comprobar = false;

		return comprobar;

	} */
	
	public static void main(String[] args) {
		capicuas(12,54);
	}
    public static void capicuas(int n1, int n2) {
    	int contador = 0;
    	for (int i= n1; i<= n2; i++) {
    		if(esCapicua(i))
    			System.out.println(i);
    		    contador++;
      	}
    	if(contador == 0)
    		System.out.println("No hay ningun numero capicua");
    }
    
    public static boolean esCapicua(int n) {
    	boolean capicua = false;
    	String nTxt = String.valueOf(n);
    	String nTxtInvertido  = "";
    	for(int i=nTxt.length()-1; i>=0; i--)
    		nTxtInvertido += nTxt.charAt(i);
    	
    	if(nTxt.equals(nTxtInvertido))
    		  capicua = true;
    	
    	return capicua;
    	
    }	
    
	
}
