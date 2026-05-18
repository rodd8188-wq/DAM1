package Boletin23Daniel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class D_Ejercicio6 {

	public static void main(String[] args) {
		
		if(comprobarFichero("/home/alumno/A/ficheros/ejercicio6bol23")==true)
			System.out.println("Formato correcto");
		else
			System.out.println("Formato incorrecto");
	}
	
	public static boolean comprobarFichero(String ruta) {
		boolean correcto = true;
		int lineasConDatos = 0;
		try {
			Path fichero = Path.of(ruta);
			ArrayList<String> lineas = (ArrayList<String>) Files.readAllLines(fichero);
			for(String linea:lineas)
				if(linea.equals("") || linea.equals(" ")) {}
				else
					lineasConDatos++;
			int numLinea=0;
			int conjutosLlevados=0;
			int numConjuntos=lineasConDatos/3;
			if(lineasConDatos%3==0) {
				for(int i=0;i<=numConjuntos-1;i++) {
					try {
						int n = Integer.valueOf(lineas.get(numLinea));
						n = Integer.valueOf(lineas.get(numLinea+1));
						correcto = false;
					} catch(Exception e) {
						
					} finally {
						try {
							int n = Integer.valueOf(lineas.get(numLinea+2));
						} catch(Exception e) {
							correcto = false;
						}
						conjutosLlevados++;
						if(conjutosLlevados>=numConjuntos==false)
							numLinea+=3;
					}
				}
			} else {
				correcto=false;
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return correcto;
	}

}
