package PokemonPack;

public class Equipo {

	private String entrenador;
	private Pokemon[] equipo = new Pokemon[3];
	
	public Equipo(String entrenador, Pokemon p1, Pokemon p2, Pokemon p3) {
		this.entrenador = entrenador;
		this.equipo[0] = p1;
		this.equipo[1] = p2;
		this.equipo[2] = p3;
	}
	
	public void mostrar() {
		System.out.println("Entrenador: "+this.entrenador);
		for(int i=0;i<3;i++)
			this.equipo[i].mostrar();
	}

}