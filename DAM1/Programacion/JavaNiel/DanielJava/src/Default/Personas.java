package Default;

public class Personas {
	protected String nombre="";
	protected String apellidos="";
	public Personas(String nom, String ape) {
		this.nombre = nom;
		this.apellidos = ape;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombreCompleto() {
		return this.nombre+" "+this.apellidos;
	}
}
class Profesor extends Personas {
	public Profesor(String nom, String ape) {
		super(nom, ape);
	}
	public String getNombreCompleto() {			//Tiene prioridad sobre la función padre
		return "Señor "+super.getNombreCompleto();
	}
}
class Alumno extends Personas {
	private int edad;
	public Alumno(String nom, String ape, int edad) {
		super(nom, ape);
		this.edad = edad;
	}
}
