package Metodos;
public enum Numeros {
	AS(1,10,11), DOS(2,1,0), TRES(3,9,10), CUATRO(4,2,0), CINCO(5,3,0), SEIS(6,4,0), SIETE(7,5,0), SOTA(10,6,2), CABALLO(10,7,3), REY(10,8,4);
	private double cc;
	private int valor;
	private int puntos;

	
	private Numeros(double cc, int valor, int puntos) {
		this.cc = cc;
		this.valor = valor;
		this.puntos = puntos;
	}
	public double getCc() {
		return cc;
	}

	public void setCc(double cc) {
		this.cc = cc;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
}