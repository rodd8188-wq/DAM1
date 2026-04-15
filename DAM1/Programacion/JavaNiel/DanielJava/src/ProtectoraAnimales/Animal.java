package ProtectoraAnimales;

abstract class Animal {
	protected int anyoNacimiento;
	protected boolean adoptado=false;
	public Animal(int anyoNac, boolean adop) {
		this.anyoNacimiento=anyoNac;
	}
}

class Perro extends Animal{
	private boolean vacunado;
	private String nombre=null;
	public Perro(String nom, int anyoNac, boolean adop, boolean vac) {
		super(anyoNac,adop);
		this.nombre=nom;
		this.vacunado=vac;
	}
	public Perro(int anyoNac, boolean adop, boolean vac) {
		super(anyoNac,adop);
		this.vacunado=vac;
	}
	public void allInf() {
		System.out.println("---------------------");
		System.out.println("Nombre: "+this.nombre+"\nAnyo nacimiento: "+this.anyoNacimiento+"\nAdoptado: "+this.adoptado+"\nVacunado: "+this.vacunado);
		System.out.println("---------------------");
	}
}

class Gato extends Animal{
	private boolean vacunado;
	private String nombre;
	public Gato(String nom, int anyoNac, boolean adop, boolean vac) {
		super(anyoNac,adop);
		this.nombre=nom;
		this.vacunado=vac;
	}
	public Gato(int anyoNac, boolean adop, boolean vac) {
		super(anyoNac,adop);
		this.vacunado=vac;
	}
	public void allInf() {
		System.out.println("---------------------");
		System.out.println("Nombre: "+this.nombre+"\nAnyo nacimiento: "+this.anyoNacimiento+"\nAdoptado: "+this.adoptado+"\nVacunado: "+this.vacunado);
		System.out.println("---------------------");
	}
}

class Tortuga extends Animal{
	private String nombre;
	private String tipo;
	public Tortuga(String nom,int anyoNac, boolean adop, String tipo) {
		super(anyoNac,adop);
		this.nombre=nom;
		this.tipo=tipo;
	}
	public Tortuga(int anyoNac, boolean adop, String tipo) {
		super(anyoNac,adop);
		this.tipo=tipo;
	}
	public void allInf() {
		System.out.println("---------------------");
		System.out.println("Nombre: "+this.nombre+"\nAnyo nacimiento: "+this.anyoNacimiento+"\nAdoptado: "+this.adoptado+"\nTipo: "+this.tipo);
		System.out.println("---------------------");
	}
}
