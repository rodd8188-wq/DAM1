package nose;

public class Persona {
	
	//ESTO SON LOS DATOS, ATRIBUTOS:
	//EL PRIVATE ES PARA QUE ESOS DATOS SOLO SE PUEDEN MANEJAR DENTRO DE LA CLASE.
	private String nombre;
	private String apellidos;
	private int edad;
	//Se puede inicilizar los datos aqui pero el constructor lo hace, asi que no es necesario.
	private static int numPersonas = 0; //Se pone static porque es comun a todos los objetos
	
/*	public int getnumPersonas() {
		return numPersonas;
	} */
	
	public Persona(String nom, String ape) { //Este es el constructor, el dato que no considere importante no lo pongo, por ejemplo la edad
		//Estos constructores se tienen que llamar exactamente igual que la clase
		this.nombre = nom;   //Este this sirve para indicar que esto pertenece solo y exclusivo al objeto
		this.apellidos = ape;
		numPersonas++;
	}
	
	public Persona(String nom, String ape, int edad) { //Este es otro constructor, si llamamos 2 strings y 1 entero, llamaremos a este constructor.
		this.nombre = nom;
		this.apellidos = ape;
		this.edad = edad;
		numPersonas++;
	}

	public static int getNumPersonas() {
		return numPersonas;
	}

/*	public static void setNumPersonas(int numPersonas) {
		Persona.numPersonas = numPersonas;
	} */
	
/*	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	para hacer todo esto hacemos "source">"Generate getters and setters" */
	
	
	public void incrementaEdad() { // Esto es un metodo(FUNCIONES) de la clase Persona, y no lleva static.  
		 this.edad++;
	}
	
	public void mostrar() {
		System.out.println(this.apellidos + ", " + this.nombre);
	} 
}
