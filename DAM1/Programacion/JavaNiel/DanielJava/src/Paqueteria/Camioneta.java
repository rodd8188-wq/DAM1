package Paqueteria;

import java.util.ArrayList;
import java.util.HashMap;

public class Camioneta {
	static Localizacion sede = new Localizacion(0, 0);
	private int pesoMaximo;
	private int kmsMaximo;
	public Camioneta(int peso, int kms) {
		pesoMaximo=peso;
		kmsMaximo=kms;
	}
	public Paquete paqueteMasCercano() {
		ArrayList<Paquete> paquetes = Paquete.getListaPaquetes();
		Paquete masCercano = paquetes.get(0);
		HashMap<Paquete,Double> paquetesDistancia = new HashMap<>();
		int i=0;
		int locCercano=-1;
		do {
			i=0;
			for(Paquete p: paquetes) {
				Double distancia = sede.distancia(p.getLocalizacion());
				if (masCercano.getLocalizacion().getX()<0) {
					masCercano = p;
					locCercano = i;
				}
				else {
					if(masCercano.getLocalizacion().distancia(sede)<p.getLocalizacion().distancia(sede)) {
						masCercano = p;
						locCercano = i;
					}
				}
				i++;
			}
			paquetesDistancia.put(masCercano, masCercano.getLocalizacion().distancia(sede));
			paquetes.remove(locCercano);
			
		} while(i<paquetes.size());
		return masCercano;
	}
	public Localizacion getSede() {
		return this.sede;
	}
	public Camioneta getCamioneta() {
		return this;
	}
}
