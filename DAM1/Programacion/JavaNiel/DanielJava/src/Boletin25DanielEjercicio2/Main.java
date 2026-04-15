package Boletin25DanielEjercicio2;

public class Main {

	public static void main(String[] args) {
		
		Sucursal s1 = new Sucursal("Calle de Las Margaritas", "Madrid");
		
		Cliente cliente1 = new Cliente("Daniel", "Rodriguez", "00034817A", s1);
		Cliente cliente2 = new Cliente("Paula", "Briceño", "04569817A", s1);
		
		CuentaCorriente c1 = new CuentaCorriente(1000.00, cliente1, s1);
		CuentaCorriente c2 = new CuentaCorriente(1000.00, cliente2,cliente1, s1);
		CuentaCorriente c3 = new CuentaCorriente(1000.00, cliente1, s1);
		CuentaCorriente c4 = new CuentaCorriente(1000.00, cliente2, s1);
		
		cliente1.mostrarCuentas();
		
	}

}
