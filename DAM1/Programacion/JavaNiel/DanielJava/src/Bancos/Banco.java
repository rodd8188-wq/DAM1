package Bancos;

import java.util.ArrayList;

public class Banco {
	protected String nombre;
	protected String codigo;
	ArrayList<Sucursal> sucursales = new ArrayList<>();
	public Banco(String nombre, String codigo) {
		this.nombre=nombre;
		this.codigo=codigo;
	}
	public void allInf() {
		System.out.println("Nombre: "+nombre+"\nCodigo: "+codigo);
	}
	public void nuevaSucursal(Sucursal s) {
		this.sucursales.add(s);
	}
	public void listarSucursales() {
		System.out.println("Banco: "+this.nombre+" / Codigo: ("+this.codigo+")");
		for(Sucursal s: sucursales) {
			System.out.println("-"+s.getCiudad()+"("+s.getCodigo()+")");
		}
	}
}
