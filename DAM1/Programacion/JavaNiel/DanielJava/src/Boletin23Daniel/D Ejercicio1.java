package Boletin23Daniel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class D_Ejercicio1 {

	///1. Escribe un programa que reciba por teclado el nombre de un fichero y una palabra. Tú programa
	///debería de decirnos cuantas líneas tiene el fichero y cuantas veces aparece esa palabra repetida en
	///el fichero.
	
	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		Boolean existe=true;
		String rutaFichero;
		do {
			System.out.print("Escriba el nombre del fichero: ");
			rutaFichero = teclado.nextLine();
			if(existe(rutaFichero)==false) {
				existe=false;
				System.out.printf("El fichero %s no existe\n",rutaFichero);
			} else {
				existe=true;
			}
		} while(existe==false);
		ArrayList<String> fichero = devuelveContenido(rutaFichero);
		System.out.print("Introduce la palabra a buscar: ");
		String palabraBuscar = teclado.nextLine();
		if(devuelveContenido(rutaFichero)==null)
			System.out.println("El fichero esta vacio.");
		else
			System.out.printf("El fichero tiene %d lineas.\n",fichero.size());
		int contador=0;
		for(String linea:fichero) {
			contador += cuentaPalabras(linea,palabraBuscar);
		}
		if(contador==0)
			System.out.println("La palabra no aparece ni una sola vez");
		else if(contador==1)
			System.out.println("La palabra aparece una vez.");
		else
			System.out.printf("La palabra aparece %d veces.\n", contador);
		
	}
	public static boolean existe(String ruta) {
		File n = new File(ruta);
		if(n.exists())
			return true;
		else
			return false;
	}
	public static ArrayList<String> devuelveContenido(String ruta){
		ArrayList<String> lineas=null;
		try {
			Path fichero = Path.of(ruta);
			lineas = (ArrayList<String>) Files.readAllLines(fichero);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return lineas;
	}
	public static int cuentaPalabras(String linea, String palabra) {
		String[] palabras = linea.split(" ");
		int num=0;
		for(String p:palabras) {
			if(p.equalsIgnoreCase(palabra))
				num+=1;
			else 
				num+=0;
		}
		return num;
	}

}
