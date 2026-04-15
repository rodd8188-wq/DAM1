package Examen9AbrilDaniel;

import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Personaje {
	//Finales estaticos
	final static int 
	tamanyoNombre = 50,
	tamanyoTitulo = 100,
	tamanyoRegistro = tamanyoNombre + tamanyoTitulo;
	final static String ruta = "/home/alumno/A/ficheros/personajes.dat";
	
	//Estaticos
	private static ArrayList<Personaje> personajes = new ArrayList<>();
	
	//Atributos
	private String tituloAnime;
	private String nombrePersonaje;
	
	//Constructor
	public Personaje(String titulo, String nombre) {
		tituloAnime = titulo;
		nombrePersonaje = nombre;
		personajes.add(this);
	}
	
	public static void MostrarFicheroBinario() {
		try(RandomAccessFile raf = new RandomAccessFile(ruta,"r")){
			for(int i=0;i<raf.length()/tamanyoRegistro;i++) {
				String titulo = leerTitulo(raf);
				String nombre = leerNombre(raf);
				System.out.printf("%s (%s)\n", nombre, titulo);
			}
		} catch (Exception e) {
			
		}
	}
	
	public static String leerTitulo(RandomAccessFile raf) throws Exception{
		String titulo = ""; 
		for(int i=0;i<tamanyoTitulo;i++) {
			char c = raf.readChar();
			titulo = titulo + c;
		}
		return titulo.trim();
	}
	
	public static String leerNombre(RandomAccessFile raf) throws Exception{
		String nombre = ""; 
		for(int i=0;i<tamanyoNombre;i++) {
			char c = raf.readChar();
			nombre = nombre + c;
		}
		return nombre.trim();
	}
	
	public static void escribirFicheroBinario() {
		try(RandomAccessFile raf = new RandomAccessFile(ruta,"rw")){
			for(Personaje personaje : personajes) {
				raf.seek(raf.length());
				escribirTitulo(raf, ruta, personaje);
				escribirNombre(raf, ruta, personaje);
			}
			//System.out.println("Escritura correcta");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void escribirTitulo(RandomAccessFile raf, String nombre, Personaje personaje) throws Exception{
		char[] chars = new char[tamanyoTitulo];
		for(int i=0;i<tamanyoTitulo;i++) {
			if(i<personaje.tituloAnime.length())
				chars[i] = personaje.tituloAnime.charAt(i);
			else
				chars[i] = ' ';
		}
		for(char c: chars)
			raf.writeChar(c);
	}
	
	public static void escribirNombre(RandomAccessFile raf, String nombre, Personaje personaje) throws Exception{
		char[] chars = new char[tamanyoNombre];
		for(int i=0;i<tamanyoNombre;i++) {
			if(i<personaje.nombrePersonaje.length())
				chars[i] = personaje.nombrePersonaje.charAt(i);
			else
				chars[i] = ' ';
		}
		for(char c: chars)
			raf.writeChar(c);
	}
	
	public static void ConstructorDePersonajesEnAnimes() {
		HashMap<String,String> animes = leerFicheroAnimes();
		HashMap<String,String> personajes = leerFicheroPersonajes();
		for(Entry<String, String> personaje : personajes.entrySet()) {
			//System.out.println(personaje);
			for(Entry<String, String> anime : animes.entrySet())
				if(personaje.getValue().equals(anime.getValue()))
					new Personaje(anime.getKey(), personaje.getKey());
		}
	}
	
	public static HashMap<String,String> leerFicheroAnimes(){
		HashMap<String,String> animes = new HashMap<>();
		try {
			ArrayList<String> lineas;
			Path fichero = Path.of("/home/alumno/A/ficheros/animes.txt");
			lineas = (ArrayList<String>) Files.readAllLines(fichero);
			for(String linea : lineas) {
				String[] datos = linea.split(" ");
				String nombreAnime = datos[1];
				if(datos.length>=3)
					for(int i=2;i<datos.length;i++)
						nombreAnime+=" "+datos[i];
				animes.put(nombreAnime, datos[0]);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return animes;
	}
	
	public static HashMap<String,String> leerFicheroPersonajes(){
		HashMap<String,String> animes = new HashMap<>();
		try {
			ArrayList<String> lineas;
			Path fichero = Path.of("/home/alumno/A/ficheros/personajes.txt");
			lineas = (ArrayList<String>) Files.readAllLines(fichero);
			for(String linea : lineas) {
				String[] datos = linea.split(" ");
				String nombre = datos[1];
				if(datos.length>=3)
					for(int i=2;i<datos.length;i++)
						nombre+=" "+datos[i];
				animes.put(nombre, datos[0]);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return animes;
	}
	
}