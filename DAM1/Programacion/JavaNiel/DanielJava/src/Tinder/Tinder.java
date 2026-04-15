package Tinder;

import java.util.ArrayList;

public class Tinder {
	private ArrayList<Hombre> hombres = new ArrayList<>();
	private ArrayList<Mujer> mujeres = new ArrayList<>();
	private ArrayList<Otro> otros = new ArrayList<>();
	public static void Tinder(Persona persona) {
		
	}
	public void anyade(Hombre hombre) {
		this.hombres.add(hombre);
	}
	public void anyade(Mujer mujer) {
		this.mujeres.add(mujer);
	}
	public void anyade(Otro otro) {
		this.otros.add(otro);
	}
	
	public void buscaMatches(Hombre hombre) {
		ArrayList<Persona> matches = new ArrayList<>();
		if(hombre.getInteres()==0) {
			for(Otro o:this.otros) {
				if(o.getInteres()==1 || o.getInteres()==0)
					if(hombre.esMatch(o)==true) {
						o.mostrarDatos();
						matches.add(o);
					}
			}
		} else if(hombre.getInteres()==1) {
			for(Hombre h:this.hombres) {
				if(hombre!=h)
					if(h.getInteres()==1 || h.getInteres()==0) {
						if(hombre.esMatch(h)==true) {
							h.mostrarDatos();
							matches.add(h);
						}
					}
			}
		} else {
			for(Mujer m:mujeres) {
				if(m.getInteres()==1 || m.getInteres()==0)
					if(hombre.esMatch(m)==true) {
						m.mostrarDatos();
						matches.add(m);
					}
			}
		}
	}
	
	
	
	
	public void buscaMatches(Mujer mujer) {
		ArrayList<Persona> matches = new ArrayList<>();
		if(mujer.getInteres()==0) {
			for(Otro o:this.otros) {
				if(o.getInteres()==2 || o.getInteres()==0)
					if(mujer.esMatch(o)==true) {
					o.mostrarDatos();
					matches.add(o);
				}
			}
		} else if(mujer.getInteres()==1) {
			for(Hombre h:this.hombres) {
				if(h.getInteres()==2 || h.getInteres()==0)
					if(mujer.esMatch(h)==true) {
						h.mostrarDatos();
						matches.add(h);
					}
			}
		} else {
			for(Mujer m:mujeres) {
				if(mujer!=m)
					if(m.getInteres()==2 || m.getInteres()==0)
						if(mujer.esMatch(m)==true) {
							m.mostrarDatos();
							matches.add(m);
						}
			}
		}
	}
	public void buscaMatches(Otro otro) {
		ArrayList<Persona> matches = new ArrayList<>();
		if(otro.getInteres()==0) {
			for(Otro o:this.otros) {
				if(otro!=o)
					if(o.getInteres()==0)
						if(otro.esMatch(o)==true) {
							o.mostrarDatos();
							matches.add(o);
						}
			}
		} else if(otro.getInteres()==1) {
			for(Hombre h:this.hombres) {
				if(h.getInteres()==0)
					if(otro.esMatch(h)==true) {
						h.mostrarDatos();
						matches.add(h);
					}
			}
		} else {
			for(Mujer m:mujeres) {
				if(m.getInteres()==0)
					if(otro.esMatch(m)==true) {
						m.mostrarDatos();
						matches.add(m);
					}
			}
		}
	}
}
