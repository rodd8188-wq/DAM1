package ProtectoraAnimales;

public class ProtectoraPool {

	public static void main(String[] args) {
		
		//Nombre(opcional)	AñoNacimiento	Adoptado(boolean)	Vacunado(boolean)
		Perro perro1 = new Perro("Bowie",2023,true,true);
		Perro perro2 = new Perro(2025,false,false);
		
		//Nombre(opcional)	AñoNacimiento	Adoptado(boolean)	Vacunado(boolean)
		Gato gato1 = new Gato("Coco",2024,true,true);
		Gato gato2 = new Gato(2025,false,false);
		Gato gato3 = new Gato("Negro",2023,false,true);
		
		//Nombre(opcional)	AñoNacimiento	Adoptado(boolean)	Tipo(Acuatico o Terrestre)
		Tortuga tortuga1 = new Tortuga("Lola",2025,true,"Acuatico");
		Tortuga tortuga2 = new Tortuga(2025,false,"Acuatico");
		
		// Nombre	Apellidos	Edad	Telefono	AnimalInteresado
		Cliente cliente1 = new Cliente("Jose","Paul Pascal",38,567909034,"Perro",perro1,gato1,null);
		
		
		cliente1.allInf();
		
	}

}
