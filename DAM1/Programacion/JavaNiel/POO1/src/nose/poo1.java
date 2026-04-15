package nose;

public class poo1 {

	public static void main(String[] args) {
		
       Persona persona1 = new Persona("Lucia", "Sanz"); //Asi creo un objeto (persona1) de tipo Persona, y dentro del parentesis llamo(invoco) al constructor.
       persona1.mostrar();
       
   /*    Persona persona2; //asi creamos una variabe(persona2)(Esto no seria un objeto) , pero no invocamos al constructor, asi que no funciona 
       persona2.mostrar(); */
       
       Persona persona2;
       persona2 = new Persona("Pepe", "Potamo", 57); //Ahora si es un objeto
       persona2.mostrar();
       
     /*  persona2 = null; //Asi se destruye un objeto, si lo mostramos nos aparecera error
       persona2.mostrar(); */
       
       persona2 = new Persona("Alberto", "Perez", 57);
       persona2.mostrar();
       
       System.out.println(persona1.getNumPersonas());
       System.out.println(persona2.getNumPersonas()); 
	}

}
