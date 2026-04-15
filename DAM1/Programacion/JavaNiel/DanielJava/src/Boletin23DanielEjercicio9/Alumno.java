package Boletin23DanielEjercicio9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import FicherosBinarios.Tarea;

public class Alumno implements Serializable {
	private static int intentoDeLectura = 0;
	private int id = 1;
	private static ArrayList<Alumno> listaAlumnos = new ArrayList<>();
	private String nombre;
	private Double[] notas = new Double[5];
	private static String ruta = "/home/alumno/A/ficheros/Redes.bin";
	public Alumno(String nom, Double RA1, Double RA2, Double RA3, Double RA4, Double RA5) {
		Alumno.listaAlumnos = leerListaAlumnos();
		id = generarIdAlumno();
		nombre=nom;
		notas[0] = RA1;
		notas[1] = RA2;
		notas[2] = RA3;
		notas[3] = RA4;
		notas[4] = RA5;
		Alumno.listaAlumnos.add(this);
		grabarListaAlumnos(listaAlumnos, ruta);
	}
	
	public int generarIdAlumno() {
		int id = 1;
		boolean encontrado = false;
		if(listaAlumnos==null)
			id = 1;
		else {
			for(int i=1;i<=listaAlumnos.size()+1;i++) {
				for(Alumno alumno: listaAlumnos)
					if(encontrado == false) {
						do {
							if(i!=alumno.id && i!=0) {
								id = i;
								encontrado = true;
							}
						} while(encontrado = false);
					} else 
						break;
			}
		}
		return id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public static void grabarListaAlumnos(ArrayList<Alumno> listaAlumnos, String ruta) {
		try(ObjectOutputStream binario = new ObjectOutputStream(new FileOutputStream(ruta))) {
			binario.writeObject(listaAlumnos);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean ultimaEscrituraListaAlumnos() {
		boolean exito = true;
		try {
			Alumno.grabarListaAlumnos(listaAlumnos, ruta);
			System.out.println("Lista grabada con exito");
		} catch (Exception e) {
			exito = false;
			System.out.println("Error al grabar la lista");
		}
		return exito;
	}
	
	public static void primeraLecturaListaAlumnos() {
		Alumno.intentoDeLectura = 0;
		Alumno.listaAlumnos = leerListaAlumnos();
	}
	
	public static ArrayList<Alumno> leerListaAlumnos() {
		Alumno.intentoDeLectura++;
		ArrayList<Alumno> listaAlumnos=null;
		try(ObjectInputStream binario = new ObjectInputStream(new FileInputStream(Alumno.ruta))) {
			listaAlumnos = (ArrayList<Alumno>)binario.readObject();
		} catch(FileNotFoundException e) {
			if(intentoDeLectura > 1)
				System.out.println("No existe el fichero");
		} catch(Exception e) {
			System.out.println("Error al leer la lista:");
			System.out.println(e.getMessage());
		}
		return listaAlumnos;
	}
	
	public static void anyadirAlumno(Scanner teclado) {
		System.out.print("Nombre del alumno: ");
		teclado.nextLine();
		String nombre = teclado.nextLine();
		Double RA1;	Double RA2;	Double RA3;	Double RA4;	Double RA5;
		//RA1
		int contador=0;
		do {
			if(contador>0)
				System.out.println("Nota invalida");
			System.out.print("Nota del RA1: ");
			try {
				RA1 = teclado.nextDouble();
			} catch(Exception e) {
				RA1 = -1.0;
				teclado.nextLine();
			}
			contador++;
		} while(RA1 < 0 || RA1 > 10);
		//RA2
		contador=0;
		do {
			if(contador>0)
				System.out.println("Nota invalida");
			System.out.print("Nota del RA2: ");
			teclado.nextLine();
			try {
				RA2 = teclado.nextDouble();
			} catch(Exception e) {
				RA2 = -1.0;
			}
			contador++;
		} while(RA2 < 0 || RA2 > 10);
		//RA3
		contador=0;
		do {
			if(contador>0)
				System.out.println("Nota invalida");
			System.out.print("Nota del RA3: ");
			teclado.nextLine();
			try {
				RA3 = teclado.nextDouble();
			} catch(Exception e) {
				RA3 = -1.0;
			}
			contador++;
		} while(RA3 < 0 || RA3 > 10);
		//RA4
		contador=0;
		do {
			if(contador>0)
				System.out.println("Nota invalida");
			System.out.print("Nota del RA4: ");
			teclado.nextLine();
			try {
				RA4 = teclado.nextDouble();
			} catch(Exception e) {
				RA4 = -1.0;
			}
			contador++;
		} while(RA4 < 0 || RA4 > 10);
		//RA5
		contador=0;
		do {
			if(contador>0)
				System.out.println("Nota invalida");
			System.out.print("Nota del RA5: ");
			teclado.nextLine();
			try {
				RA5 = teclado.nextDouble();
			} catch(Exception e) {
				RA5 = -1.0;
			}
			contador++;
		} while(RA5 < 0 || RA5 > 10);
		//Crear alumno
		new Alumno(nombre,RA1,RA2,RA3,RA4,RA5);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public static void eliminarAlumno(Scanner teclado) {
		try {
			mostrarListaAlumnos();
			System.out.print("ID del alumno: ");
			teclado.nextLine();
			int id = teclado.nextInt();
			int posicion=0;
			for(Alumno alumno: listaAlumnos) {
				if(id == alumno.id)
					listaAlumnos.remove(posicion);
				System.out.println(posicion);
				posicion++;
			}
			grabarListaAlumnos(listaAlumnos, ruta);	
		} catch(Exception e) {
			System.err.println("Error al eliminar el alumno");
		}
	}
	
	public static int mostrarMenu(Scanner teclado) {
		System.out.println(" - REDES - ");
		System.out.println("1. Añadir alumno");
		System.out.println("2. Eliminar alumno");
		System.out.println("3. Modificar alumno");
		System.out.println("4. Mostrar alumnos");
		System.out.println("5. Mostrar alumnos aprovados");
		System.out.println("6. Mostrar alumnos suspensos");
		System.out.println("7. Terminar programa");
		System.out.print(" Opción: ");
		int respuesta = 0;
		try {
			respuesta = teclado.nextInt();
		} catch(Exception e) {
			respuesta = 0;
			teclado.nextLine();
		}
		return respuesta;
	}
	
	public static void mostrarListaAlumnos() {
		try {
			int contador = 0;
			for(Alumno alumno: Alumno.leerListaAlumnos()) {
				contador++;
				if(contador<=1) {
					System.out.println("-----------------");
					System.out.println("Lista de alumnos:");
				}
				System.out.println(alumno.getId()+" | "+alumno.getNombre());
			}
			System.out.println("-----------------");
		} catch (Exception e) {
			///System.out.println(e.getMessage());
		}
	}
}
