package Boletin3;

public class ESOpool {

	public static void main(String[] args) {
		//Nombre	Apellidos	Grupo	Edad	Ciclo	Modulo
		Alumno alumno1 = new Alumno("Daniel","Rodriguez","A",19,"DAM","Superior");
		
		//Nombre	Apellidos	Ciclo	Departamento
		Profesor profesor1 = new Profesor("Juan","Perez","DAM","Informática");
		Profesor profesor2 = new Profesor("Lucas","Brimon","","EMPRESA");
		Profesor profesor3 = new Profesor("Paula","Perez","","Politica");
		
		//Nombre	Año		Horas semanales		Optativa(Si/No)
		Modulo programacion = new Modulo("Programación",1,8,"No");
		Modulo sistemas = new Modulo("Sistemas informáticos",1,8,"No");
		Modulo ipe = new Modulo("IPE 1",1,3,"No");
		
		//Nombre	Grado	Modulo-1	Modulo-2	Modulo-3
		Ciclo dam = new Ciclo("Desarrollo de Aplicaciones Multiplataforma","Superior");
		dam.anyadeModulo(programacion);
		dam.anyadeModulo(sistemas);
		dam.anyadeModulo(ipe);
		
		//Nombre	Ciclo	Curso	NumAlumnos
		Grupo dam1 = new Grupo("DAM1",dam,1,28);
		dam1.anyadeTutor(profesor1);
		dam1.anyadeAlumno(alumno1);
		
		
		//allInf te da toda la información
		//mayorEdad te dice si el alumno es mayor o menor de los 18
		
		
		
		
		
		
	}

}
