package OtroCentroMedico;

import java.util.ArrayList;

public class Medico {
	private String nombre;
	private Especialidad especialidad;
	private ArrayList<Paciente> listaPacientes = new ArrayList<>();
	public Medico(String nom, Especialidad esp) {
		nombre=nom;
		especialidad=esp;
		this.especialidad.anyadirMedico(this);
	}
	public void anyadirPaciente(Paciente pac) {
		this.listaPacientes.add(pac);
	}
	public void mostrarListaPacientes() {
		for(Paciente c: listaPacientes) {
			System.out.println(c.getNombre());
		}
	}
}
