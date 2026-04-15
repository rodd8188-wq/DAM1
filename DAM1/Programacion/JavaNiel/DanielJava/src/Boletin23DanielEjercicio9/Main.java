package Boletin23DanielEjercicio9;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		int respuesta = 0;
		do {
			Alumno.primeraLecturaListaAlumnos();
			respuesta = Alumno.mostrarMenu(teclado);
			switch(respuesta) {
			case 1:
				Alumno.anyadirAlumno(teclado);
				break;
			case 2:
				Alumno.eliminarAlumno(teclado);
				break;
			case 3:
				System.out.println("3");
				break;
			case 4:
				Alumno.mostrarListaAlumnos();
				break;
			case 5:
				System.out.println("5");
				break;
			case 6:
				System.out.println("6");
				break;
			case 7:
				System.out.println("Programa terminado");
				break;
			default:
				System.out.println("Introduce una opción valida");
				break;
			}
		} while(respuesta!=6);
		teclado.close();
	}
	

}
