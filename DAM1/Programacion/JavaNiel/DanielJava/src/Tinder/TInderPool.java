package Tinder;

import java.time.LocalDate;

public class TInderPool {

	public static void main(String[] args) {
		
		Tinder tinderGoya = new Tinder();
		
		Hombre h1 = new Hombre(tinderGoya,"Daniel","21/10/1990",2);
		Hombre h2 = new Hombre(tinderGoya,"Nicolas","01/12/2005",2, 50 , 80);
		Hombre h3 = new Hombre(tinderGoya,"Alejandro","10/05/2006",0, 18, 25);
		Hombre h4 = new Hombre(tinderGoya,"Alberto","04/11/1994",1);
		Hombre h5 = new Hombre(tinderGoya,"Manolo","01/07/1972",1);
		Hombre h6 = new Hombre(tinderGoya,"Mateo","10/08/2002",0);
		
		Mujer m1 = new Mujer(tinderGoya, "Luisa","25/01/2000",2, 18, 35);
		Mujer m2 = new Mujer(tinderGoya, "Paula","19/05/2001",1);
		Mujer m3 = new Mujer(tinderGoya, "Alberta","10/04/2002",1, 18, 30);
		Mujer m4 = new Mujer(tinderGoya, "Marta","16/05/1999",0);
		   
		Otro o1 = new Otro(tinderGoya, "Jane","12/03/2004",0);
		
		tinderGoya.buscaMatches(h1);
		
	}
}
