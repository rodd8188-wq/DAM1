package LectorFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Texto {

	public static void main(String[] args) {
		//metodo1();
		//metodo2();
		//metodo3();
		metodo4();
	}
	
	public static void metodo1() {
		try {
			
			BufferedReader lector = new BufferedReader(new FileReader("/home/alumno/A/ficheros/metodo1"));
			String linea;
			while((linea = lector.readLine())!=null) {
				System.out.println(linea);
			}
			
			//Otra manera para recorrerlo
			/*
			String linea = lector.readLine();
			do {
				System.out.println(linea);
				if(linea!=null)
					linea=lector.readLine();
			} while(linea!=null);
			*/
			
			lector.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public static void metodo2() {
		try {
			
			File fichero = new File("/home/alumno/A/ficheros/metodo1");
			
			Scanner lector = new Scanner(fichero);
			String linea;
			while(lector.hasNextLine()) {
				linea=lector.nextLine();
				System.out.println(linea);
			}
			
			lector.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public static void metodo3() {
		try {
			
			ArrayList<String> lineas;
			Path fichero = Path.of("/home/alumno/A/ficheros/metodo1");
			lineas = (ArrayList) Files.readAllLines(fichero);
			
			System.out.println(lineas);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void metodo4() {
		try {
			
			Path fichero = Path.of("/home/alumno/A/ficheros/metodo1");
			String contenido = null;
			
			contenido=Files.readString(fichero);
			System.out.println(contenido.replace("\n", " "));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
