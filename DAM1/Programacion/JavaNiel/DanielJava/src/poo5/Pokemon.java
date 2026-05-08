package poo5;

public class Pokemon implements Comparable<Pokemon>{
	private int codigo;
	private String nombre;
	private String[] tipo = new String[2];
	public Pokemon(int c, String nom, String tipo) {
		this.codigo = c;
		this.nombre = nom;
		this.tipo[0] = tipo;
		this.tipo[1] = null;
	}
	public Pokemon(int c, String nom, String t1, String t2) {
		this.codigo = c;
		this.nombre = nom;
		this.tipo[0] = t1;
		this.tipo[1] = t2;
	}
	
	// Sobreescribir el metodo toString()
	@Override	// Declarar que el metodo sobreescribe otro de la clase padre (Solo es decorativo e informativo)
	public String toString() {
		String linea ="(#"+ String.valueOf(this.codigo)+")" + this.nombre + "\n";
		if(tipo[1]==null)
			linea += "Tipo: " + this.tipo[0];
		else
			linea += "Tipos: " + this.tipo[0] + " y " + this.tipo[1];
		return linea+"\n";
	}
	
	// Sobreescribir el metodo equals()
	@Override
	public boolean equals(Object otro) {		// Tiene que ser un objeto de tipo Object
		Pokemon pk = (Pokemon) otro;
		if(this.codigo == pk.codigo)
			return true;
		return false;
	}
	
	// Sobreescribir el metodo compareTo()			IMPORTANTE hay que implementar que se comparable (implements Comparable<Pokemon>)
	// Al sobreescribir este metodo hace que algunos metodos como Collections.sort() funcione de la misma manera y los ordene por el codigo del pokemon
	@Override
	public int compareTo(Pokemon otro) {			// Puede ser un objeto de cualquier tipo
		if(this.codigo == otro.codigo)
			return 0;
		else if(this.codigo > otro.codigo)
			return 1;
		else
			return -1;
	}
}
