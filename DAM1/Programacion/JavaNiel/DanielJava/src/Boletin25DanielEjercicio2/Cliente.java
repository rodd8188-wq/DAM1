package Boletin25DanielEjercicio2;

import java.io.Serializable;
import java.util.HashMap;

public class Cliente implements Serializable {
	
	private static HashMap<String, Cliente> clientes = new HashMap<>();
	
	private String nombre;
	private String apellidos;
	private String nif;
	private Sucursal sucursalAlta;
	
	public Cliente(String nom, String ape, String nif, Sucursal suc)  {
		nombre = nom;
		apellidos = ape;
		this.nif = nif;
		sucursalAlta = suc;
		Cliente.clientes.put(this.nif,this);
	}
	
	public void mostrarCuentas() {
		int contadorMostradas = 0;
		System.out.println(" - CUENTAS DE " + this.nif + " - ");
		for(CuentaCorriente cuenta: CuentaCorriente.getCuentas().values()) {
			if(this == cuenta.getCliente1()) {
				if(contadorMostradas>0)
					System.out.println();
				System.out.println("IBAN  | " + cuenta.getCodigo());
				System.out.println("Saldo | " + cuenta.getSaldo() + " €");
				contadorMostradas++;
			} else if(this == cuenta.getCliente2()) {
				if(contadorMostradas>0)
					System.out.println();
				System.out.println("IBAN  | " + cuenta.getCodigo());
				System.out.println("Saldo | " + cuenta.getSaldo() + " €");
				contadorMostradas++;
			}
		}
	}
}
