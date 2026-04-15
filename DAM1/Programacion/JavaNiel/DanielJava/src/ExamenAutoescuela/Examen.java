package ExamenAutoescuela;

import java.util.ArrayList;
import java.util.HashSet;

public class Examen {
	private int puntuacion;
	private int numPreguntas;
	static ArrayList<Preguntas> listaPreguntas = new ArrayList<>();
	public Examen(int numPreguntas, ArrayList<Preguntas> listaPreguntas) {
		this.numPreguntas=numPreguntas;
		this.listaPreguntas=listaPreguntas;
	}
	public void mostrarTodasPreguntas() {
		String pre=""; 
		for(int i=0;i<this.listaPreguntas.size();i++)
			System.out.println(this.listaPreguntas.get(i).getPregunta());
	}
	public void montarExamen() {
		int posArray;
		ArrayList<Integer> yaSalido = new ArrayList<>();
		for(int i=0;i<this.numPreguntas;i++) {
			boolean repetido=false;
			while (repetido==false) {
				repetido=false;
				posArray = (int)(Math.random()*Preguntas.getListaPreguntas().size())+0;
				if(yaSalido.contains(posArray)==false) {
					yaSalido.add(posArray);
					repetido=true;
					this.listaPreguntas.add(Preguntas.listaTodasPreguntas.get(posArray));	
				} else {
					repetido=false;
				}
			}
		}
	}
	public void mostrarPreguntasExamen() {
		for(Preguntas s:listaPreguntas)
			System.out.println(s.getPregunta()+"\n");
	}
}
