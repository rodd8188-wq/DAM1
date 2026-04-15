package Ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.*;

public class Swing {

	public static void main(String[] args) {
		
		JFrame ventana = new JFrame("Ventana de Bienvenida");
		
		JLabel mensaje = new JLabel("Hola mundo");
		
		JButton boton = new JButton("Aceptar");
		
		JTextField edicion = new JTextField(50);
		
		//Que se muestren uno encima del otro
		ventana.setLayout(new FlowLayout());
		
		//Tamaño de la ventana
		ventana.setSize(600,600);
		
		//Añadir a la ventana
		ventana.add(mensaje);
		ventana.add(boton);
		ventana.add(edicion);
		ventana.add(mensaje, BorderLayout.CENTER);
		
		//Que aparezca en el centro
		ventana.setLocationRelativeTo(null);
		
		//Cuando se cierre la ventana se termina el programa
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Que la ventana sea visible
		ventana.setVisible(true);
		
	}

}
