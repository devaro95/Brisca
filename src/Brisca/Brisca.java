package Brisca;

import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import Metodos.Baraja;
import Metodos.Carta;
import Metodos.Palos;

public class Brisca{
	public void partida() {
		Stack<Carta> bazaJugador = new Stack<Carta>();
		Stack<Carta> bazaCPU = new Stack<Carta>();
		int mano, puntosHumano = 0, puntosOrdenador=0;
		Baraja baraja = new Baraja();
		Carta humano = null;
		Carta ordenador = null;
		baraja.Barajar();
		baraja.getCartas();  //(mostrar las cartas de la baraja)
		@SuppressWarnings("rawtypes")
		Vector[] manos = baraja.repartirMano(2, 3);
		Carta triunfo = baraja.getCartas().firstElement();
		Palos paloTriunfo = triunfo.getPalo();
		
		//ramdom para saber quien empieza
		mano =(int) (Math.random()*2)+1;
		while(manos[1].size()>0) {
			System.out.println("El triunfo es " +triunfo);
			if(mano==1) {
			//turno del humano
				humano = jugadaHumano(manos[0]);
				//turno del ordenador
				//System.out.println("Mano del ordenador:\n" + manos[1]); (mostrar la mano del ordenador)
				ordenador = jugadaCPU(manos[1], mano);
			}
			else if(mano==2) {
				//turno del ordenador
				//System.out.println("Mano del ordenador:\n" + manos[1]); (mostrar la mano del ordenador)
				ordenador = jugadaCPU(manos[1], mano);
				//turno del humano
				humano = jugadaHumano(manos[0]);
			}
			//comparamos las dos cartas para saber quien gana
			mano = brisca(humano, ordenador, paloTriunfo, bazaJugador, bazaCPU, mano);
			//mientras haya cartas, se roba
			robarDespues(baraja, mano,manos);
			
			System.out.println("Tu baza es :" + bazaJugador);
			System.out.println("La baza del ordenador es : " + bazaCPU);
		}
		//contamos los puntos de las cartas
		puntosHumano = contarPuntos(bazaJugador, puntosHumano);
		puntosOrdenador = contarPuntos(bazaCPU, puntosOrdenador);
		System.out.println("Tus puntos son: " + puntosHumano);
		System.out.println("Los puntos del ordenador son: " + puntosOrdenador);
		if(puntosHumano>puntosOrdenador) {
			System.out.println("Enhorabuena has ganado!");
		}else {
			System.out.println("Lo siento, has perdido");
		}
	}
	
