package Ajedrez;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

public class Tablero extends JFrame {
	public Tablero(String[][] tablero) {
		JFrame ventana = new JFrame("Tablero");
		GridLayout gl = new GridLayout(8,3);
		//gl.setHgap(5); gl.setVgap(5);
		ventana.setLayout(gl);
		int i=0;
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				JButton casilla = new JButton(tablero[x][y]);
				if(i%2==0) {
					casilla.setBackground(new Color(0,0,0));
					casilla.setForeground(new Color(255,255,255));
				}
				ventana.add(casilla);
				i++;
			}
			i++;
		}
		ventana.setLocationRelativeTo(null);
		ventana.setSize(800,800);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
	}
}
