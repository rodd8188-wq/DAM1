package PokemonPack;

public class Pokemon {

	private String nombre;
	private String [] Tipo=new String [2];
	private int nPokedex;
	private int  PV= (int)(Math.random()*51)+50;
	//private Pokemon evolucion = null;
	
	public Pokemon(String nom,String tipo,int nPokedex) {
		this.nombre=nom;
		this.Tipo[0]=tipo;
		this.nPokedex=nPokedex;
	}
	public Pokemon(String nom,String tipo,String tipo2 ,int nPokedex) {
		this.nombre=nom;
		this.Tipo[0]=tipo;
		this.Tipo[1]=tipo2;
		this.nPokedex=nPokedex;
	}
	public void mostrar(){
		if(this.Tipo[1]==null) {
			System.out.println("-------------");
			System.out.println(nPokedex+"-"+nombre+"\n"+"Tipo: "+Tipo[0]+" "+"\n"+"PV: "+PV+"ps");
			System.out.println("-------------");
		}
		else {
			System.out.println("-------------");
			System.out.println(nPokedex+"-"+nombre+"\nTipo: "+Tipo[0]+", "+Tipo[1]+" "+"\n"+"PV: "+PV+"ps");
			System.out.println("-------------");
		}
	}
	
	public void combateContra(Pokemon atacado) {
		boolean finalizado = false;
		String vencedor="";
		if (this.PV <= 0)
			System.out.println("Un pokemon sin PV no puede combatir");
		else {
			do {
				int danyoAtq = (int)(Math.random()*21)+20;
				System.out.println(this.nombre+" realizó un ataque de "+danyoAtq);
				atacado.PV -=danyoAtq;
				if(atacado.PV >0)
					System.out.println(atacado.nombre+" le quedan "+atacado.PV+" puntos de vida\n");
				else
					System.out.println(this.nombre+" no le quedan puntos de vida\n");
				if (this.PV > 0 && atacado.PV > 0) {
					int danyoDef = (int)(Math.random()*21)+20;
					System.out.println(atacado.nombre+" realizó un ataque de "+danyoAtq);
					this.PV -= danyoDef;
					if(this.PV >0)
						System.out.println(this.nombre+" le quedan "+this.PV+" puntos de vida\n");
					else
						System.out.println(this.nombre+" no le quedan puntos de vida\n");
				}
				if (this.PV <= 0) {
					System.out.println(this.nombre+" ha sido derrotado");
					finalizado=true;
					vencedor = atacado.nombre;
				}
				if (atacado.PV <= 0) {
					System.out.println(atacado.nombre+" ha sido derrotado");
					finalizado=true;
					vencedor = this.nombre;
				}
			}while(finalizado ==false);
			System.out.println("\n----------------------");
			System.out.println("\nEl vencedor es "+vencedor);
			System.out.println("\n----------------------");
		}
	}
	
	
	
	
}
