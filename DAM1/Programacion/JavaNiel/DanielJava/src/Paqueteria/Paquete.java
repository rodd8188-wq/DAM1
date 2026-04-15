package Paqueteria;

import java.util.ArrayList;

public class Paquete {
	private static ArrayList<Paquete> listaPaquetes = new ArrayList<>();
	private Double peso;
	private Localizacion localizacion;
	public Paquete(Double peso, int x, int y) {
		this.peso=peso;
		this.localizacion= new Localizacion(x,y);
		Paquete.listaPaquetes.add(this);
	}
	public void mostrarPaquete(Localizacion sede) {
		System.out.println("  Paquete"+"\nDistancia:\n"+this.localizacion.distancia(sede)+"\nPeso:\n"+this.peso+"\nLocalización:\n"+this.localizacion.getX()+" "+this.localizacion.getY());
	}
	public void mostrarPaquetes() {
		int i=1;
		for(Paquete p:listaPaquetes) {
			System.out.println("  Paquete"+i+"\nPeso:\n"+p.peso+"\nLocalización:\n"+p.localizacion.getX()+" "+p.localizacion.getY());
			i++;
		}
	}
	public static ArrayList<Paquete> getListaPaquetes(){
		return listaPaquetes;
	}
	public Localizacion getLocalizacion() {
		return this.localizacion;
	}
}
