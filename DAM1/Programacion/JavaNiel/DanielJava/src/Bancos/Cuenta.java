package Bancos;

public class Cuenta {
	private String cliente1;
	private String cliente2;
	private double saldo;
	private String sucursal;
	private String codigo;
	public Cuenta(Cliente cliente1, Cliente cliente2, double saldo, Sucursal sucursal, String codigo) {
		this.cliente1=cliente1.nombre;
		this.cliente2=cliente2.nombre;
		this.saldo=saldo;
		this.sucursal=sucursal.ciudad;
		this.codigo=codigo;
	}
	public Cuenta(Cliente cliente1, double saldo, Sucursal sucursal, String codigo) {
		this.cliente1=cliente1.nombre;
		this.saldo=saldo;
		this.sucursal=sucursal.ciudad;
		this.codigo=codigo;
	}
	public void allInf() {
		if(cliente2==null)
			System.out.println("Cliente: "+cliente1+"\nSaldo: "+saldo+"\nSucursal: "+sucursal+"\nCodigo: "+codigo);
		else
			System.out.println("Clientes: "+cliente1+", "+cliente2+"\nSaldo: "+saldo+"\nSucursal: "+sucursal+"\nCodigo: "+codigo);
	}
}
