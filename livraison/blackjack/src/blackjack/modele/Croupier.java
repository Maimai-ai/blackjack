package blackjack.modele;

import cartes.modele.Paquet;
import java.util.Random;

/**
 * Classe RandomPlayer qui herite de la methode abstraite GamePlayer
 */
public class Croupier extends GamePlayer{
	private Random randomGenerator;
	public Paquet paquet;
	public static int jeton=Integer.MAX_VALUE;
	Blackjack bj;

	/**
	 * Constructeur de RandomPlayer qui prends en argument un Random et un paquet de carte
	 * @param randomGenerator une instance de Random et permet d'utiliser ces methodes
	 * @param paquet une instance de Paquet
	 */
	public Croupier(Random randomGenerator, Paquet paquet){
		super(paquet);
		this.randomGenerator=randomGenerator;
		this.jeton=jeton;
	}

	/**
	 * Constructeur de RandomPlayer qui prends en argument un Random et un paquet de carte
	 * @param randomGenerator une instance de Random et permet d'utiliser ces methodes
	 * @param paquet une instance de Paquet
	 * @param bj une instance de Blackjack
	 */
	public Croupier(Random randomGenerator, Paquet paquet,Blackjack bj){
		super(paquet,bj);
		this.randomGenerator=randomGenerator;
		this.jeton=jeton;
	}

	/**
	 * Redefinition de la methode continuer, si le nombre total de carte est plus grand ou egale a 21 il renvoit false sinon il choisit aleatoirement
	 * @param blackjack une instance de Blackjack
	 * @return un boolean
	 */
	@Override
	public boolean continuer(Blackjack blackjack){
		if(blackjack.getTotal()>=17) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Methode vide car le croupier ne prends aucune mise
	 */
	@Override
	public void choixMise(int n) {
		this.mise = n;
	}
	/**
	 * Redefinition de la methode toString
	 * @return une String
	 */
	@Override
	public String toString() {
		return "Croupier";
	}
}
