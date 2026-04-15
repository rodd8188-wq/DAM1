package Bancos;

import java.util.ArrayList;

public class Sucursal {
	private String calle;
	private int numero;
	protected String ciudad;
	private int codigoPostal;
	protected String codigo;
	ArrayList<Cuenta> cuentas = new ArrayList<>();
	ArrayList<Cliente> clientes = new ArrayList<>();
	public Sucursal(Banco b, String calle, int numero, String ciudad,int codigoPostal, String codigo) {
		this.calle=calle;
		this.numero=numero;
		this.codigoPostal=codigoPostal;
		this.ciudad=ciudad;
		this.codigo=codigo;
		
		b.nuevaSucursal(this);
	}
	public void allInf() {
		System.out.println("Calle: "+calle+"\nNúmero: "+numero+"\nCiudad: "+ciudad+"\nCodigo Postal: "+codigoPostal+"\nCodigo: "+codigo);
	}
	public void listarClientes() {
		for(Cliente c: clientes) {
			System.out.println("- "+c.getApellidos()+", "+c.getNombre()+" ("+c.getNIF()+")");
		}
	}
	public void nuevoCliente(Cliente n) {
		this.clientes.add(n);
	}
	public String getCiudad() {
		return this.ciudad;
	}
	public String getCodigo() {
		return this.codigo;
	}
	public void anyadeCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}
	
}