	public int brisca(Carta humano, Carta ordenador, Palos paloTriunfo, Stack<Carta> jugadoruno, Stack<Carta> jugadordos, int mano) {
		//iguales que el triunfo
		if((humano.getPalo()==paloTriunfo) && (ordenador.getPalo()==paloTriunfo)) {
			//humano menor que ordenador, gana ordenador
			if(humano.getNumero().getValor()<ordenador.getNumero().getValor()) {
				jugadordos.push(humano);
				jugadordos.push(ordenador);
				System.out.println("gana ordenador");
				mano=2;
				//humano mayor que ordenador, gana humano
			}else {
				jugadoruno.push(humano);
				jugadoruno.push(ordenador);
				System.out.println("gana humano");
				mano = 1;
			}
			//carta humano diferente triunfo, carta ordenador igual triunfo, gana ordenador
		}else if((humano.getPalo()!=paloTriunfo) && (ordenador.getPalo()==paloTriunfo)) {
				jugadordos.push(humano);
				jugadordos.push(ordenador);
				System.out.println("gana ordenador");
				mano = 2;
				//carta humano igual triunfo, carta ordenador diferente triunfo, gana humano
		}else if((humano.getPalo()==paloTriunfo) && (ordenador.getPalo()!=paloTriunfo)) {
			jugadoruno.push(humano);
			jugadoruno.push(ordenador);
			System.out.println("gana humano");
			mano = 1;
		}
		//en caso de que el turno sea del jugador primero
		if(mano==1) {
			//cartas con diferente palo, gana humano
			if((humano.getPalo()!=ordenador.getPalo()) && (humano.getPalo()!=paloTriunfo)) {
				jugadoruno.push(humano);
				jugadoruno.push(ordenador);
				System.out.println("gana humano");
				mano = 1;
			}
			//cartas con el mismo palo
		}else if((humano.getPalo()==ordenador.getPalo()) && (humano.getPalo()!=paloTriunfo)){
			//si humano mayor, gana humano
			if((humano.getNumero().getValor())>(ordenador.getNumero().getValor())) {
				jugadoruno.push(humano);
				jugadoruno.push(ordenador);
				System.out.println("gana humano");
				mano = 1;
				//si humano menor, gana ordenador
			}else if((humano.getNumero().getValor())<(ordenador.getNumero().getValor())){
				jugadordos.push(humano);
				jugadordos.push(ordenador);
				System.out.println("gana ordenador");
				mano = 2;
			}
			//turno del ordenador primero
		}else if(mano==2) {
			//diferente palo, gana ordenador
			if((humano.getPalo()!=ordenador.getPalo()) && (ordenador.getPalo()!=paloTriunfo)) {
				jugadordos.push(humano);
				jugadordos.push(ordenador);
				System.out.println("gana ordenador");
				mano = 2;
				//mismo palo, se comprueba cual mayor
			}else if((humano.getPalo()==ordenador.getPalo()) && (ordenador.getPalo()!=paloTriunfo)){
				//mayor la del ordenador, gana ordenador
				if((humano.getNumero().getValor())<(ordenador.getNumero().getValor())) {
					jugadordos.push(humano);
					jugadordos.push(ordenador);
					System.out.println("gana ordenador");
					mano = 2;
					// mayor la del humano, gana el humano
				}else {
					jugadoruno.push(humano);
					jugadoruno.push(ordenador);
					System.out.println("gana humano");
					mano = 1;
				}
			}
		}
		//devolvemos z para saber quien comienza el turno en la siguiente ronda y quien roba primero
		return mano;
	}
	public static int contarPuntos(Stack<Carta> puntosTotal,int puntosJugador) {
		int capacidad =(int) puntosTotal.size();
		for(int i=0; i< capacidad; i++) {
			puntosJugador += puntosTotal.pop().getNumero().getPuntos();
		}
		return puntosJugador;
	}
	
	public static void robarCarta(Baraja baraja, Vector<Carta> manos) {
		manos.add(baraja.DarCarta());
	}
	@SuppressWarnings("unchecked")
	public static void robarDespues(Baraja baraja, int mano, @SuppressWarnings("rawtypes") Vector manos[]) {
		if(baraja.getCartas().size()>0) {
			if(mano==1) {
				robarCarta(baraja, manos[0]);
				robarCarta(baraja, manos[1]);
			}else {
				robarCarta(baraja, manos[1]);
				robarCarta(baraja, manos[0]);
			}
		}
	}
	public Carta jugadaCPU(@SuppressWarnings("rawtypes") Vector manos, int mano) {
		Carta ordenador=null;
		int x;
		if(mano==1) {
			x = (int) (Math.random()*manos.size())+1;
			ordenador =(Carta) manos.remove(x-1);
			System.out.println("El ordenador juega: " + ordenador);
		}else {
			x = (int) (Math.random()*manos.size())+1;
			ordenador =(Carta) manos.remove(x-1);
			System.out.println("El ordenador juega: " + ordenador);
		}
		return ordenador;
		
	}
	public Carta jugadaHumano(@SuppressWarnings("rawtypes") Vector manos) {
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner (System.in);
		Carta humano;
		int x;
		System.out.println("Tu mano es:\n" + manos);
		System.out.println("Que carta quieres jugar?: ");
		x = teclado.nextInt();
		humano =(Carta) manos.remove(x-1);
		System.out.println("Has jugado:" + humano);	
		return humano;
	}
}