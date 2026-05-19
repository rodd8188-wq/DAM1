package Boletin28DanielEjercicio2;

class Druida extends Personaje implements Guerrero, Mago{
	public Druida(String nombre) {
		super(nombre);
	}
	
	@Override
    public int[] getAtributo() {
        return this.atributo;
    }
}
