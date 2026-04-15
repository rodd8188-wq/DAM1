package nose2;

public class poo2 {

	public static void main(String[] args) {
        
	Pokemonxd p1 = new Pokemonxd (1,"Bulbasaur", "Planta");
	Pokemonxd p2 = new Pokemonxd (6, "Charizard", "Fuego", "Volador");
	Pokemonxd p3 = new Pokemonxd (2, "Ivysaur", "Planta");
    Pokemonxd p5 = new Pokemonxd (11, "Metapod", "Bicho");
    Pokemonxd p6 = new Pokemonxd (7, "Squirtle", "Agua");
	
	p1.mostrar();
	p2.mostrar();
	
	p1.setEvolucion(p3);  // p1 evoluciona a p3
	
	p1 = p1.evoluciona();  // aqui invoco la "funcion" que lo hara evolucionar
    p1.mostrar();
    
    p2 = p1.evoluciona();
    p2.mostrar();
        
    Pokemonxd p4 = new Pokemonxd(25, "Pikachu", "Electrico");
    
    p4.combateContra(p2); 
    p4.combateContra(p2);

    /*
    p2.mostrar();
    p4.mostrar();
    p4.combateContra(p2);
    p2.mostrar();
    p4.mostrar(); ESTO ES PARA VER LA VIDA QUE TIENEN DESPUES DEL COMBATE, EL COMBATE ES DE 1 GOLPE:)*/ 
    
    
 /* Pokemonxd[] miJuego = new Pokemonxd[100]; 
    miJuego[0] = new Pokemonxd (1,"Bulbasaur", "Planta");
    miJuego[1] = new Pokemonxd (6, "Charizard", "Fuego", "Volador");
    ESTO SON 100 POKEMONES XD
     */
   
    Equipo equipo1 = new Equipo("Jose Maria", p1, p2, p4);
    Equipo equipo2 = new Equipo("Ash", p3, p5, p6);
    
    equipo2.mostrar();
    
	}

}
