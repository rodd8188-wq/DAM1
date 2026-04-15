package ProtectoraAnimales;

public class Cliente {
	private String nombre;
	private String apellidos;
	private int edad;
	private int telefono;
	private String animalInteresado;
	private Perro[] perros = new Perro[3];
	private Gato[] gatos = new Gato[2];
	private Tortuga[] tortugas = new Tortuga[1];
	
	public Cliente(String nom,String ape, int edad, int tlf, String interes, Perro perros, Gato gatos, Tortuga tortugas) {
		this.nombre=nom;
		this.apellidos=ape;
		this.edad=edad;
		this.telefono=tlf;
		this.animalInteresado=interes;
		this.gatos[0]=gatos;
		this.perros[0]=perros;
		this.tortugas[0]=tortugas;
	}
	public void allInf() {
		System.out.println("--------------------------------");
		System.out.println("Nombre: "+this.nombre+"\nApellidos: "+this.apellidos+"\nEdad: "+this.edad+"\nTelefono: "+this.telefono+"\nInteres: "+this.animalInteresado+
				"\nPerros: "+"\nGatos: "+"\nTortugas: ");
		System.out.println("--------------------------------");
	}
}
