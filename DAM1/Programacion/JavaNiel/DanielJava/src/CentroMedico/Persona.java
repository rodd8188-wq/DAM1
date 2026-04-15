package CentroMedico;

import java.util.ArrayList;

abstract class Persona {
	protected String nombre;
	protected String apellidos;
	protected CentroMedico centro;
	
	public Persona(String nombre, String apellidos, CentroMedico centro) {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.centro=centro;
	}
	public String getNombre() {
		return this.nombre;
	}
	public String getApellidos() {
		return this.apellidos;
	}
	abstract public void cambiaCentro(CentroMedico c);
}
	
class Medico extends Persona {
	private String especialidad;
	private ArrayList<Consulta> consultas = new ArrayList<>();
	private int numColegiado;//formato seis dígitos numéricos
	public Medico(String nombre, String apellidos, CentroMedico centro, String esp, int numCol) {
		super(nombre,apellidos,centro);
		especialidad=esp;
		numColegiado=numCol;
		String numTxt = Integer.toString(numColegiado);
		this.centro.anyadeMedico(this);
		if(numTxt.matches("[0-9]{6}")==true) {
			
		} else {
			System.out.println("El codigo de \""+this.nombre+" "+this.apellidos+"\" no esta en un formato valido ("+this.numColegiado+").");
		}
	}
	public void cambiaCentro(CentroMedico c) {
		this.centro.eliminarMedico(this);
		this.centro=c;
		this.centro.anyadeMedico(this);
	}
	public void anyadeConsulta(Consulta c) {
		this.consultas.add(c);
	}
	public void listarConsultas() {
		System.out.println("Lista de consultas del medico "+this.getNombre()+" "+this.getApellidos());
		System.out.println("--------------------");
		for(Consulta c:consultas) {
			System.out.println("Motivo de la consulta: "+c.motivoConsulta+"\nFecha: "+c.getFecha()+"\nPaciente que la realizó: "+c.paciente.getNombre()+" "+c.paciente.getApellidos()+"\nConsejo medico: "+c.getConsejoMedico());
			System.out.println("--------------------");
		}
	}
	public String getEspecialidad() {
		return this.especialidad;
	}
	public int getNumColegiado() {
		return this.numColegiado;
	}
}
class Paciente extends Persona {
	private String dni;
	private int telefono;
	private ArrayList<Consulta> consultas = new ArrayList<>();
	public Paciente(String nombre, String apellidos, CentroMedico centro, String dni, int telef) {
		super(nombre,apellidos,centro);
		this.dni=dni;
		telefono=telef;
		this.centro.anyadePaciente(this);
	}
	public void cambiaCentro(CentroMedico c) {
		this.centro.eliminarPaciente(this);
		this.centro=c;
		this.centro.anyadePaciente(this);
	}
	public void anyadeConsulta(Consulta c) {
		this.consultas.add(c);
	}
	public void listarConsultas() {
		System.out.println("Lista de consultas del paciente "+this.getNombre()+" "+this.getApellidos());
		System.out.println("--------------------");
		for(Consulta c:consultas) {
			System.out.println("Motivo de la consulta: "+c.motivoConsulta+"\nFecha: "+c.getFecha()+"\nMedico a cargo: "+c.medico.getNombre()+" "+c.medico.getApellidos()+"\nConsejo medico: "+c.consejoMedico);
			System.out.println("--------------------");
		}
	}
}

