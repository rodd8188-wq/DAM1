package Ajedrez;

import java.util.Scanner;

public class Piezas{
private String nombre;
private int valor;
private String movimiento;
	public Piezas(String nom, int val, String mov) {
		this.nombre = nom;
		this.valor=val;
		this.movimiento=mov;
	}
	public String getNombre() {
		return this.nombre;
	}
	public int getValor() {
		return this.valor;
	}
	public String getMovimiento() {
		return this.movimiento;
	}
}
