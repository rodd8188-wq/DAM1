package Examen26Feb;

public class JuegoPool {

	public static void main(String[] args) {
		//Creamos el juego con el número de participantes
		Juego juego = new Juego(456);
		//Visualizamos el panel de jugadores con todos activos
		juego.verJugadores();
		
		//Creamos la primera prueba donde se eliminan 150 jugadores
		juego.nuevaPrueba(150);
		//Visualizamos otra vez el panel viendo los jugadores restantes
		juego.verJugadores();
		
		//Segunda prueba donde eliminaremos a 200 jugadores
		juego.nuevaPrueba(250);
		//Listar otra vez los jugadores restantes
		juego.verJugadores();
		
		//Esta prueba no se ejecutara al haber mas expulsados que jugadores
		juego.nuevaPrueba(200);
		
		if(juego.numJugadoresRestantes()==1)
			System.out.println("Ya hay ganador");
	}

}
