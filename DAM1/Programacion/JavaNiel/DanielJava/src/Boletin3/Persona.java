package Boletin3;

abstract class Persona {
	
	protected String nombre="";
	
	public Persona(String nom) {
		this.nombre = nom;
	}
}
	
class Profesor extends Persona {
	private String departamento;
	private String apellidos;
	private String grupo;
	public Profesor(String nom, String apellidos, String grupo, String departamento) {
		super(nom);
		this.apellidos=apellidos;
		if(departamento.equalsIgnoreCase("Informática")||departamento.equalsIgnoreCase("Empresa")||departamento.equalsIgnoreCase("Inglés")) {
			this.departamento=departamento.toUpperCase();
		}
		else
			this.departamento="Departamento no valido";
		if(grupo.equals(""))
			this.grupo="No tiene";
		else
			this.grupo=grupo;
	}
	public void allInf() {
		System.out.println("Nombre: "+nombre+"\nApellidos: "+apellidos+"\nGrupo: "+grupo+"\nDepartamento: "+departamento);
	}
}
	
class Alumno extends Persona {
	private int edad;
	private String ciclo;
	private String modulo;
	private String apellidos;
	private String grupo;
	private boolean mayorEdad;
	public Alumno(String nom, String apellidos, String grupo, int edad, String ciclo, String modulo) {
		super(nom);
		this.edad=edad;
		this.ciclo=ciclo;
		this.modulo=modulo;
		this.apellidos=apellidos;
		this.grupo=grupo;
		if(this.edad>=18)
			mayorEdad = true;
		else
			mayorEdad = false;
	}
	
	public void allInf() {
		System.out.println("Nombre: "+nombre+"\nApellidos: "+apellidos+"\nGrupo: "+grupo+"\nEdad: "+this.edad+
				"\nCiclo: "+ciclo+"\nModulo: "+modulo);
	}
	public void mayorEdad() {
		if(mayorEdad==true)
			System.out.println("Tiene la mayoria de edad.");
		else
			System.out.println("Es menor de edad");
	}
}

class Modulo extends Persona {
	private int anyo;
	private int horasLectivas;
	private String optativa;
	public Modulo(String nom,int anyo,int horasLectivas,String optativa) {
		super(nom);
		this.anyo=anyo;
		this.horasLectivas=horasLectivas;
		if(optativa.equals("Si")||optativa.equals("No")||optativa.equals("SI")||optativa.equals("NO"))
			this.optativa=optativa;
		else
			this.optativa=null;
	}
	public int getCurso() {
		return this.anyo;
	}
	
	public void allInf() {
		System.out.println("Nombre: "+this.nombre+"\nAño: "+this.anyo+"\nHoras lectivas: "+this.horasLectivas+"\nOptativa: "+this.optativa);
	}
}

class Ciclo extends Persona {
	private Modulo[] primero = new Modulo[8];
	private Modulo[] segundo = new Modulo[8];
	private String nombre;
	private String grado;
	private int numModulosPrimero=0;
	private int numModulosSegundo=0;
	public Ciclo(String nom,String grado) {
		super(nom);
		this.grado=grado;
	}
	
	public void anyadeModulo(Modulo m) {
		if(m.getCurso()==1) {
			primero[this.numModulosPrimero]=m;
			numModulosPrimero++;
		}
		else {
			segundo[this.numModulosSegundo]=m;
			numModulosSegundo++;
		}
	}
	
	public void allInf() {
		System.out.println();
	}
}

class Grupo extends Persona {
	private Ciclo ciclo;
	private int curso;
	private int numAlumnos;
	private Alumno[] listaAlumnos;
	
	public Grupo(String nom, Ciclo ciclo, int curso, int numAlumnos) {
		super(nom);
		this.ciclo=ciclo;
		this.curso=curso;
		this.numAlumnos=numAlumnos;
		this.listaAlumnos=new Alumno[numAlumnos];
	}
	
	public void anyadeTutor(Profesor tutor) {
		
	}
	
	public void anyadeAlumno(Alumno alumno) {
		
	}
	
	public void allInf() {
		System.out.println();
	}
}

	
