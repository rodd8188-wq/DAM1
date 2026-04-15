package Examen2;

import java.util.ArrayList;

public class Examen2Pool {

	public static void main(String[] args) {
		
		Programador emp1 = new Programador("Elena Nito Del Bosque",1500.44,true,false);
		Programador emp2 = new Programador("Demetrio Imedio",1400.21,false,false);
		Programador emp3 = new Programador("Felipe Lotas",1600.11,true,true);
		
		JefeProyecto emp4 = new JefeProyecto("Germán Ivela",2000.25);
		JefeProyecto emp5 = new JefeProyecto("Benito Camelas",1987.75);
		
		Proyecto pro1 = new Proyecto("Hacer la aplicaciñon Java para ENAIRE",emp4,3);
		Proyecto pro2 = new Proyecto("Hacer aplicación movil para Clínica",emp4);
		Proyecto pro3 = new Proyecto("Software de gestión de empleados",emp5);
		
		///Muestra toda la informacion
		//emp1.allInf();
		
		///Realiza un cambio de jefe en un proyecto
		//pro1.cambioJefe(emp5);
		
		///Hacer un cambio en el numero de desarrolladores
		//pro1.cambioNumDesarrolladores(2);
		
		///Asignar un desarrollador a un proyecto
		//pro2.asignarDesarrollador(emp1);
		
		///Metodo que te calcule el salario de un empleado 
		//emp1.calcularSalario();
		
	}

}
