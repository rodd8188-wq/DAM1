package Ajedrez;

import java.util.Scanner;

public class AjdPool {

	public static void main(String[] args) {
		
		///Nombre	Valor	Movimiento
		Piezas vacio = new Piezas("vacio",0,null);
		
		Piezas peonW = new Piezas("PeonW",1,"peon");
		Piezas peonB = new Piezas("PeonB",1,"peon");
		
		Piezas caballoW = new Piezas("CaballoW",3,"caballo");
		Piezas caballoB = new Piezas("CaballoB",3,"caballo");
		
		Piezas alfilW = new Piezas("AlfilW",3,"alfil");
		Piezas alfilB = new Piezas("AlfilB",3,"alfil");
		
		Piezas torreW = new Piezas("TorreW",5,"torre");
		Piezas torreB = new Piezas("TorreB",5,"torre");
		
		Piezas damaW = new Piezas("DamaW",9,"dama");
		Piezas damaB = new Piezas("DamaB",9,"dama");
		
		Piezas reyW = new Piezas("ReyW",1000,"rey");
		Piezas reyB = new Piezas("ReyB",1000,"rey");
		
		String tablero[][] = new String[8][8];
		Piezas tableroBack[][] = new Piezas[8][8];
		
		//Puntuaciones de los lados
		int puntuacionW=0;
		int puntuacionB=0;
		
		//Montar el tablero interno
		montarTableroBack(tableroBack, vacio, peonW, peonB, caballoB, caballoW, alfilB, alfilW, torreB, torreW, damaW, damaB, reyW, reyB);
		//Montar el tablero externo
		montarTableroFront(tablero, vacio, peonW, peonB, caballoB, caballoW, alfilB, alfilW, torreB, torreW, damaW, damaB, reyW, reyB);
		//Montar el trablero en la ventana
		Tablero v = new Tablero(tablero);
		
		///
		///Empezar a jugar:
		///
		
		//Mostrar el tablero y las puntuaciones para mover BLANCAS
		/*
		mostrarPuntuacionW(puntuacionW);
		mostrarPuntuacionB(puntuacionB);
		mostrarTableroFront(tablero);
		mostrarPuntuacionW(puntuacionW);
		do {
		//Pedir el movimiento para BLANCAS
		puntuacionW = pedirMovimientoBlancas(tableroBack, tablero, vacio, puntuacionW);
		//Mostrar el tablero y las puntuaciones para mover NEGRAS
		mostrarPuntuacionW(puntuacionW);
		mostrarTableroFront(tablero);
		mostrarPuntuacionB(puntuacionB);
				
		} while (puntuacionW<100 && puntuacionB<100);
		//Declarar el ganador
		if(puntuacionW>100)
			System.out.println("BLANCAS GANAN");
		else
			System.out.println("NEGRAS GANAN");
		*/
	}
	
	
	
	
	
