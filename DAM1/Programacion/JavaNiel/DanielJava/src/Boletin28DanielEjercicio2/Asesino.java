package Boletin28DanielEjercicio2;

class Asesino extends Personaje implements Guerrero, Ladron{
	public Asesino(String nombre) {
		super(nombre);
	}
	
	@Override
    public int[] getAtributo() {
        return this.atributo;
    }
}