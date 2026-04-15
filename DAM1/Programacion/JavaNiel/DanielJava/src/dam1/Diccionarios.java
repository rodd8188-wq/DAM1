package dam1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Diccionarios {

	public static void main(String[] args) {
		
		///Diccionarios
		
				//Se componenen de "CLAVE" + "VALOR"
				HashMap<String,Double> sueldos = new HashMap<>();
				sueldos.put("José María Morales", 3567.44);
				sueldos.put("Pepe Potamo", 1755.44);
				sueldos.put("Inés Perado", 3454.00);
				
				System.out.println(sueldos);
				
				//Lo sustituye en vez de crear uno nuevo
				sueldos.put("José María Morales", 4567.44);
				
				System.out.println(sueldos);
				
				String nombre = "Pepe Potamo";
				
				//sueldos.remove(nombre);
				
				
				if(sueldos.containsKey(nombre))
					System.out.printf("El sueldo de %s es %.2f\n",nombre, sueldos.get(nombre));
				else
					System.out.println("Esa clave no existe");
				
				for (Entry<String, Double> persona:sueldos.entrySet()) {
					System.out.printf("%s: %.2f\n",persona.getKey(),persona.getValue());
				}
				
				for(String persona: sueldos.keySet()) {
					System.out.printf("%s\n",persona);
				}
				
				for(String persona: sueldos.keySet()) {
					System.out.printf("%s: %.2f\n",persona, sueldos.get(persona));
				}
				
				for(Double sueldo: sueldos.values()) {
					System.out.printf("%.2f\n",sueldo);
				}
				
				sueldos.forEach((nombre2, sueldo2)-> System.out.println(nombre2+": "+sueldo2));
				
				
				//Perfecto para modificar datos a la hora de recorrerlo
				Iterator<Map.Entry<String,Double>> iterador = sueldos.entrySet().iterator();
				while(iterador.hasNext()) {
					Map.Entry<String,Double> persona = iterador.next();
					System.out.printf("it- %s: %.2f\n",persona.getKey(),persona.getValue());
				}
				
				//Forma simplificada de la de arriba
				Iterator<String> iterador2 = sueldos.keySet().iterator();
				while(iterador2.hasNext()) {
					String nombre3 = iterador2.next();
					System.out.printf("it2- %s: %.2f\n",nombre3,sueldos.get(nombre));
				}
	}

}
