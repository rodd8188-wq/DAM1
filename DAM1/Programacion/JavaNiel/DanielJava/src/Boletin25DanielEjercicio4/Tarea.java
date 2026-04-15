package Boletin25DanielEjercicio4;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class Tarea {
	private static ArrayList<Tarea> tareas = new ArrayList<>();
	private String identificador;
	private String titulo;
	private int prioridad;
	private boolean realizada;
	//	Id	Titulo	Prioridad(1-9)	Realizada(0 = false, 1 = true)
	public Tarea(String id, String titulo, int prioridad, boolean realizada) {
		this.identificador=id;
		this.titulo=titulo;
		this.prioridad=prioridad;
		this.realizada=realizada;
		
		boolean repetido=false;
		for(Tarea tarea: tareas) {
			if(tarea.identificador.equals(this.identificador))
				repetido=true;
		}
		if(repetido==true)
			System.out.println("Error: ID "+this.identificador+" ya existente.");
		else {
			tareas.add(this);
			System.out.printf("Tarea '%s' (ID: %s) añadida.\n", this.titulo, this.identificador);
			Tarea.ordenarTareas();
		}
		
	}
	
	public static void anyadirTareasFichero(String ruta) {
		try {
			ArrayList<String> lineas;
			Path fichero = Path.of(ruta);
			lineas = (ArrayList<String>) Files.readAllLines(fichero);
			for(String linea: lineas) {
				String array[] = linea.split(",");
				//System.out.println(array[0]+" "+array[1]+" "+array[2]+" "+array[3]);
				String id = array[0];
				String titulo = array[1];
				int prioridad = Integer.valueOf(array[2]);
				boolean realizada = false;
				if(array[3].equals("1"))
					realizada = true;
				
				//Nueva tarea
				new Tarea(id, titulo, prioridad, realizada);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Por si hay que automatizar los IDs
	public static String contadorIdentificador() {
		int contador=0;
		contador++;
		String numId = Integer.toString(contador);
		String id ="P"+numId;
		return id;
	}
	
	public static void listarTareas() {
		System.out.println("  -  LISTADO DE TAREAS:");
		for(Tarea tarea: tareas) {
			if(tarea.realizada)
				System.out.print(" X ");
			else
				System.out.print("   ");
			System.out.printf("[%s] %s (Prioridad: %d)\n",tarea.identificador,tarea.titulo,tarea.prioridad);
		}
	}
	
	public static void eliminarTarea(String id) {
		boolean encontrada = false;
		int contadorPos = 0;
		try {
			for(Tarea tarea: tareas) {
				if(id.equals(tarea.identificador)) {
					encontrada = true;
					tareas.remove(contadorPos);
					System.out.printf("Tarea con ID %s ('%s') eliminada.\n", tarea.identificador, tarea.titulo);
				}
				contadorPos++;
			}
		} catch(Exception e) {
			
		} finally {
			if(encontrada==false)
				System.out.printf("Error: No se encontró una tarea con ID %s\n", id);
		}
	}
	
	public static void marcarComoCompletada(String id) {
		boolean encontrada = false;
		try {
			for(Tarea tarea: tareas) {
				if(id.equals(tarea.identificador)) {
					encontrada = true;
					tarea.realizada=true;
					System.out.printf("Tarea ID %s '%s' marcada como completada\n",tarea.identificador,tarea.titulo);
				}
			}
		} catch(Exception e) {
			
		} finally {
			if(encontrada==false)
				System.out.printf("Error: No se encontró una tarea con ID %s\n", id);
		}
	}
	
	public static void mostrarTareasCompletadas() {
		System.out.println(" - LISTADO DE TAREAS COMPLETADAS:");
		int contTareas = 0;
		for(Tarea tarea: tareas) {
			if(tarea.realizada) {
				System.out.printf("[%s] %s (Prioridad: %d)\n",tarea.identificador,tarea.titulo,tarea.prioridad);
				contTareas++;
			}
		}
		if(contTareas==0)
			System.out.println("No hay tareas completadas");
	}
	
	public static void mostrarTareasNoCompletadas() {
		System.out.println(" - LISTADO DE TAREAS NO COMPLETADAS:");
		int contTareas = 0;
		for(Tarea tarea: tareas) {
			if(tarea.realizada==false) {
				System.out.printf("[%s] %s (Prioridad: %d)\n",tarea.identificador,tarea.titulo,tarea.prioridad);
				contTareas++;
			}
		}
		if(contTareas==0)
			System.out.println("No hay tareas no completadas");
	}
	
	public static void grabarFicheroTareas(String ruta) {
		try {
			PrintWriter pluma = new PrintWriter(ruta);
			for(Tarea tarea: tareas) {
				int completada = 0;
				if(tarea.realizada)
					completada = 1;
				pluma.printf("%s,%s,%d,%d",tarea.identificador,tarea.titulo,tarea.prioridad,completada);
				pluma.println();
			}
			pluma.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private static void ordenarTareas() {
		if(tareas.size()>=3) {
			boolean cambio;
			do {
				cambio = false;
				for (int i = 0; i <= tareas.size() - 2; i++) {
						int pos1 = i;
						int pos2 = i + 1;
						if ( tareas.get(i).prioridad >  tareas.get(i + 1).prioridad) {
							Collections.swap(tareas, pos1, pos2);
							cambio = true;
						}
				}
			} while (cambio == true);
		} else if(tareas.size()==2) {
			if(tareas.get(0).prioridad>tareas.get(1).prioridad)
				Collections.swap(tareas, 0, 1);
		}
	}
	
}
