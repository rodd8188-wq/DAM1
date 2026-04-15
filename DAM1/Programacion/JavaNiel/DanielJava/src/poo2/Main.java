package poo2;

public class Main {

	public static void main(String[] args) {
		Tarea t1 = new Tarea("Aprender Java","Estudiar POO para aprobar el segundotrimestre","verde");
		Tarea t2 = new Tarea("Stranger Things","Ver la última temporada antes de que me frían a spoilers","amarillo");
		Tarea t3 = new Tarea("Salir a hacer deporte","Hay que bajar ese roscón de reyes","rojo");
		
		//t1.mostrar();
		//t2.mostrar();
		//t2.marcarComoCompletada();
		//Tarea.mostarTareasNoCompletadas();
		Tarea.mostarTareas();
	}

}
