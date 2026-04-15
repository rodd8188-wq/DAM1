package FicherosBinarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		String ruta = "/home/alumno/A/ficheros/binario.bin";
		
		//escribirFicheroBinario(ruta);
		
		//leerFicheroBinario(ruta);
		
		Tarea t1 = new Tarea("ET10","Hacer cosas",3,true);
		Tarea t2 = new Tarea("YT20","No perder la racha",7,false);
		Tarea t3 = new Tarea("ET90","Comer comida",1,false);
		Tarea t4 = new Tarea("MT1","Cenar cena",5,false);
		
		grabarListaTareas(Tarea.getListaTareas(), ruta);
		
		ArrayList<Tarea> listaRecuperada = leerListaTareas(ruta);
		
		for(Tarea tarea: listaRecuperada)
			tarea.mostrarTarea();
		
		//grabarTarea(t1, ruta);
		
		/*
		Tarea tarea = leerTarea(ruta);
		if(tarea != null)
			tarea.mostrarTarea();
		*/
		
	}
	
	public static void grabarTarea(Tarea tarea, String ruta) {
		try(ObjectOutputStream binario = new ObjectOutputStream(new FileOutputStream(ruta))) {
			binario.writeObject(tarea);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void grabarListaTareas(ArrayList<Tarea> listaTareas, String ruta) {
		try(ObjectOutputStream binario = new ObjectOutputStream(new FileOutputStream(ruta))) {
			binario.writeObject(listaTareas);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static ArrayList<Tarea> leerListaTareas(String ruta) {
		ArrayList<Tarea> listaTareas=null;
		try(ObjectInputStream binario = new ObjectInputStream(new FileInputStream(ruta))) {
			listaTareas = (ArrayList<Tarea>)binario.readObject();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return listaTareas;
	}
	
	public static Tarea leerTarea(String ruta) {
		Tarea tarea=null;
		try(ObjectInputStream binario = new ObjectInputStream(new FileInputStream(ruta))) {
			tarea = (Tarea)binario.readObject();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return tarea;
	}
	
	public static void leerFicheroBinario(String ruta) {
		try(DataInputStream binario = new DataInputStream(new FileInputStream(ruta))) {
			
			///Leer los número entero (int)
			System.out.println(binario.readInt());
			///Leer los número con decimales (Double)
			System.out.println(binario.readDouble());
			///Leer los boleano (boolean)
			System.out.println(binario.readBoolean());
			///Leer los caracter (char)
			System.out.println(binario.readChar());
			///Leer los cadena de texto (String)
			System.out.println(binario.readUTF());
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void escribirFicheroBinario(String ruta) {
		try(DataOutputStream binario = new DataOutputStream(new FileOutputStream(ruta))) {
			
			///Escribir un número entero (int)
			binario.writeInt(3456);
			///Escribir un número con decimales (Double)
			binario.writeDouble(3.1415935);
			///Escribir un boleano (boolean)
			binario.writeBoolean(false);
			///Escribir un caracter (char)
			binario.writeChar('X');
			///Escribir una cadena de texto (String)
			binario.writeUTF("Hola mundo binario");
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
