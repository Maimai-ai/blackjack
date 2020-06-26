/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes.modele;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;
import java.util.Collections;

/**
 * Paquet est une classe qui herite de Observable, donc est observable
 * Cette classe nous permet de creer un paquet de carte et de le manipuler
 */
public class Paquet extends Observable{
	public ArrayList<Carte> list;
	public final static String[] forme52={"as","2","3","4","5","6","7","8","9","10","valet","dame","roi"};
	public final static String[] forme32={"as","7","8","9","10","valet","dame","roi"};
	public final static String[] couleur={"pique","carreau","trefle","coeur"};

	public Paquet(){
		this.list = new ArrayList<Carte>();
	}

	/**
	 * Methode qui cree un paquet de carte, cela peut etre un paquet de 52 cartes comme de 32 cartes
	 */
	public void initialiser(){
		//Factory.jeu32();
		//        Factory.jeu52();
		for(int i=0;i<couleur.length;i++) {
			for(int j=0; j<forme52.length;j++) {
				Carte c = new Carte(forme52[j],couleur[i]);
				this.list.add(c);
			}
		}
	}

	/**
	 * Accesseur de la liste de Carte
	 * @return une liste de Carte
	 */
	public ArrayList<Carte> getList() {
		return this.list;
	}

	/**
	 * Accesseur du nombre de Carte dans le paquet
	 * @return un entier
	 */
	public int getNbCartes() {
		return this.list.size();
	}

	/**
	 * Methode qui nous permet de connaitre la hauteur d une carte dans le paquet
	 * @param i un entier qui représente la position de la carte dans le paquet 
	 * @return un String soit la hauteur
	 */   
	public String getHauteur(int i){
		Carte c = this.list.get(i);
		return c.getHauteur();
	}

	/**
	 * Methode qui nous permet d ajouter une carte en haut du paquet
	 * @param c une Carte que nous ajoutons
	 */
	public void addCardTop(Carte c){
		this.list.add(0,c);
	}

	/**
	 * Methode qui nous permet d ajouter une carte en bas du paquet
	 * @param c une Carte que nous ajoutons
	 */
	public void addCardEnd(Carte c){
		this.list.add(c);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Methode qui nous retourne une Carte aleatoirement dans le paquet
	 * @return une Carte
	 */
	public Carte tirageHasard(){
		int position = (int)(Math.random()*this.list.size());
		return this.list.get(position);
	}

	/**
	 * Methode qui nous permet de melanger le paquet de Carte
	 */
	public void melangeCarte(){
		Collections.shuffle(this.list);
	}

	/**
	 * Methode qui nous permet de couper le paquet
	 */    
	public void couperPaquet(){
		int position = (int)(Math.random()*this.list.size());
		ArrayList<Carte> liste1 = new ArrayList<Carte>();
		ArrayList<Carte> liste2 = new ArrayList<Carte>();

		//tant que la position fait partie des 3 premieres cartes du paquets ou bien des 3 
		do{
			position = (int)(Math.random()*this.list.size());
		}while(position<3 || position>48);

		//Cree une liste avec le premier paquet coupe
		for(int i = 0 ; i<position ;i++){
			liste1.add(this.list.get(i));
		}
		//Cree une liste avec le second paquet coupe
		for(int j = position; j< this.list.size(); j++){
			liste2.add(this.list.get(j));
		}
		this.list.clear();
		this.list.addAll(liste2);
		this.list.addAll(liste1);
	}

	/**
	 * Methode qui nous permet de retirer une Carte dans le paquet
	 * @param n un entier qui correspond a l emplacement de la Carte dans le paquet
	 * @return la Carte retirer du paquet
	 */
	public Carte retire(int n){
		if(this.list.size()<=n) {
			return null;
		}
		else {
			Carte c = this.list.get(n);
			this.list.remove(n);
			this.setChanged();
			this.notifyObservers();
			return c;
		}

	}

	/**
	 * Surcharge de la methode retire(n), ici la methode permet de retirer la premiere carte du paquet
	 * @return la premiere Carte du paquet
	 */
	public Carte retire(){
		return retire(0);
	}
}
