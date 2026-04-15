package nose2;

public class Pokemonxd {
	
	private String nombre;
	private int codigo;
	private String[] tipo = new String[2];
	private int pV;
	//private String evolucion;
	private Pokemonxd evolucion = null; //Pokemonxd ya es un tipo de dato porque lo hemos creado abajo
	
	public Pokemonxd(int cod, String nom, String tip) {
		this.nombre = nom;
		this.codigo = cod;
		this.tipo[0] = tip;
		this.pV = (int)((Math.random()*51)+50);
	}
	
	public Pokemonxd(int cod, String nom, String tip1, String tip2) {
		this.nombre = nom;
		this.codigo = cod;
		this.tipo[0] = tip1;
		this.tipo[1] = tip2;
		this.pV = (int)((Math.random()*51)+50);
	}
	
	public void mostrar() {
		System.out.println("--------------");
		System.out.println(this.codigo + " - " + this.nombre);
		
		if(this.tipo[1] == null)
		    System.out.println("Tipo:" + this.tipo[0]);
		else
			System.out.println("Tipos: " + this.tipo[0] + ", " + this.tipo[1]);
		
		if(this.evolucion != null)
			System.out.println("Evoluciona en: " + this.evolucion.nombre);
		
		
		System.out.println("PV: " + this.pV);
		System.out.println("--------------");
			
	}
	
	public void setEvolucion(Pokemonxd p) { //recibiremos un Pokemonxd, aqui creamos el tipo de dato Pokemonxd, por eso lo pongo ahi. ( no devuelve nada, le ponemos void)
		this.evolucion = p;
	}
	
	public Pokemonxd evoluciona() { //devolvere un pokemonxd asi que lo pongo ahi
		Pokemonxd pokemon = this; //ponemos un variable intermedia para no poner 2 returns, SOLO DEBE HABER 1 RETURN.
		if(this.evolucion == null)
			System.out.println("Este pokemon no sabe evolucionar");
		else 
			pokemon = this.evolucion;
		return pokemon;	
	}
	
	public boolean combateContra(Pokemonxd atacado) { //p3 es this, p2 es el atacado, asi que lo podemos como argumento,
		boolean combateTerminado;
		boolean vencedor = true; //lo inicializamos aqui con cualquier valor.
		if(this.pV <= 0 || atacado.pV <= 0)
			System.out.println("Un pokemon sin PV no puede combatir");
		else {	
		do {	
		int danyo = (int)((Math.random()*51)+25);
		atacado.pV -= danyo;
		if(atacado.pV > 0) {
			danyo = (int)((Math.random()*51)+25);
			this.pV -= danyo;
			if(this.pV > 0)
				combateTerminado = false;
			else {
				vencedor = false;
				System.out.println(this.nombre + " ha sido derrotado");
				combateTerminado = true;
			}
		}
		else {
			vencedor = true;
			System.out.println(atacado.nombre + " ha sido derrotado");
			combateTerminado = true;
		}
	}while (combateTerminado == false);
   }
		//True si gana el atacante. false si gana el atacado
		return vencedor;
	
 }
}
