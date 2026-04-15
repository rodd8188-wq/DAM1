package OtroCentroMedico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Paciente {
	private String nombre;
	private HashMap<Medico,Especialidad> listaCitas = new HashMap<>(); 
	public Paciente(String nom) {
		nombre=nom;
	}
	public void pedirCita(Especialidad esp) {
		this.listaCitas.put(esp.citaMedica(),esp);
	}
	public String getNombre() {
		return this.nombre;
	}
	public void mostrarCitas() {
		for (Entry<Medico,Especialidad> entry:listaCitas.entrySet()) {
			System.out.printf("%s: %.2f\n",entry.getKey(),entry.getValue());
		}
	}
}
