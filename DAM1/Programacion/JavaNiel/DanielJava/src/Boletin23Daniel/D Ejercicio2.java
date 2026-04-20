package Boletin23Daniel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class D_Ejercicio2 {

	///2. Escribe una función que nos cuente el número de líneas y de caracteres que hay en un fichero. La
	///llamada a la función debería de ser como esta:
	
	public static void main(String[] args) {
		
		estadisticas("/home/alumno/A/ficheros/metodo2");
			}
	public static void estadisticas(String ruta) {
		try {
			Path fichero = Path.of(ruta);
			ArrayList<String> lineas = (ArrayList<String>) Files.readAllLines(fichero);
			System.out.printf("Número de líneas: %d\n",lineas.size());
			int lineasVacias=0;
			int numCaracteres=0;
			int numEspacios=0;
			for(String linea:lineas) {
				if(linea.equalsIgnoreCase(""))
					lineasVacias++;
				String[] caracteres = linea.split("");
				for(String caracter:caracteres) {
					if(caracter.equalsIgnoreCase(" ")==false && caracter.equalsIgnoreCase("")==false)
						numCaracteres++;
					else if(caracter.equals(" "))
						numEspacios++;
				}	
			}
			System.out.printf("Líneas en blanco: %d\n", lineasVacias);
			System.out.printf("Cantidad de caracteres sin contar los espacios: %d\n", numCaracteres);
			System.out.printf("Cantidad de espacios: %d", numEspacios);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
