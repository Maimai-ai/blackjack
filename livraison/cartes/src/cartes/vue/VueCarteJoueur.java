/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes.vue;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;


import cartes.modele.*;
/**
 * VueCarteJoueur est une vue des cartes du joueur, il herite de la classe abstraite VueCarte
 * et implemente Observer
 */
public class VueCarteJoueur extends VueCarte implements Observer{

	public Paquet paquet;
	public ArrayList<Carte> listeCarte;
	private int taillePolice;
	private String titre;
	private int taillePaquet;

	/**	
	 * Constructeur de VueCarteJoueur qui prend le paquet du joueur
	 * @param paquet une instance de Paquet
	 * @param titre une instance de String
	 */
	public VueCarteJoueur(Paquet paquet, String titre){
		super(paquet,titre);
		this.listeCarte = paquet.getList();
	}

	/**
	 * Methode qui permet de dessiner la paquet de carte
	 * @param g une instance de Graphics
	 */
	@Override
	public void drawCartes(Graphics g) {
		int largeurPannel = this.getWidth();

		int largeurCarte = 126;
		int hauteurCarte = 189;

		// Si la largeur de l'ecran est plus grand que 1500
		if(largeurPannel > 1500){
			largeurCarte =155;
			hauteurCarte = 230;
		}

		//Longueur et largeur d'une seule carte

		int ligne =1;


		int posX = this.getPosX();
		int posY = this.getPosY() + getTaillePolice()+3;

		if(getEpaisseur() !=0) {
			for(Carte carte: getListeDesCartes()) {
				//Attribue l'emplacement des carte visible du joueur
				if (carte == getListeDesCartes().get(0)) {
					posX = this.getPosX();
				}
				else {
					posX = largeurCarte + posX +3;
				}

				String couleur = carte.getCouleur();
				String forme = carte.getHauteur();
				//String laValeur = Integer.toString(Paquet.valeurCarte(uneCarte));

				int taillePolice = (int)(hauteurCarte/10);
				g.setFont(new Font("Times new Roman", Font.PLAIN, taillePolice));

				// Permet affichage
				if(posX+3> largeurPannel-largeurCarte){
					posX=this.getPosX();
					posY= hauteurCarte*ligne + 5;
					ligne++;
				}
				peindreSymbol( g, couleur, forme, posX+3,posY+taillePolice+hauteurCarte/2, posX+3, posY+hauteurCarte/2,largeurCarte, hauteurCarte, posX, posY );	

			}
		}
		else{
			g.setColor(Color.BLUE);
			g.drawRect(posX+3, posY,largeurCarte,hauteurCarte);
			g.drawString("NC", posX+3, posY+hauteurCarte/2);

		}
	}
}
