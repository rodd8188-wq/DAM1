package Default;

public class Cositas {

	public static void main(String[] args) {
		Profesor profesor1 = new Profesor("José María","Morales");
		System.out.println(profesor1.getNombreCompleto());
		
		Alumno alumno1 = new Alumno("Andrés","Ortega",19);
		System.out.println(alumno1.getNombreCompleto());
		
		Personas persona1 = new Personas("Daniel","Lopez");
		System.out.println(persona1.getNombre());
	}

}