	public static void montarTableroBack(Piezas tableroBack[][],
			Piezas vacio,Piezas peonW,Piezas peonB,Piezas caballoB,Piezas caballoW,Piezas alfilB,
			Piezas alfilW,Piezas torreB,Piezas torreW,Piezas damaW,Piezas damaB,Piezas reyW,Piezas reyB) {
		//Rellenar el tablero vacio
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				tableroBack[x][y]=vacio;
			}
		}
		//Asignar piezas blancas(W)
		tableroBack[0][0]=torreW;
		tableroBack[0][1]=caballoW;
		tableroBack[0][2]=alfilW;
		tableroBack[0][3]=damaW;
		tableroBack[0][4]=reyW;
		tableroBack[0][5]=alfilW;
		tableroBack[0][6]=caballoW;
		tableroBack[0][7]=torreW;
		for(int i=0;i<8;i++) {
			tableroBack[1][i]=peonW;
		}
		//Probar piezas (Back)
		tableroBack[5][0]=torreW;
		
		//Asignar piezas negras(B)
		tableroBack[7][0]=torreB;
		tableroBack[7][1]=caballoB;
		tableroBack[7][2]=alfilB;
		tableroBack[7][3]=damaB;
		tableroBack[7][4]=reyB;
		tableroBack[7][5]=alfilB;
		tableroBack[7][6]=caballoB;
		tableroBack[7][7]=torreB;
		for(int i=0;i<8;i++) {
			tableroBack[6][i]=peonB;
		}
	}
	public static void montarTableroFront(String tablero[][],
			Piezas vacio,Piezas peonW,Piezas peonB,Piezas caballoB,Piezas caballoW,Piezas alfilB,
			Piezas alfilW,Piezas torreB,Piezas torreW,Piezas damaW,Piezas damaB,Piezas reyW,Piezas reyB) {
		//Rellenar el tablero vacio
			for(int x=0;x<8;x++) {
				for(int y=0;y<8;y++) {
					tablero[x][y]=vacio.getNombre();
				}
			}
			//Asignar piezas negras(B)
			tablero[0][0]=torreB.getNombre();
			tablero[0][1]=caballoB.getNombre();
			tablero[0][2]=alfilB.getNombre();
			tablero[0][3]=damaB.getNombre();
			tablero[0][4]=reyB.getNombre();
			tablero[0][5]=alfilB.getNombre();
			tablero[0][6]=caballoB.getNombre();
			tablero[0][7]=torreB.getNombre();
			for(int i=0;i<8;i++) {
				tablero[1][i]=peonB.getNombre();
			}
			
			//Probar piezas (Front)
			tablero[5][0]=torreW.getNombre();
			
			//Asignar piezas blancas(W)
			tablero[7][0]=torreW.getNombre();
			tablero[7][1]=caballoW.getNombre();
			tablero[7][2]=alfilW.getNombre();
			tablero[7][3]=damaW.getNombre();
			tablero[7][4]=reyW.getNombre();
			tablero[7][5]=alfilW.getNombre();
			tablero[7][6]=caballoW.getNombre();
			tablero[7][7]=torreW.getNombre();
			for(int i=0;i<8;i++) {
				tablero[6][i]=peonW.getNombre();
			}
	}
	public static void mostrarTableroFront(String[][] tablero) {
		System.out.print("\n");
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				System.out.printf("%10s",tablero[x][y]);
			}
			System.out.println("\n");
		}
	}
	public static void mostrarPuntuacionW(int puntW) {
		System.out.println("Blancas: "+puntW);
	}
	public static void mostrarPuntuacionB(int puntB) {
		System.out.println("Negras: "+puntB);
	}
	public static int casillaDeLetraANumero(String piezaMover) {
		char charX1=piezaMover.charAt(0);
		String posX1=String.valueOf(charX1);
		if(posX1.equalsIgnoreCase("A")) {
			posX1="0";
		} else if(posX1.equalsIgnoreCase("B")) {
			posX1="1";
		} else if(posX1.equalsIgnoreCase("C")) {
			posX1="2";
		} else if(posX1.equalsIgnoreCase("D")) {
			posX1="3";
		} else if(posX1.equalsIgnoreCase("E")) {
			posX1="4";
		} else if(posX1.equalsIgnoreCase("F")) {
			posX1="5";
		} else if(posX1.equalsIgnoreCase("G")) {
			posX1="6";
		} else
			posX1="7";
		int casilla=Integer.valueOf(posX1);
		return casilla;
	}
	public static int posicionBuena(int pos) {
		switch (pos) { 
	    case 1:
	    	pos=7;
	    	break;
	    case 2:
	    	pos=6;
	    	break;
	    case 3:
	    	pos=5;
	    	break;
	    case 4:
	    	pos=4;
	    	break;
	    case 5:
	    	pos=3;
	    	break;
	    case 6:
	    	pos=2;
	    	break;
	    case 7:
	    	pos=1;
	    	break;
	    case 8:
	    	pos=0;
	    	break;
		}
		return pos;
	}
	public static void movimientoNoPermitidoPieza() {
		System.out.println("Movimiento ilegal, esa pieza no puede moverse de esa manera");
	}
	public static int pedirMovimientoBlancas(Piezas tableroBack[][], String tablero[][],Piezas vacio, int PuntuacionW) {
		System.out.println("MUEVEN BLANCAS");
		Scanner teclado = new Scanner(System.in);
		boolean casillaReal=false;
		int contadorErr=0;
		int posX1int=-1;
		int posY1int=-1;
		int posX2int=-1;
		int posY2int=-1;
		String piezaMover="";
		boolean cancelarM1=false;
		
		do {
			if(contadorErr==0)
				System.out.print("Introduce la casilla de la pieza a mover: ");
			else
				System.out.print("Introduce una casilla que este ocupada por una pieza blanca: ");
			piezaMover = teclado.nextLine();
			if(piezaMover.matches("[A-H][1-8]")||piezaMover.matches("[a-h][1-8]")) {
				casillaReal=true;
				posX1int = casillaDeLetraANumero(piezaMover);
				char charY1=piezaMover.charAt(1);
				posY1int=(int)(charY1)-48;
				
				posY1int=posicionBuena(posY1int);
				
				String casilla1 = tablero[posY1int][posX1int];
				char W = casilla1.charAt(casilla1.length()-1);
				String lastW = String.valueOf(W);
				if(lastW.compareTo("W")==0) {
					//tablero[posY1int][posX1int]=vacio.nombre;
					contadorErr=0;
				} else {
					casillaReal=false;
					contadorErr++;
				}
				//Muestra la pieza a mover y si es blanca o negra (W = blanca B = negra)
				System.out.println(casilla1+" "+W);
			}
			else {
				System.out.println("Introduce una casilla real");
				contadorErr=0;
			}
		} while(casillaReal!=true);
		//La casilla a donde lo mueves
		do {
			casillaReal=false;
			System.out.print("Introduce la casilla donde mueves la pieza: ");
			String moverA = teclado.nextLine();
			String piezaMoverLower=piezaMover.toLowerCase();
			if(piezaMoverLower.compareTo(moverA.toLowerCase())==0) {
				System.out.println("No puedes moverla a la misma casilla");
				casillaReal=false;
			} else {
				if(moverA.matches("[A-H][1-8]")||moverA.matches("[a-h][1-8]"))
					casillaReal=true;
				else
					System.out.println("Introduce una casilla real");
			}
			posX2int=casillaDeLetraANumero(moverA);
			char charY2=moverA.charAt(1);
			posY2int=(int)(charY2)-48;
			posY2int=posicionBuena(posY2int);
			
			//Comprobar si la pieza a mover puede hacer ese movimiento
			Piezas piezaParaMover = tableroBack[posY1int][posX1int];
			switch (piezaParaMover.getMovimiento()) {
				case "peon":{
					Piezas casillaDelante = tableroBack[posY1int-1][posX1int];
					Piezas dondeSeMueve = tableroBack[posY2int][posX2int];
					if(posY1int==6 && posY2int==posY1int-2 && casillaDelante.getNombre().equals("vacio")==true) {	//Si esta en la segunda casilla para dos para arriba
						//System.out.println("Esta permitido--------------------Doble recto");
					} else if(posY2int==posY1int-1 && posX2int==posX1int && casillaDelante.getNombre().equals("vacio")==true) {	//Si es una casilla para arriba
						//System.out.println("Esta permitido--------------------Recto");
					} else if(posY2int==posY1int-1 && posX2int==posX1int+1) {	//Si es una casilla para arriba a la derecha
						if(dondeSeMueve.getNombre().equals("vacio")==false) {
							//System.out.println("Esta permitido--------------------Derecha");
						} else {
							casillaReal=false;
							movimientoNoPermitidoPieza();
						}
					} else if(posY2int==posY1int-1 && posX2int==posX1int-1) {	//Si es una casilla para arriba a la izquierda
						if(dondeSeMueve.getNombre().equals("vacio")==false) {
							//System.out.println("Esta permitido--------------------Izquierda");
						}
						else {
							casillaReal=false;
							movimientoNoPermitidoPieza();
						}
					} else {
						casillaReal=false;
						if(posY2int!=posY1int) {
							movimientoNoPermitidoPieza();
						}
					}
					break;
				}
				case "caballo":{
					//Se podría hacer con switch y el último else con un default
					if(posY2int==posY1int-2 && posX2int==posX1int+1) {	//Mover 2 arriba y 1 a la izquierda
						//System.out.println("1-Esta permitido--------------------2 arriba 1 izquierda");
					} else if(posY2int==posY1int-2 && posX2int==posX1int-1) {	//Mover 2 arriba y 1 a la derecha
						//System.out.println("2-Esta permitido--------------------2 arriba 1 derecha");
					} else if(posY2int==posY1int-1 && posX2int==posX1int+2) {	//Mover 1 arriba y 2 a la derecha
						//System.out.println("3-Esta permitido--------------------1 arriba 2 derecha");
					} else if(posY2int==posY1int+1 && posX2int==posX1int+2) {	//Mover 1 abajo y 2 a la derecha
						//System.out.println("4-Esta permitido--------------------1 abajo 2 derecha");
					} else if(posY2int==posY1int+1 && posX2int==posX1int+2) {	//Mover 2 abajo y 1 a la derecha
						//System.out.println("5-Esta permitido--------------------2 abajo 1 derecha");
					} else if(posY2int==posY1int-1 && posX2int==posX1int+2) {	//Mover 2 abajo y 1 a la izquierda
						//System.out.println("6-Esta permitido--------------------2 abajo 1 izquierda");
					} else if(posY2int==posY1int-2 && posX2int==posX1int+1) {	//Mover 1 abajo y 2 a la izquierda
						//System.out.println("7-Esta permitido--------------------1 abajo 2 izquierda");
					} else if(posY2int==posY1int-2 && posX2int==posX1int-1) {	//Mover 1 arriba y 2 a la izquierda
						//System.out.println("8-Esta permitido--------------------1 arriba 2 izquierda");
					} else {
						casillaReal=false;
						if(posY2int!=posY1int)
							movimientoNoPermitidoPieza();
					}
					break;
				}
				case "alfil":{
					casillaReal=false;
					movimientoNoPermitidoPieza();
					//En ORDEN para que comprobar del más cerca a más lejano
					//Crear 7 arriba derecha
					//Crear 7 arriba izquierda
					//Crear 7 abajo derecha
					//Crear 7 abajo izquierda
					break;
				}
				case "torre":{
					//En ORDEN para que comprobar del más cerca a más lejano
					//Crear 7 arriba recto
					Piezas arriba1 = tableroBack[0][0];
						Piezas arriba2 = tableroBack[0][0];
							Piezas arriba3 = tableroBack[0][0];
								Piezas arriba4 = tableroBack[0][0];
									Piezas arriba5 = tableroBack[0][0];
										Piezas arriba6 = tableroBack[0][0];
											Piezas arriba7 = tableroBack[0][0];
					if(posY2int==posY1int-1 && posX2int==posX1int) {
						arriba1=tableroBack[posY2int][posX2int];
						if(arriba1.getNombre().equals(vacio.getNombre())) {
							System.out.println("------------------------Mover 1 arriba");
						} else {
							casillaReal=false;
							movimientoNoPermitidoPieza();
						}
					} else if(posY2int==posY1int-2 && posX2int==posX1int) {
						arriba2=tableroBack[posY2int][posX2int];
						if(arriba2.getNombre().equals(vacio.getNombre())&&
								arriba1.getNombre().equals(vacio.getNombre())) {
							System.out.println("------------------------Mover 2 arriba");
						} else {
							casillaReal=false;
							movimientoNoPermitidoPieza();
						}
					} else if(posY2int==posY1int-3 && posX2int==posX1int) {
						arriba3=tableroBack[posY2int][posX2int];
						if(arriba3.getNombre().equals(vacio.getNombre())&&
								arriba2.getNombre().equals(vacio.getNombre())&&
								arriba1.getNombre().equals(vacio.getNombre())) {
							System.out.println("------------------------Mover 3 arriba");
						} else {
							casillaReal=false;
							movimientoNoPermitidoPieza();
						}
					} else if(posY2int==posY1int-4 && posX2int==posX1int) {
						arriba4=tableroBack[posY2int][posX2int];
						if(arriba4.getNombre().equals(vacio.getNombre())&&
								arriba3.getNombre().equals(vacio.getNombre())&&
								arriba2.getNombre().equals(vacio.getNombre())&&
								arriba1.getNombre().equals(vacio.getNombre())) {
							System.out.println("------------------------Mover 4 arriba");
						} else {
							casillaReal=false;
							movimientoNoPermitidoPieza();
						}
					} else if(posY2int==posY1int-5 && posX2int==posX1int) {
						arriba5=tableroBack[posY2int][posX2int];
						if(arriba5.getNombre().equals(vacio.getNombre())&&
								arriba4.getNombre().equals(vacio.getNombre())&&
								arriba3.getNombre().equals(vacio.getNombre())&&
								arriba2.getNombre().equals(vacio.getNombre())&&
								arriba1.getNombre().equals(vacio.getNombre())) {
							System.out.println("------------------------Mover 5 arriba");
						} else {
							casillaReal=false;
							movimientoNoPermitidoPieza();
						}
					} else if(posY2int==posY1int-6 && posX2int==posX1int) {
						arriba6=tableroBack[posY2int][posX2int];
						if(arriba6.getNombre().equals(vacio.getNombre())&&
								arriba5.getNombre().equals(vacio.getNombre())&&
								arriba4.getNombre().equals(vacio.getNombre())&&
								arriba3.getNombre().equals(vacio.getNombre())&&
								arriba2.getNombre().equals(vacio.getNombre())&&
								arriba1.getNombre().equals(vacio.getNombre())) {
							System.out.println("------------------------Mover 6 arriba");
						} else {
							casillaReal=false;
							movimientoNoPermitidoPieza();
						}
					} else if(posY2int==posY1int-7 && posX2int==posX1int) {
						arriba7=tableroBack[posY2int][posX2int];
						if(arriba7.getNombre().equals(vacio.getNombre())&&
								arriba6.getNombre().equals(vacio.getNombre())&&
								arriba5.getNombre().equals(vacio.getNombre())&&
								arriba4.getNombre().equals(vacio.getNombre())&&
								arriba3.getNombre().equals(vacio.getNombre())&&
								arriba2.getNombre().equals(vacio.getNombre())&&
								arriba1.getNombre().equals(vacio.getNombre())) {
							System.out.println("------------------------Mover 7 arriba");
						} else {
							casillaReal=false;
							movimientoNoPermitidoPieza();
						}
					} else {
						casillaReal=false;
						movimientoNoPermitidoPieza();
					}
					
					//Crear 7 abajo recto
					
					Piezas abajo1 = tableroBack[0][0];
						Piezas abajo2 = tableroBack[0][0];
							Piezas abajo3 = tableroBack[0][0];
								Piezas abajo4 = tableroBack[0][0];
									Piezas abajo5 = tableroBack[0][0];
										Piezas abajo6 = tableroBack[0][0];
											Piezas abajo7 = tableroBack[0][0];
					
					//Crear 7 derecha recto
					
					Piezas derecha1 = tableroBack[0][0];
						Piezas derecha2 = tableroBack[0][0];
							Piezas derecha3 = tableroBack[0][0];
								Piezas derecha4 = tableroBack[0][0];
									Piezas derecha5 = tableroBack[0][0];
										Piezas derecha6 = tableroBack[0][0];
											Piezas derecha7 = tableroBack[0][0];
					
					//Crear 7 izquierda recto
					Piezas izquierda1 = tableroBack[0][0];
						Piezas izquierda2 = tableroBack[0][0];
							Piezas izquierda3 = tableroBack[0][0];
								Piezas izquierda4 = tableroBack[0][0];
									Piezas izquierda5 = tableroBack[0][0];
										Piezas izquierda6 = tableroBack[0][0];
											Piezas izquierda7 = tableroBack[0][0];
					
					
					//TERMINAR MOVIMIENTO TORRE
					break;
				}
				case "dama":{
					casillaReal=false;
					movimientoNoPermitidoPieza();
					//En ORDEN para que comprobar del más cerca a más lejano
					//Crear 7 arriba derecha
					//Crear 7 arriba izquierda
					//Crear 7 abajo derecha
					//Crear 7 abajo izquierda
					//Crear 7 arriba recto
					//Crear 7 abajo recto
					//Crear 7 derecha recto
					//Crear 7 izquierda recto
					break;
				}
				case "rey":{
					casillaReal=false;
					movimientoNoPermitidoPieza();
					//En ORDEN para que comprobar del más cerca a más lejano
					//Crear 1 arriba derecha
					//Crear 1 arriba izquierda
					//Crear 1 abajo derecha
					//Crear 1 abajo izquierda
					//Crear 1 arriba recto
					//Crear 1 abajo recto
					//Crear 1 derecha recto
					//Crear 1 izquierda recto
					break;
				}
			}
		} while(casillaReal!=true);
		
		if(posX1int != -1|posY1int != -1|posX2int != -1|posY2int != -1) {
			
			//Mover las piezas en el Tablero Externo
			tablero[posY2int][posX2int]=tablero[posY1int][posX1int];
			tablero[posY1int][posX1int]=vacio.getNombre();
			Piezas casillaPiezaA = tableroBack[posY2int][posX2int];
			PuntuacionW+=casillaPiezaA.getValor();
			//Mover las piezas en el Tablero Interno
			tableroBack[posY2int][posX2int]=tableroBack[posY1int][posX1int];
			tableroBack[posY1int][posX1int]=vacio;
		}
		else
			System.out.println("Algo salio mal");
		return PuntuacionW;
	}
}