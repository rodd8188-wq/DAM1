package Examen2;

import java.util.ArrayList;

public class Empleados {
	private String nombre;
	private double salarioBase;
	ArrayList<Empleados> programadores = new ArrayList<>();
	public Empleados(String nom, double sal) {
		nombre=nom;
		salarioBase=sal;
	}
	public String getNombre() {
		return this.nombre;
	}
	public double getSalario() {
		return this.salarioBase;
	}
}
class Programador extends Empleados {
	private String codigo;
	private boolean java;
	private boolean python;
	public Programador(String nom, double sal, boolean java, boolean python) {
		super(nom,sal);
		this.java=java;
		this.python=python;
		this.programadores.add(this);
		int num = programadores.indexOf(this);
		codigo="EMP-"+Integer.toString(num)+1;
	}
	public void allInf() {
		System.out.println("Nombre: "+this.getNombre()+"\nCodigo: "+this.codigo+"\nSalario: "+this.getSalario()+"€\nJava: "+this.java+"\nPython: "+this.python);
	}
	public String getCodigo() {
		return this.codigo;
	}
	public void calcularSalario() {
		double salario=this.getSalario();
		if(this.java==true)
			salario+=200;
		if(this.python==true)
			salario+=200;
		System.out.println("Salario: "+salario);
	}
}
class JefeProyecto extends Empleados{
	private String codigo;
	public JefeProyecto(String nom, double sal) {
		super(nom,sal);
		this.programadores.add(this);
		int num = programadores.indexOf(this);
		codigo="EMP-"+Integer.toString(num)+1;
	}
	public String getCodigo() {
		return this.codigo;
	}
	public void allInf() {
		System.out.println("Nombre: "+this.getNombre()+"\nCodigo: "+this.codigo+"\nSalario: "+this.getSalario()+"€");
	}
}
