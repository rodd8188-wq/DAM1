package poo2;

import java.time.LocalDate;
import java.util.Arrays;

public class Tarea {
	private String titulo;
	private String descripcion;
	private String color;
	private LocalDate fecha;
	private boolean completada = false;
	private static Tarea[] lista = null;

	public Tarea(String tit,String desc,String col) {
		this.titulo=tit;
		this.descripcion=desc;
		this.color=col;
		this.fecha=LocalDate.now();
		if(lista == null) {
			// inicializo la lista con un elemento y copio en él la tarea
			lista = new Tarea[1];
			lista[0] = this;
		}
		else {
			// aumento en una la lista y copio en él la tarea
			lista = Arrays.copyOf(lista, lista.length+1);
		}
	}
	public void marcarComoCompletada() {
		completada=true;
	}
	
	public static void mostrarTareasNoCompletadas() {
		for(Tarea tarea: lista)
			if(tarea.completada == false)
				tarea.mostrar();
	}
	
	public static void mostarTareas() {
		for(Tarea tarea: lista)
			tarea.mostrar();
	}
	
	public void mostrar() {
		System.out.println(titulo+"("+color+")");
		System.out.println(descripcion);
		System.out.println("Fecha: "+fecha+" - Completada: "+completada);
		System.out.println("---------------------------------------------");
	}
}