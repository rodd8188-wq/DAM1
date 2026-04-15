package FicherosPorPosicion;

import java.io.RandomAccessFile;
import java.util.HashMap;

public class MainFicherosPorPosicion {
	
	//Variables finales
	static final char 
	charDeBorradoDeRegistro = '*';
	static final int 
	tamanyoNombre = 20, 
	tamanyoRegistro = (tamanyoNombre * 2) + 4;	// * 2 porque un char ocupa 2 bytes y  + 4 porque un int ocupa 4 bytes
	
	public static void main(String[] args) {
		
		///Acceso aleatorio a un fichero (No es aleatorio)
		String ruta = "/home/alumno/A/ficheros/agenda.dat";
		HashMap<String, Integer> agenda = new HashMap<>();
		agenda.put("Alejandro", 33);
		agenda.put("Luis", 24);
		agenda.put("Ana", 32);
		agenda.put("Elvira", 41);
		try {
			crearAgenda(ruta,agenda);
			//leerRegistro(ruta,2);
			//leerRegistro(ruta,3);
			//modificarRegistro(ruta,2,"Manolo",33);
			//leerRegistro(ruta,2);
			//nuevoRegistro(ruta,"José Antonio", 56);
			//leerRegistro(ruta,5);
			//borrarRegistro(ruta,5);
			leerTodosLosRegistros(ruta);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void borrarRegistro(String ruta, int registro) throws Exception{
		try(RandomAccessFile raf = new RandomAccessFile(ruta,"rw")){
			long posicion = tamanyoRegistro * (registro-1);
			if(posicion>=raf.length()) {
				System.out.println("El registro "+registro+" no existe.");
				System.out.println("El registro más alto es el "+raf.length()/tamanyoRegistro);
			} else {
				raf.seek(posicion);
				if(raf.readChar()==charDeBorradoDeRegistro) 
					System.out.println("El registro "+registro+" ya ha sido borrado.");
				else {
					raf.seek(posicion);
					raf.writeChar(charDeBorradoDeRegistro);
					System.out.printf("Registro %d borrado correctamente\n", registro);
				}
			}
		}
	}
	
	public static void leerTodosLosRegistros(String ruta) throws Exception{
		try(RandomAccessFile raf = new RandomAccessFile(ruta,"r")){
			System.out.println(" - TODOS LOS REGISTROS - ");
			for(int i=0;i<raf.length()/tamanyoRegistro;i++) {
				//raf.seek(tamanyoRegistro * i);					///Linea no necesaria
				String nombre = leerNombre(raf);
				int edad = raf.readInt();
				if(nombre.charAt(0) == charDeBorradoDeRegistro == false) {
					System.out.printf("Registro %d | Nombre : %-20s | Edad : %d\n",i+1, nombre, edad);
				}
			}
		}
	}
	
	public static void nuevoRegistro(String ruta, String nombre, int edad) throws Exception{
		try(RandomAccessFile raf = new RandomAccessFile(ruta,"rw")){
			raf.seek(raf.length());
			escribirNombre(raf, nombre);
			raf.writeInt(edad);
			System.out.println("Registro creado con exito");
		}
	}
	
	public static void crearAgenda(String ruta, HashMap<String, Integer> agenda)  throws Exception{
		//"r" = modo para leer
		//"rm" = modo para leer y escribir
		//"rwd" y "rws" = modo escritura lectura directamente en el disco (Más segura pero más lenta)
		///Si el fichero no existe se crea y si existe respeta el contenido ya existente
		try(RandomAccessFile raf = new RandomAccessFile(ruta,"rw")){
			for(String nombre: agenda.keySet()) {
				int edad = agenda.get(nombre);
				escribirNombre(raf,nombre);
				raf.writeInt(edad);
			}
			System.out.println("Agenda creada. Tamaño: "+raf.length()+" bytes.");
		}
	}
	
	public static void escribirNombre(RandomAccessFile raf, String nombre) throws Exception{
		char[] chars = new char[tamanyoNombre];
		for(int i=0;i<tamanyoNombre;i++) {
			if(i<nombre.length())
				chars[i] = nombre.charAt(i);
			else
				chars[i] = ' ';
		}
		for(char c: chars)
			raf.writeChar(c);
	}
	
	public static void leerRegistro(String ruta, int registro) throws Exception{
		try(RandomAccessFile raf = new RandomAccessFile(ruta,"r")){
			long posicion = tamanyoRegistro * (registro-1);
			if(posicion>=raf.length()) {
				System.out.println("El registro "+registro+" no existe.");
				System.out.println("El registro más alto es el "+raf.length()/tamanyoRegistro);
			} else {
				raf.seek(posicion);
				String nombre = leerNombre(raf);
				if(nombre.charAt(0) == charDeBorradoDeRegistro == false) {
					int edad = raf.readInt();
					System.out.printf("Registro %d: Nombre - %s | Edad - %d\n",registro, nombre, edad);
				} else
					System.out.printf("Registro %d marcado para borrar\n", registro);
			}
		}
	}
	
	public static String leerNombre(RandomAccessFile raf) throws Exception{
		String nombre = ""; 
		for(int i=0;i<tamanyoNombre;i++) {
			char c = raf.readChar();
			nombre = nombre + c;
		}
		return nombre.trim();	//trim devuelve el nombre sin espacios en blanco
	}
	
	public static void modificarRegistro(String ruta, int registro, String nombre, int edad) throws Exception{
		try(RandomAccessFile raf = new RandomAccessFile(ruta,"rw")){
			long posicion = tamanyoRegistro * (registro-1);
			if(posicion>=raf.length()) {
				System.out.println("El registro "+registro+" no existe.");
				System.out.println("El registro más alto es el "+raf.length()/tamanyoRegistro);
			} else {
				raf.seek(posicion);
				if(raf.readChar() == charDeBorradoDeRegistro == false) {
					raf.seek(posicion);
					escribirNombre(raf, nombre);
					raf.writeInt(edad);
					System.out.printf("Registro %d modificado correctamente\n", registro);
				} else
					System.out.printf("Registro %d marcado para borrar\n", registro);
			}
		}
	}
	
	
}
