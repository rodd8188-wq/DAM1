package Boletin28DanielEjercicio2;

public interface Guerrero { 	// Fuerza(0) + Destreza(1)
    
    int[] getAtributo();
    
    public default int golpear() { 
        int fuerza = this.getAtributo()[0];
        int destreza = this.getAtributo()[1];
        
        return (int)(Math.random() * ((fuerza + destreza) - 5 + 1)) + 5;
    }
}
