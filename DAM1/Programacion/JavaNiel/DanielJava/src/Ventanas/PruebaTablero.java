package Ventanas;

import java.awt.GridLayout;

import javax.swing.*;

public class PruebaTablero {

	public static void main(String[] args) {
		
		JFrame v = new JFrame("Tablero");
		GridLayout gl = new GridLayout(8,3);
		gl.setHgap(5); gl.setVgap(5);
		v.setLayout(gl);
		for(int i = 1; i <= 64; i++) {
		v.add(new JButton(String.valueOf(i)));
		}
		v.setSize(500,500);
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		
		
	}

}
