package Boletin25DanielEjercicio2;

import java.io.Serializable;
import java.util.HashMap;

public class CuentaCorriente implements Serializable {
	
	private static int contCodigo = 0;	//12 Digitos
	private static HashMap<String, CuentaCorriente> cuentas = new HashMap<>();
	
	private String codigo = "";
	private Double saldo;
	private Cliente[] titular = new Cliente[2];
	private Sucursal sucursalAbierta;
	
	//En caso de un solo titular
	public CuentaCorriente(Double saldo, Cliente cliente1, Sucursal sucursal) {
		try {
			//IBAN
			this.saldo = Math.round(saldo * 100.0) / 100.0;
			titular[0] = cliente1;
			titular[1] = null;
			sucursalAbierta = sucursal;
			this.codigo = "ES68" + this.sucursalAbierta.getCodigo() + montarCodigo12Digitos();
			CuentaCorriente.cuentas.put(this.codigo,this);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	//En caso de varios titulares
	public CuentaCorriente(Double saldo, Cliente cliente1, Cliente cliente2, Sucursal sucursal) {
		try {
			//IBAN
			this.saldo = Math.round(saldo * 100.0) / 100.0;
			titular[0] = cliente1;
			titular[1] = cliente2;
			sucursalAbierta = sucursal;
			this.codigo = "ES68" + this.sucursalAbierta.getCodigo() + montarCodigo12Digitos();
			CuentaCorriente.cuentas.put(this.codigo,this);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String montarCodigo12Digitos() {
		CuentaCorriente.contCodigo++;
		String cod = Integer.toString(CuentaCorriente.contCodigo);
		String codigoDigitosRestantes = "";
		if(cod.length()<12) {
			for(int i=0;i<12-cod.length();i++) {
				codigoDigitosRestantes += "0";
			}
		}
		return codigoDigitosRestantes += cod;
	}
	
	public Double getSaldo() {
		return this.saldo;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public Cliente getCliente1() {
		return this.titular[0];
	}
	
	public Cliente getCliente2() {
		return this.titular[1];
	}
	
	public static HashMap<String, CuentaCorriente> getCuentas(){
		return CuentaCorriente.cuentas;
	}
}
