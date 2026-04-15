package Bancos;

public class Cliente {
	protected String nombre;
	private String apellidos;
	private String nif;
	private int telefono;
	private Sucursal sucursal;
	public Cliente(String nombre, String apellidos, String nif, int telefono, Sucursal sucursal) {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.nif=nif;
		this.telefono=telefono;
		this.sucursal=sucursal;
		this.sucursal.anyadeCliente(this);
		
	}
	public void allInf() {
		System.out.println("Nombre: "+nombre+"\nApellidos: "+apellidos+"\nNIF: "+nif+"\nTelefono: "+telefono+"\nSucursal: "+sucursal.ciudad);
	}
	public String getApellidos() {
		return this.apellidos;
	}
	public String getNombre() {
		return this.nombre;
	}
	public String getNIF() {
		return this.nif;
	}
	
}
