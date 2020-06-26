package blackjack.modele;

import java.util.Observable;

import cartes.modele.Paquet;

/**
 * GamePlayer est une classe abstraite 
 */
public abstract class GamePlayer extends Observable {
	int mise;
	int jeton;
	int jetonInitial;
	public Paquet paquet;
	Blackjack bj;
	static boolean bloque = false;

	/**
	 * Constructeur de GamePlayer qui prend en argument un paquet de carte
	 * @param paquet une instance de Paquet
	 */
	public GamePlayer(Paquet paquet){
		this.jeton = 500;
		this.jetonInitial=this.jeton;
		this.mise = 0;
		this.paquet=paquet;
	}
	/**
	 * Constructeur de GamePlayer qui prend en argument un paquet de carte
	 * @param paquet une instance de Paquet
	 * @param bj une instance de Blackjack
	 */
	public GamePlayer(Paquet paquet, Blackjack bj){
		this.jeton = 500;
		this.mise = 0;
		this.bj = bj;
		this.paquet=paquet;
	}

	/**
	 * Accesseur à la mise du joueur
	 * @return un entier
	 */
	public int getMise(){
		return this.mise;
	}

	public void initialiseMise(){
		this.mise = 0;
	}
	
	/**
	 * Accesseur au Blackjack du joueur
	 * @return une instance de type Blackjack
	 */
	public Blackjack getBlackjack(){
		return this.bj;
	}
	
	/**
	 * Mutateur au Blackjack du joueur
	 * @param blj une instance de type Blackjack
	 */
	public void setBlackjack(Blackjack blj){
		this.bj=blj;
	}
		
	/**
	 * Mutateur de la mise du joueur
	 * @param n une entier
	 */
	public void setMise(int n){
		this.mise+=n;
		this.jeton-=n;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Accesseur au nombre de jeton du joueur qui ne sont pas mis en jeu
	 * @return un entier
	 */
	public int getJeton(){
		return this.jeton;
	}

	/**
	 * Accesseur au paquet du joueur
	 * @return un Paquet
	 */
	public Paquet getPaquet(){
		return this.paquet;
	}

	/**
	 * Mutateur au paquet du joueur
	 * @param paquet un Paquet
	 */
	public void setPaquet(Paquet paquet){
		this.paquet=paquet;
	}

	/**
	 * Methode qui permet de doubler la mise du joueur
	 */
	public void doublerMise(){
		if(this.jeton-this.mise >=0){
			this.jeton-=this.mise;
			this.mise=this.mise*2;
			this.setChanged();
			this.notifyObservers();
		}
	}

	public int gainPerte(){
		return this.jeton-this.jetonInitial;
	}

	public void setJetonInitial(int n){
		this.jetonInitial=n;
	}

	/**
	 * La methode gain permet de mettre la mise à 0 et d'ajouter le double de la mise dans les jetons du joueur
	 */
	public void gain(){
		int totalGain = this.mise*2;
		this.mise=0;
		this.jeton+=totalGain;
	}

	/**
	 * La methode gainBlackjack permet de mettre la mise à 0 et gagner 1 et demi de la mise 
	 */
	public void gainBlackjack(){
		int totalGain = this.mise*2+(this.mise/2);
		this.mise=0;
		this.jeton+=totalGain;
	}

	/**
	 * La methode perte fait perdre la mise au joueur
	 */
	public void perte(){
		this.mise=0;
	}

	/**
	 * recupereMise permet de recuperer ce que nous avons mis en Mise lors d'une partie nulle
	 */
	public void recupereMise() {
		this.jeton+=this.mise;
	}

	/**
	 * Methode abstraite non definit
	 * @param blackjack une instance de Blackjack
	 * @return un boolean
	 */
	public abstract boolean continuer(Blackjack blackjack);

	/**
		* Methode abstraite non definit
		* @param n un entier
	*/
	public abstract void choixMise(int n);
}
