package Bancos;

public class Main {

	public static void main(String[] args) {
		Banco banco = new Banco("Creditos Informáticos","ES68 1234");
		Sucursal madrid = new Sucursal(banco,"Calle del Pez",5,"Madrid",28032,"1078");
		Sucursal sevilla = new Sucursal(banco,"Calle Lanza",7,"Sevilla",41012,"9523");
		Cliente c1 = new Cliente("José María","Morales","22999333P",913277200, madrid);
		Cliente c2 = new Cliente("Daniel","Rodriguez","28777555X",954212270, sevilla);
		Cliente c3 = new Cliente("David","Briceño","2454364G",956785890, madrid);
		Cuenta cuenta1 = new Cuenta(c1, 100.00, madrid, "112233445566");
		Cuenta cuenta2 = new Cuenta(c1, c2, 300.44, sevilla, "223344556677");
		
		///Mostrar toda la información
		//c1.allInf();
		
		///Listar las sucursales de un banco
		//banco.listarSucursales();
		
		///Listar clientes
		madrid.listarClientes();
		
		
		
	}
}
