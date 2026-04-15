package Paqueteria;

public class Localizacion {
	private int y;
	private int x;
	public Localizacion(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public Double distancia(Localizacion destino) {
		int x2 = destino.getX();
		int y2 = destino.getY();
		int x1 = this.getX();
		int y1 = this.getY();
		Double fin = Math.hypot(x2, y2);
		Double ini = Math.hypot(x1, y1);
		Double distancia = fin-ini;
		return distancia;
	}
}
