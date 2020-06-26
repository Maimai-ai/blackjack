package blackjack.modele;

import java.util.Scanner;

import cartes.modele.Paquet;

/**
 * Classe Player qui herite de la classe abstraite GamePlayer
 */
public class Player extends GamePlayer{
	public String joueur;
	public Paquet paquet;
	public int jeton;
	Blackjack bj;
	static boolean bloque = false;

	/**
	 * Constructeur de Player qui prends en argument un paquet de carte
	 * @param paquet une instance de Paquet
	 */
	public Player(Paquet paquet){
		super(paquet);
		this.joueur="joueur";
		this.jeton= 500;
	}
	/**
	 * Constructeur de Player qui prends en argument un paquet de carte
	 * @param paquet une instance de Paquet
	 * @param bj une instance de Blackjack
	 */
	public Player(Paquet paquet,Blackjack bj){
		super(paquet,bj);
		this.joueur="joueur";
		this.jeton= 500;
	}

	/**
	 * Redefinition de la methode continuer qui demande au joueur s'il veut continuer de jouer
	 * @param blackjack instance de Blackjack
	 * @return un boolean 
	 */
	@Override
	public boolean continuer(Blackjack blackjack){
		Scanner scan = new Scanner(System.in);

		System.out.println("Voulez vous reprendre une carte ? si oui = 0 si non = 1 :");
		String str = scan.nextLine();
		if(str.equals("0")){
			blackjack.ajoutCarte();
			scan.close();
			return true;
		}
		return false;
	}
	
	/**
	 * Methode qui permet de choisir la mise du joueur
	 * @param n un entier
	 */
	@Override
	public void choixMise(int n) {
		//if(!bloque) {
			setMise(n);
			bloque = true;
		//}
	}

	/**
	 * Redefinition de la methode toString() 
	 * @return un String
	 */
	@Override
	public String toString() {
		return this.joueur;
	}
}
