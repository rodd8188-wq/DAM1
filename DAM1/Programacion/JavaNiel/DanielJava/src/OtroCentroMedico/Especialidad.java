package OtroCentroMedico;

import java.util.ArrayList;
import java.util.Random;

public class Especialidad {
	private String nombre;
	private ArrayList<Medico> listaMedicos = new ArrayList<>();
	public Especialidad(String nom) {
		nombre=nom;
	}
	public void anyadirMedico(Medico med) {
		this.listaMedicos.add(med);
	}
	public String getNombre() {
		return this.nombre;
	}
	public Medico citaMedica() {
		int random = (int)(Math.random()*listaMedicos.size())+1;
		return listaMedicos.get(random);
	}
}
