package Boletin25DanielEjercicio4;

public class TareasPool {

	public static void main(String[] args) {
		String ruta = "/home/alumno/A/ficheros/tareas";
		Tarea.anyadirTareasFichero(ruta);
		
		//Tarea.eliminarTarea("P20");
		
		//Tarea.marcarComoCompletada("P20");
		
		Tarea.listarTareas();
		
		//Tarea.mostrarTareasCompletadas();
		
		//Tarea.mostrarTareasNoCompletadas();
		
		Tarea.grabarFicheroTareas(ruta);
		
	}

}
