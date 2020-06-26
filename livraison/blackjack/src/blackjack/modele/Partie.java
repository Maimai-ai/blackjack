package blackjack.modele;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import cartes.modele.Paquet;
/**
 * Partie est une classe qui represente une partie de jeude blackjack
 */
public class Partie {
	Paquet paquet;

	Blackjack playerGame;
	Blackjack randomGameA;
	Blackjack randomGameB;
	Blackjack randomGameC;
	Blackjack croupierGame;

	GamePlayer player;
	GamePlayer randomA;
	GamePlayer randomB;
	GamePlayer randomC;
	GamePlayer croupier;
	
	boolean finPartie=false;
	boolean over=false;
	
	ArrayList<Blackjack> tousLesParties;
	
	/**
	 * Constructeur de Partie vide
	 */
	public Partie() {
	}
	/**
	 * Constructeur de Partie
	 * @param paquet une instance de Paquet
	 * @param playerGame une instance du Blackjack
	 * @param randomGameA une instance du Blackjack
	 * @param randomGameB une instance du Blackjack
	 * @param randomGameC une instance du Blackjack
	 * @param croupierGame une instance du Blackjack
	 */
	public Partie(Paquet paquet, Blackjack playerGame, Blackjack randomGameA, Blackjack randomGameB, Blackjack randomGameC, Blackjack croupierGame){
		this.paquet = paquet;
		
		this.playerGame = playerGame;
		this.randomGameA = randomGameA;
		this.randomGameB = randomGameB;
		this.randomGameC = randomGameC;
		this.croupierGame = croupierGame;
		
		this.player = playerGame.getPlayer();
		this.randomA = randomGameA.getPlayer();
		this.randomB = randomGameB.getPlayer();
		this.randomC = randomGameC.getPlayer();
		this.croupier = croupierGame.getPlayer();
		
		this.tousLesParties = new ArrayList<Blackjack>();
		this.tousLesParties.add(this.playerGame);
		this.tousLesParties.add(this.randomGameA);
		this.tousLesParties.add(this.randomGameB);
		this.tousLesParties.add(this.randomGameC);
	}
	/**
	 * Distribue les cartes au départ
	 */
	public void distribution(){
		for(int i = 0; i<2;i++) {
			this.playerGame.ajoutCarte();
			this.randomGameA.ajoutCarte();
			this.randomGameB.ajoutCarte();
			this.randomGameC.ajoutCarte();
			this.croupierGame.ajoutCarte();
		}
	}
	
	/**
	 * Distribue les cartes au départ
	 * @param player une instance d'un joueur
	 * @return une instance de Blackjack
	 */
	public Blackjack getBlackjack(GamePlayer player){
		for(int i = 0 ; i<this.tousLesParties.size();i++) {
			if(this.tousLesParties.get(i).getPlayer()==player) {
				return this.tousLesParties.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Voit si toutes les parties sont finies
	 * @return un boolean
	 */
	public boolean getFinPartie() {
		int n =0;
		for(int i =1; i<this.tousLesParties.size();i++) {
			System.out.println(this.tousLesParties.get(i).getPlayer() + "a t'il fini de jouer ? ");
			if(!this.tousLesParties.get(i).getFin()) {
				System.out.println(this.tousLesParties.get(i).getPlayer() + "n'a pas fini de jouer");
				return false;
			}
			else {
				System.out.println(this.tousLesParties.get(i).getPlayer() + "a fini de jouer");
				n++;
			}
		}
		return true;
	}
	
	/**
	 * Change la valeur booleene de la fin de partie
	 * @param finPartie un boolean
	 */
	public void setFinPartie(boolean finPartie) {
		this.finPartie = finPartie;
	}
	
	/**
	 * Creer une nouvelle partie avec les joueurs
	 * @param pA une instance d'un joueur
	 * @param pB une instance d'un joueur
	 * @param pC une instance d'un joueur
	 * @param pD une instance d'un joueur
	 * @param pE une instance d'un joueur
	 * @return une instance de Partie
	 */
	public Partie newGame(GamePlayer pA,GamePlayer pB,GamePlayer pC,GamePlayer pD,GamePlayer pE) {
		Paquet paq = new Paquet();
		paq.initialiser();
		paq.melangeCarte();
		Paquet paquetA = new Paquet();
		Paquet paquetB = new Paquet();
		Paquet paquetC = new Paquet();
		Paquet paquetD = new Paquet();
		Paquet paquetE = new Paquet();

		pA.setPaquet(paquetA);
		pB.setPaquet(paquetB);
		pC.setPaquet(paquetC);
		pD.setPaquet(paquetD);
		pE.setPaquet(paquetE);

		for(int i = 0; i < this.tousLesParties.size();i++){
			this.tousLesParties.get(i).getPlayer().initialiseMise();
			this.tousLesParties.get(i).getPlayer().setJetonInitial(this.tousLesParties.get(i).getPlayer().getJeton());
		}

		Blackjack jeuA = new Blackjack(pA,paq);
		Blackjack jeuB = new Blackjack(pB,paq);
		Blackjack jeuC = new Blackjack(pC,paq);
		Blackjack jeuD = new Blackjack(pD,paq);
		Blackjack jeuE = new Blackjack(pE,paq);
		Partie partie = new Partie(paq,jeuA, jeuB,jeuC,jeuD,jeuE);
		return partie;
	}
	
	/**
	 * evaluation des Jetons
	 */
	public void remiseJeton() {
		int totalCroupier = this.croupierGame.getTotal();
		for(int i =0; i<this.tousLesParties.size(); i++) {
			Blackjack jeuI = this.tousLesParties.get(i);
			if(totalCroupier < jeuI.getTotal()) {
				jeuI.getPlayer().gain();
			}
			else if(totalCroupier == jeuI.getTotal()) {
				jeuI.getPlayer().recupereMise();
			}
			else if(totalCroupier > jeuI.getTotal() && totalCroupier <21) {
				jeuI.getPlayer().perte();
			}
			else if(jeuI.plusDe21()) {
				jeuI.getPlayer().perte();
			}
			else if(totalCroupier >21) {
				if(!jeuI.plusDe21()) {
					jeuI.getPlayer().gain();
				}
				else{
					jeuI.getPlayer().perte();				
				}
			}
			else if(jeuI.getBlackjackWin()) {
				jeuI.getPlayer().gainBlackjack();
			}
		}
	}
	
	/**
	 * Verifie si terminer
	 * @return un boolean
	 */
	public boolean isOver() {
		return this.over;
	}
	
	/**
	 * Change la valeur de terminer
	 * @param bool un boolean
	 */
	public void setOver(boolean bool) {
		this.over = bool;
	}
	
	/**
	 * Accesseur des joueurs
	 * @return un GamePlayer le joueur
	 */
	public GamePlayer getPlayer(){
		return this.player;
	}
	
	/**
	 * Accesseur des joueurs
	 * @return un GamePlayer un random
	 */
	public GamePlayer getRandomA(){
		return this.randomA;
	}
	/**
	 * Accesseur des joueurs
	 * @return un GamePlayer un random
	 */
	public GamePlayer getRandomB(){
		return this.randomB;
	}
	/**
	 * Accesseur des joueurs
	 * @return un GamePlayer un random
	 */
	public GamePlayer getRandomC(){
		return this.randomC;
	}
	
	/**
	 * Accesseur au Croupier
	 * @return un GamePlayer
	 */
	public GamePlayer getCroupier(){
		return this.croupier;
	}
	
	/**
	 * Accesseur au paquet du joueur
	 * @return un Paquet
	 */
	public Paquet getPaquet(){
		return this.paquet;
	}
}
