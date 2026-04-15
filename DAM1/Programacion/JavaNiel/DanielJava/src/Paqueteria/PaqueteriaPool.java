package Paqueteria;

public class PaqueteriaPool {
	public static void main(String[] args) {
		
		Paquete p1 = new Paquete(20.7,5,8);
		Paquete p2 = new Paquete(2.1,6,7);
		Paquete p3 = new Paquete(3.5,10,15);
		Paquete p4 = new Paquete(40.2,2,25);
		
		Camioneta c1 = new Camioneta(100,100);
		
		c1.paqueteMasCercano().mostrarPaquete(Camioneta.sede);
		
	}
}
