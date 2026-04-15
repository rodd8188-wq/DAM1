package examen;

import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {

	/*	Scanner teclado = new Scanner(System.in);
		boolean repeticion = true;
		try {
        System.out.println("Cuantos dados vas a tirar? ");
		int dados = teclado.nextInt();
		teclado.close();
		int resultados [] = new int[dados];
        System.out.println("Haz tirado " + dados + " dados y ha salido lo siguiente: ");
        for(int i=0; i< dados; i++) {
        int numeroAletorios = (int)(Math.random()*7)+1;
        resultados[i] = numeroAletorios;
        	if(i == dados-1)
            	System.out.println(numeroAletorios);
            else      
            	System.out.print(numeroAletorios + ", ");
        }
        int suma = 0;
        for(int n: resultados) {
        	suma += n;
        }
        System.out.println("La suma de todos los dados da " + suma);	
	   
		}catch(Exception e) {
		System.out.println("No es una opcion valida");
	} */
		
    	Scanner teclado = new Scanner(System.in);
    	System.out.print("Cuantos dados vas a tirar? ");
    	int numDados = 0; //CUANDO USAMOS TRY O ALGO PARECIDO, INICIALIZAMOS LA VARIABLE FUERA.
    	boolean entradaCorrecta = false;
    	while(entradaCorrecta == false) {
    		entradaCorrecta = true;
    	try {
            numDados = teclado.nextInt();
            if(numDados <= 0) {
            entradaCorrecta = false;
    		System.out.print("Entrada incorrecta. Vuelve a intentarlo");
            }
            
    	 }catch(Exception e) {
    		System.out.print("Entrada incorrecta. Vuelve a intentarlo");
    		teclado.nextLine(); //BORRAMOS EL BUFFER DEL TECLADO, POR EL TRY Y EL CATCH
           entradaCorrecta = false;	 
    	 }
    	}
    	teclado.close();
    	int contadorUnos = 0;
    	int contadorSeises = 0;
    	int suma = 0;
    	for(int i=0; i<numDados; i++) {
    		int dado = (int)(Math.random()*6)+1;
    		System.out.print(dado);
    		if( i != numDados-1)
    			System.out.print(", ");
    		else
    			System.out.println("");
    		if(dado == 1)
    			contadorUnos++;
    		else if (dado == 6)
    			contadorSeises++;
    		
    		suma += dado;
    	}
    	
    	if(contadorUnos != 0)
       System.out.println("En " + contadorUnos + " dados ha salido un 1");
    	if(contadorSeises != 0)
       System.out.println("En " + contadorSeises + " dados ha salido un 6");
    	
       System.out.println("La suma de todos los dados es: " + suma);
       
       int media = 3*numDados;
       if(media > suma)
    	   System.out.println("Tu tirada esta por debajo de la media (" + media + ")");
       else if ( media < suma)
    	   System.out.println("Tu tirada esta por encima de la media (" + media + ")");
       else 
    	   System.out.println("Tu tirada es exactamente la media (" + media + ")");
  }
}


