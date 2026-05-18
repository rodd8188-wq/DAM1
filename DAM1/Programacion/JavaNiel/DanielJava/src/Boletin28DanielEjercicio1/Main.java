package Boletin28DanielEjercicio1;

public class Main {
	
	final static int DESCUENTO_10_EUROS = 1;
	final static int DESCUENTO_10_PORCIENTO = 2;
	final static int SIN_DESCUENTO = 3;

	public static void main(String[] args) {
		
		Interfaz descuento = (precio, tipo) -> {
			switch(tipo){
				case 1:
					if(precio > 30)
						return precio - 10.0;
					else
						return precio;
				case 2:
					return precio - (precio / 100.0 * 20.0);
				case 3:
					return precio;
				default:
					return -1;
			}
		};
		
		System.out.println(descuento.aplicar(100, DESCUENTO_10_PORCIENTO));
	}

}
