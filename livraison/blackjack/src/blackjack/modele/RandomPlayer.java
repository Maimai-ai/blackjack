package blackjack.modele;

import cartes.modele.Paquet;

import java.util.Random;

/**
 * Classe RandomPlayer qui herite de la methode abstraite GamePlayer
 */
public class RandomPlayer extends GamePlayer{
	private Random randomGenerator;
	public Paquet paquet;
	public int jeton;
	Blackjack bj;
	static boolean bloque = false;

	/**
	 * Constructeur de RandomPlayer qui prends en argument un Random et un paquet de carte
	 * @param randomGenerator une instance de Random et permet d'utiliser ces methodes
	 * @param paquet une instance de Paquet
	 */
	public RandomPlayer(Random randomGenerator, Paquet paquet){
		super(paquet);
		this.randomGenerator=randomGenerator;
		this.jeton=500;
	}

	/**
	 * Constructeur de RandomPlayer qui prends en argument un Random et un paquet de carte
	 * @param randomGenerator une instance de Random et permet d'utiliser ces methodes
	 * @param paquet une instance de Paquet
	 * @param bj une instance de Blackjack
	 */
	public RandomPlayer(Random randomGenerator, Paquet paquet,Blackjack bj){
		super(paquet,bj);
		this.randomGenerator=randomGenerator;
		this.jeton=500;
	}

	/**
	 * Redefinition de la methode continuer, si le nombre total de carte est plus grand ou egale a 21 il renvoit false sinon il choisit aleatoirement
	 * @param blackjack une instance de Blackjack
	 * @return un boolean
	 */
	@Override
	public boolean continuer(Blackjack blackjack){
		if(blackjack.getTotal()>=21) {
			return false;
		}
		else {
			int position = this.randomGenerator.nextInt(2);
			if(position==0) {
				return true;
			}
			return false;
		}
	}
	
	/**
	 * Redefinition de la methode choixMise qui permet de choisir une mise au hasard
	 * @param n un entier
	 */
	@Override
	public void choixMise(int n) {
		int [] tab = {1,2,5,10,25,50};
		int number=this.randomGenerator.nextInt(tab.length);
		//if(!bloque) {
			//setMise(tab[number]);
			//bloque = true;
		//}
		for(int i =0; i<n;i++){
			number=this.randomGenerator.nextInt(tab.length);
			setMise(tab[number]);
		}
	}

	/**
	 * Redefinition de la methode toString
	 * @return une String
	 */
	@Override
	public String toString() {
		return "Joueur aleatoire #"+this.hashCode();
	}
}
