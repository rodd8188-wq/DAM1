package ProyectoFut;

public class FutPool {
	
	public static void main(String[] args) {
		
		Competicion laliga = new Competicion("La Liga eaSports");
		Competicion premier = new Competicion("Premier League");
		
		Equipo realMadrid = new Equipo("Real Madrid FC",laliga);
		Equipo realBetis = new Equipo("Real Betis FC",laliga);
		Equipo barca = new Equipo("Barcelona FC",laliga);
		Equipo rayo = new Equipo("Rayo Vallecano",laliga);
		Equipo sevilla = new Equipo("Sevilla",laliga);
		
		Jugador jugador1 = new Jugador("Cristiano",7,realMadrid);
		Jugador jugador2 = new Jugador("Joaquín",12,realBetis);
		Jugador jugador3 = new Jugador("Eduardo",16,realMadrid);
		Jugador jugador4 = new Jugador("Leo",10,barca);
		Jugador jugador5 = new Jugador("Sergio",11,barca);
		Jugador jugador6 = new Jugador("Dani",14,realMadrid);
		
		Arbitro arbitro1 = new Arbitro("Alexandru");
		Arbitro arbitro2 = new Arbitro("Andrea");
		
		Entrenador entrenador1 = new Entrenador("Simeone",realMadrid);
		
		realMadrid.listarJugadores();
		
	}

}
