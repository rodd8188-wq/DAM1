package Boletin25DanielEjercicio2;

import java.io.Serializable;
import java.util.HashMap;

public class Sucursal implements Serializable {
	
	private static int contCodigo = 0;	//4 Digitos
	private static HashMap<String, Sucursal> sucursales = new HashMap<>();
	
	private String direccion;
	private String provincia;
	private String codigo;
	
	public Sucursal(String dir, String prov) {
		Sucursal.contCodigo++;
		String cod = Integer.toString(Sucursal.contCodigo);
		String codigo4Digitos = "";
		if(cod.length()<4) {
			for(int i=0;i<4-cod.length();i++) {
				codigo4Digitos += "0";
			}
		}
		this.codigo = (codigo4Digitos += cod);
		direccion = dir;
		provincia = prov;
		Sucursal.sucursales.put(this.codigo,this);
	}
	
	public String getCodigo(){
		return this.codigo;
	}
}
