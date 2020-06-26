/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.vue;

import cartes.modele.Carte;
import cartes.modele.Paquet;

import cartes.vue.VueCarte;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import blackjack.modele.GamePlayer;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

/**
 * VueRandom herite de la classe abstraite VueCarte et implemente Observer
 * Il s'agit de la Vue d'un joueur Random
 */
public class VueRandom extends VueCarte implements Observer{

	public Paquet paquet;
	public ArrayList<Carte> listeCarte;
	private int taillePolice;
	private String titre;
	private int taillePaquet;
	GamePlayer player;
	private int mise;

	/**
	 * Constructeur de VueRandom
	 * @param paquet une instance de Paquet, le paquet du joueur
	 * @param player une instance de Gameplayer le joueur
	 * @param titre le titre de la vue
	 */
	public VueRandom(Paquet paquet, GamePlayer player, String titre){
		super(paquet,titre);
		this.listeCarte = paquet.getList();
		this.taillePaquet = paquet.getNbCartes();
		this.player = player;
		this.mise = player.getMise();

	}

	/**
	 * drawCartes qui permet de dessiner des cartes, la premiere etant visible et les autres non
	 * @param g une instance de Graphics
	 */
	public void drawCartes(Graphics g) {
		//    	 hauteur du JPanel
		int largeurPannel = this.getWidth();


		//Longueur et largeur d'une seule carte
		int largeurCarte = 126;
		int hauteurCarte = 189;

		int ligne =1;

		int posX = this.getPosX();
		int posY = this.getPosY() + this.taillePolice+3;

		int taillePolice = (int)(hauteurCarte/3);
		g.setFont(new Font("Times new Roman", Font.PLAIN, taillePolice));

		if(getEpaisseur() !=0) {

			//Dessine la premiere carte
			Carte c =getListeDesCartes().get(0);
			posX = this.getPosX();
			if(c.getCouleur().equals("pique")||c.getCouleur().equals("trefle")) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.RED);
			}
			String couleur = c.getCouleur();
			String forme = c.getHauteur();

			g.fillRect(posX, posY,largeurCarte,hauteurCarte);

			g.setColor(Color.YELLOW);
			g.drawRect(posX, posY,largeurCarte,hauteurCarte);

			g.setColor(Color.BLUE);
			g.drawString(couleur, posX+3, posY+hauteurCarte/2);
			g.drawString(forme, posX+3, posY+taillePolice+hauteurCarte/4);

			//Cache toutes las autres cartes
			for(Carte carte: this.listeCarte) {

				if (carte != getListeDesCartes().get(0)) {
					posX = largeurCarte + posX +3;

					if(posX+3> this.getWidth()-largeurCarte){
						posX=this.getPosX();
						posY= hauteurCarte*ligne + 5;
					}
					peindreSymbol( g, couleur, forme, posX+3,posY+taillePolice+hauteurCarte/2, posX+3, posY+hauteurCarte/2,largeurCarte, hauteurCarte, posX, posY );

					
				}
				else {
					g.fillRoundRect(posX, posY, largeurCarte, hauteurCarte,5,5);
				}
			}
		}
		else{
			g.setColor(Color.BLUE);
			g.drawRect(posX+3, posY,largeurCarte,hauteurCarte);
			g.drawString("vide", posX+3, posY+hauteurCarte/2);

		}
	}
}
