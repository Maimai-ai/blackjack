/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.modele;

import cartes.modele.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

/**
 * Blackjack est une classe qui represente le jeu il herite de Observable
 */
public class Blackjack extends Observable{

	public GamePlayer joueur;
	public Paquet paq;
	public ArrayList<Carte> listCarte; // Les cartes en sa possession
	public int valeur;
	public int nbAs=0;
	boolean fin = false;

	/**
	 * Constructeur de Blackjack
	 * @param joueur une instance de GamePlayer
	 * @param paq une instance du paquet de carte, soit la pioche
	 */
	public Blackjack(GamePlayer joueur,Paquet paq){
		this.joueur = joueur;
		this.listCarte=this.joueur.getPaquet().getList();
		this.valeur=valeur;
		this.paq=paq;
		this.nbAs=nbAs;
	}

	/**
	 * Accesseur au joueur
	 * @return un GamePlayer
	 */
	public GamePlayer getPlayer(){
		return this.joueur;
	}
	
	/**
	 * Accesseur au boolean fin
	 * @return un boolean
	 */
	public boolean getFin(){
		return this.fin;
	}

	/**
	 * Accesseur au paquet du joueur
	 * @return un Paquet
	 */
	public Paquet getPaquet(){
		return this.joueur.getPaquet();
	}

	/**
	 * Lorqu'un joueur a un Blackjack c'est a dire qu'il a deux cartes et que l'ensemble est egale a 21
	 * @return un boolean
	 */
	public boolean getBlackjackWin(){
		if(this.listCarte.size()==2){
			if(this.listCarte.get(0).isBuche() && this.listCarte.get(1).getHauteur()=="as"){
				return true;
			}
			if(this.listCarte.get(1).isBuche() && this.listCarte.get(0).getHauteur()=="as"){
				return true;
			}
		}
		return false;
	}

	/**
	 * Cette methode permet d'ajouter une carte en haut de votre du paquet du joueur
	 */
	public void ajoutCarte(){
	
			if(this.paq.getNbCartes() != 0) {
				Carte c = this.paq.retire();
				this.joueur.getPaquet().addCardTop(c);
			}
			
			this.setChanged();
			this.notifyObservers();
		
	}

	/**
	 * Cette methode permet de calculer la valeur total de l'ensemble des cartes dans compter les AS
	 */
	public void valeurTotalSansAs(){
		int compteur = 0;
		for(int i =0; i<getPaquet().getList().size(); i++){
			Carte c = getPaquet().getList().get(i);
			if(c.getHauteur().equals("10") || c.getHauteur().equals("valet") || c.getHauteur().equals("dame") || c.getHauteur().equals("roi")){
				compteur+=10;
			}
			else if(!c.getHauteur().equals("as")){
				int entier = Integer.parseInt(c.getHauteur());
				compteur+=entier;
			}
			else {
				this.nbAs++;
			}
		}
		this.valeur = compteur;
	}

	/**
	 * Cet methode permet de calculer au mieux la valeur total des as dans les mains du joueurs
	 */
	public void choixAs(){
		int[] i = {1,2,3,4};
		if(this.nbAs!=0){
			for(int n =0; n < i.length; n++){
				if(this.nbAs == i[n]){
					if(this.valeur>(21-11-(i[n]-1))){
						this.valeur+=i[n];
					}
					else{
						this.valeur+=11+(i[n]-1);
					}
				}
			}
		}
	}

	/**
	 * Cette methode permet d'avoir la valeur total de toutes les cartes dans les mains du joueurs
	 * @return un entier
	 */
	public int getTotal() {
		valeurTotalSansAs();
		choixAs();
		return this.valeur;
	}
	
	/**
	 * Methode qui permet de savoir si un joueur a plus de 21
	 * @return un boolean
	 */
	public boolean plusDe21(){
		if(getTotal()>21) {
			finPartie(true);
			return true;
		}
		return false;
	}
	
	/**
		* Methode qui permet de modifier la variable fin
		* @param bool un boolean
	*/
	public void finPartie(boolean bool) {
		this.fin = bool;
	}
}
