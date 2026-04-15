package Tinder;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

abstract class Persona {
	protected boolean tengoPreferenciasDeEdad;
	protected String nombre;
	protected int edad;
	protected int edadMinima=18;
	protected int edadMaxima=130;
	protected int interes;//0 Ambos - 1 Hombre - 2 Mujer
	protected LocalDate fechaNacimiento;
	
	public Persona(String nombre, String fechaNac, int interes) {
		this.nombre=nombre;
		this.interes=interes;
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.fechaNacimiento = LocalDate.parse(fechaNac, formato);
		this.tengoPreferenciasDeEdad=false;
	}
	public Persona(String nombre, String fechaNac, int interes, int edadMin, int edadMax) {
		this(nombre,fechaNac,interes);
		edadMinima=edadMin;
		edadMaxima=edadMax;
		this.interes=interes;
		if(edadMinima < 18)
			edadMinima=18;
		this.tengoPreferenciasDeEdad=true;
	}
	public boolean esMatch(Persona persona) {
		boolean matchThis= false;
		boolean matchPersona= false;
		boolean matchBoth= false;
		if(this.tengoPreferenciasDeEdad==true) {
			if(persona.getEdad()<this.edadMaxima && persona.getEdad()>this.edadMinima) {
				matchThis=true;
			}
		} else if(this.tengoPreferenciasDeEdad==false) {
			matchThis=true;
		}
		if(persona.tengoPreferenciasDeEdad==true) {
			if(this.getEdad()<persona.edadMaxima && this.getEdad()>persona.edadMinima) {
				matchPersona=true;
			}
		} else if(persona.tengoPreferenciasDeEdad==false) {
			matchPersona=true;
		}
		if(matchPersona==true && matchThis==true) {
			matchBoth=true;
		}
		return matchBoth;
	}
	
	public void mostrarDatos() {
		System.out.printf("Nombre: %s. Edad: %d\n",this.nombre,this.getEdad());
		if(this instanceof Hombre)
			System.out.print("Soy un hombre que busco ");
		else if(this instanceof Mujer)
			System.out.print("Soy una mujer que busca ");
		else
			System.out.print("No tengo una identidad sexual y busco ");
		switch(this.interes) {
		case 0:
			System.out.println("a una persona sin importar su orientación sexual.");
			break;
		case 1:
			System.out.println("a un hombre.");
			break;
		case 2:
			System.out.println("a una mujer.");
			break;
		}
		if(this.tengoPreferenciasDeEdad==true)
			System.out.println("Y mis preferencias de edad estan entre "+this.edadMinima+" y "+this.edadMaxima+".");
		else
			System.out.println("Y no tengo preferencias en cuanto a la edad de mi pareja.");
		System.out.println();
	}
	public int getInteres() {
		return this.interes;
	}
	public String getNombre() {
		return this.nombre;
	}
	public int getEdad() {
		int edadPersona=18;
		LocalDate fechaHoy = LocalDate.now();
		Period periodo = Period.between(this.fechaNacimiento, fechaHoy);
		return periodo.getYears();
	}
}
