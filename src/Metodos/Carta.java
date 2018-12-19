package Metodos;
public class Carta {
	Palos palo;
	Numeros numeros;
	public Carta(Palos palo, Numeros numero) {
		super();
		this.palo = palo;
		this.numeros = numero;
	}
	/**
	 * @return the palo
	 */
	public Palos getPalo() {
		return palo;
	}
	/**
	 * @param palo the palo to set
	 */
	public void setPalo(Palos palo) {
		this.palo = palo;
	}
	/**
	 * @return the numero
	 */
	public Numeros getNumero() {
		return numeros;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Numeros numero) {
		this.numeros = numero;
	}
	
	public String toString() {
		return numeros + " de " + palo;
	}
	
}