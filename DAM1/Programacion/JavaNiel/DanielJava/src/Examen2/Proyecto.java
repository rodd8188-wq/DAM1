package Examen2;

import java.util.ArrayList;

public class Proyecto {
	private String nombre;
	private String codigo;
	private JefeProyecto jefe;
	private int numeroMax;
	private boolean numMaxSi;
	ArrayList<Proyecto> proyectos = new ArrayList<>();
	ArrayList<Programador> desarrolladores = new ArrayList<>();
	
	public Proyecto(String nombre, JefeProyecto jefe, int numMax) {
		this.nombre=nombre;
		this.jefe=jefe;
		numeroMax=numMax;
		
		this.proyectos.add(this);
		int num = proyectos.indexOf(this);
		codigo="PRO-"+Integer.toString(num)+1;
		
		numMaxSi=true;
	}
	public Proyecto(String nombre, JefeProyecto jefe) {
		this.nombre=nombre;
		this.jefe=jefe;
		
		this.proyectos.add(this);
		int num = proyectos.indexOf(this);
		codigo="PRO-"+Integer.toString(num)+1;
		
		numMaxSi=false;
	}
	public void asignarDesarrollador(Programador nuevoPro) {
		if(numMaxSi==true)
			if(this.desarrolladores.size()<this.numeroMax) {
				this.desarrolladores.add(nuevoPro);
				System.out.println(nuevoPro.getNombre()+" asignado al proyecto "+this.codigo+".");
			}
			else
				System.out.println("No se puede asignar a "+nuevoPro.getNombre()+" al proyecto "+this.codigo+". Máximo de desarrolladores cubierto.");
		else
			System.out.println("No se puede asignar a "+nuevoPro.getNombre()+" al proyecto "+this.codigo+". No tiene aún definido el número de desarrolladores.");
	}
 	public void cambioNumDesarrolladores(int nuevoNum) {
		if(numMaxSi==false) {
			this.numeroMax=nuevoNum;
			System.out.println(nuevoNum+" Desarrolladores asignados al proyecto "+this.codigo);
			numMaxSi=true;
		} else {
			System.out.println("Ya hay "+this.numeroMax+" Desarrolladores asignados al proyecto "+this.codigo+". Este dato no puede cambiarse.");
		}
	}
	public void allInf() {
		if(numMaxSi==false)
			System.out.println("Nombre: "+this.nombre+"\nCodigo: "+this.codigo+"\nJefe de Proyecto: "+this.jefe.getNombre());
		else {
			System.out.println("Proyecto "+this.codigo+". "+this.nombre+"\nJefe de Proyectos: "+jefe.getCodigo()+". "+jefe.getNombre()+"\nDesarrolladores asignados:");
			for(Programador i:this.desarrolladores) {
				System.out.println(i.getCodigo()+". "+i.getNombre());
			}
		}
			
	}
	
	public void cambioJefe(JefeProyecto nuevoJefe) {
		this.jefe=nuevoJefe;
		System.out.println("El jefe de Proyecto "+this.codigo+" ha cambiado. Ahora es "+this.jefe.getNombre()+".");
	}
}
