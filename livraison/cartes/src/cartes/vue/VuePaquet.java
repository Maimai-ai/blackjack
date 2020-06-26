/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes.vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Observable;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

import cartes.modele.*;
import cartes.*;
/**
 * Une Vue des cartes du joueurs qui herite de la classe abstraite VueCarte et qui implemente Observer
 * Il observe le Paquet de celui ci
 */
public class VuePaquet extends VueCarte implements Observer {
	public Paquet paquet;
	public ArrayList<Carte> listeCarte;
	public int nbCartes;
	private int taillePolice;
	private String titre;
	private int taille;

	/**
	 * Constructeur de VuePaquet qui prend en argument le paquet, soit la pioche
	 * @param paquet une instance de Paquet
	 * @param titre une instance de String
	 */
	public VuePaquet(Paquet paquet, String titre) {
		super(paquet,titre);
		this.listeCarte = paquet.getList();
		this.nbCartes = paquet.getNbCartes();
	}

	/**
	 * Methode qui permet de dessiner la paquet de carte
	 * @param g une instance de Graphics
	 */
	@Override
	public void drawCartes(Graphics g) {
		int largeurPannel = this.getWidth();
		int largeurCarte = 169;
		int hauteurCarte = 253;
		if(largeurPannel > 1500){
			largeurCarte =155;
			hauteurCarte = 230;
		}

		this.taille = getEpaisseur();

		int posX = this.getPosX();
		int posY = this.getPosY() + this.taillePolice+3;

		if(getEpaisseur()!=0) {
			for(Carte uneCarte : getListeDesCartes()) {
				if (uneCarte == getListeDesCartes().get(taille-1)) { // la carte a piocher la derniere
					posX = this.getPosX();
					posY = this.getPosY() + this.taillePolice+3;
					g.setColor(Color.BLACK);

				}
				else {
					posX = posX + (largeurCarte - (largeurCarte-1));
					posY = posY + (hauteurCarte - ( hauteurCarte -1));
					g.setColor(Color.BLUE);
				}


				g.fillRoundRect(posX, posY,largeurCarte,hauteurCarte,5,5);
			}
		}
		else {
			g.drawRect(posX, posY,largeurCarte,hauteurCarte);
		}
	}
}
