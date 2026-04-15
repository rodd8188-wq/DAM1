package Boletin23Daniel;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class D_Ejercicio11 {

	public static void main(String[] args) {
		String ruta = "/home/alumno/A/ficheros/login";
		HashMap<String, String> usuarios = leerFichero(ruta);
		Scanner teclado = new Scanner(System.in);
		int respuesta=0;
		if(usuarios.size()>=1) {
			do {
			System.out.println("\n1.Iniciar sesión\n2.Crear un usuario\n3.Terminar programa\n");
			String respuestaStr="";
			respuesta=0;
			boolean correcta=false;
			do {
				System.out.print("Escribe la opción (1, 2, 3) :");
				respuestaStr = teclado.nextLine();
				try {
					respuesta = Integer.parseInt(respuestaStr);
				} catch (Exception e) {
					correcta=false;
					respuesta=0;
				}
				if(respuesta==1||respuesta==2||respuesta==3)
					correcta=true;
			} while(correcta==false);
			if(respuesta==1) {
				iniciarSesion(usuarios);
			} else if(respuesta==2) {
				nuevoUsuario(usuarios,ruta);
			} else {
				System.out.println("Programa terminado");
			}
			} while(respuesta!=3);
		}
		teclado.close();
	}
	public static HashMap<String, String> leerFichero(String ruta) {
		boolean ficheroAccesible=true;
		HashMap<String, String> usuarios = new HashMap<>();
		try {
			ArrayList<String> lineas;
			Path fichero = Path.of(ruta);
			lineas = (ArrayList<String>) Files.readAllLines(fichero);
			for(String linea: lineas) {
				String[] user = linea.split(":");
				usuarios.put(user[0],user[1]);
			}
		} catch(Exception e) {
			System.out.println("Fichero inexistente o imposible acceder a él");
			ficheroAccesible=false;
		}
		if(ficheroAccesible==true) {
			if(usuarios.size()==0)
				System.out.println("Fichero vacío");
		}
		return usuarios;
	}
	public static boolean iniciarSesion(HashMap usuarios) {
		boolean inicio=false;
		Scanner teclado = new Scanner(System.in);
		System.out.print("Usuario: ");
		String usuario = teclado.nextLine();
		if(usuarios.containsKey(usuario)==false)
			System.out.println("Usuario no encontrado");
		else {
			System.out.print("Contraseña: ");
			String contrasenya = teclado.nextLine();
			if(contrasenya.equals(usuarios.get(usuario))==false) {
				System.out.println("Contraseña incorrecta");
				inicio=false;
			} else {
				System.out.println("Login");
				inicio=true;
			}
		}
		return inicio;
	}
	public static void nuevoUsuario(HashMap usuarios, String ruta) {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Nuevo usuario: ");
		String usuario = teclado.nextLine();
		System.out.print("Contraseña: ");
		String password = teclado.nextLine();
		System.out.print("Repite la contraseña: ");
		String passwordRepetida = teclado.nextLine();
		if(password.equals(passwordRepetida)==false)
			System.out.println("Las contraseñas no coinciden");
		else if(usuarios.containsKey(usuario)==true)
			System.out.println("Ese usuario ya existe");
		else if(usuario.indexOf(":")>=0 || password.indexOf(":")>=0)
			System.out.println("Ni el usuario ni la contraseña puened tener el caracter ':'");
		else
			grabarEnFichero(usuario, password, ruta);
	}
	public static void grabarEnFichero(String usuario, String password, String ruta) {
		try(PrintWriter escritor = new PrintWriter(new FileWriter(ruta,true))){
			escritor.printf("%s:%s",usuario,password);
			try {
				Path fichero = Path.of(ruta);
				String contenido = null;
				contenido=Files.readString(fichero);
				if(contenido.substring(contenido.length()-2).equals("\n")==false) {
					escritor.print("\n");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
