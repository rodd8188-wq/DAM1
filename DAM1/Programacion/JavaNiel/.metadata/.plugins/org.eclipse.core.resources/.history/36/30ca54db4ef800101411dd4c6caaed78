package ExamenAutoescuela;

import java.util.ArrayList;

public class Preguntas {
	private String pregunta;
	private String respuestaCorrecta;
	private String respuesta2;
	private String respuesta3;
	private String respuesta4;
	private String respuesta5;
	private String respuesta6;
	static ArrayList<Preguntas> listaTodasPreguntas = new ArrayList<>();
	static ArrayList<Preguntas> listaPreguntasExamen = new ArrayList<>();
	public Preguntas(String pregunta, String res1, String res2, String res3, String res4, String res5, String res6){
		this.pregunta=pregunta;
		respuestaCorrecta=res1;
		respuesta2=res2;
		respuesta3=res3;
		respuesta4=res4;
		respuesta5=res5;
		respuesta6=res6;
		this.listaTodasPreguntas.add(this);
	}
	public String getPregunta() {
		return this.pregunta;
	}
	public static ArrayList<Preguntas> getListaPreguntas(){
		return listaTodasPreguntas;
	}
	public void mostrarTodasPreguntas() {
		String pre="";
		for(int i=0;i<listaTodasPreguntas.size();i++)
			System.out.println(listaTodasPreguntas.get(i).pregunta);
	}
	public void mostrarPreguntasExamen() {
		
	}
}
