package CentroMedico;

import java.util.ArrayList;

public class CentroMedico {
	private String nombre;
	private String codigo;//formato CM-9999
	private ArrayList<Medico> medicos = new ArrayList<>();
	private ArrayList<Paciente> pacientes = new ArrayList<>();
	
	public CentroMedico(String nombre, String codigo) {
		this.nombre=nombre;
		this.codigo=codigo;
		if(this.codigo.matches("CM-[0-9]{4}")) {
			
		} else {
			System.out.println("El codigo de \""+this.getNombre()+"\" no esta en un formato valido ("+this.getCodigo()+").");
		}
	}
	public String getCodigo() {
		return this.codigo;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void anyadeMedico(Medico m) {
		this.medicos.add(m);
	}
	public void anyadePaciente(Paciente p) {
		this.pacientes.add(p);
	}
	public void listarMedicos() {
		System.out.println("Lista de medicos del Centro Medico de "+this.getNombre());
		System.out.println("--------------------");
		for(Medico m:medicos) {
			System.out.println("Nombre: "+m.getNombre()+"\nApellidos: "+m.getApellidos()+"\nEspecialidad: "+m.getEspecialidad()+"\nNúmero de colegiado: "+m.getNumColegiado());
			System.out.println("--------------------");
		}
	}
	public void listarPacientes() {
		System.out.println("Lista de pacientes del Centro Medico de "+this.getNombre());
		System.out.println("--------------------");
		for(Paciente p:pacientes) {
			System.out.println("Nombre: "+p.getNombre()+"\nApellidos: "+p.getApellidos());
			System.out.println("--------------------");
		}
	}
	public void eliminarMedico(Medico m) {
		this.medicos.remove(m);
	}
	public void eliminarPaciente(Paciente p) {
		this.pacientes.remove(p);
	}
}
