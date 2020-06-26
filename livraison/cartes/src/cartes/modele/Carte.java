/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes.modele;

/**
 * Classe Carte qui permet de creer une carte avec sa hauteur et sa couleur
 */
public class Carte {
	public String hauteur;
	public String couleur;

	/**
	 * Constructeur de Carte 
	 * @param hauteur un String
	 * @param couleur un String
	 */
	public Carte(String hauteur, String couleur){
		this.hauteur = hauteur;
		this.couleur = couleur;
	}

	/**
	 * Accesseur a la hauteur de la carte
	 * @return une String
	 */
	public String getHauteur(){
		return this.hauteur;
	}

	/**
	 * Accesseur à la couleur de la carte
	 * @return une String
	 */
	public String getCouleur(){
		return this.couleur;
	}

	/**
	 * Methode qui permet de savoir si notre carte est une Buche ou non
	 * @return un boolean
	 */
	public boolean isBuche(){
		if(this.hauteur == "roi" || this.hauteur == "valet" || this.hauteur == "reine" || this.hauteur == "10"){
			return true;
		}
		return false;
	}

	/**
	 * Redefinition de la methode toString()
	 * @return une String
	 */
	@Override
	public String toString(){
		return this.hauteur +" de "+ this.couleur;
	}
}
