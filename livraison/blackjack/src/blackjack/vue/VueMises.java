/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.vue;

import cartes.modele.Carte;
import cartes.modele.Paquet;

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
 * VueMises herite de JPanel et implemente Observer
 * Il s'agit de la Vue des mises d'un joueur
 */
public class VueMises extends JPanel implements Observer{

	private int taillePolice;
	private String titre;
	private GamePlayer player;
	private String mise;
	private String jetons;

	/**
	 * Constructeur de VueMises
	 * @param player une instance de GamePlayer, le joueur
	 */
	public VueMises(GamePlayer player){
		super();
		this.player = player;
		this.titre="";
	}

	/**
	 * Accesseur a la hauteur de l'ecran de l'ordinateur
	 * @return un entier
	 */
	public int getHauteur(){
		int hauteur = this.getHeight();
		return hauteur;
	}

	/**
	 * Accesseur à la largeur de l'ecran de l'ordinateur
	 * @return un entier
	 */
	public int getLargeur() {
		int largeur = this.getWidth();
		return largeur;
	}

	/**
	 * Accesseur a une position X
	 * @return un entier
	 */
	public int getPosX() {
		int posX = getLargeur()/50;
		return posX;
	}

	/**
	 * Accesseur a une position Y 
	 * @return un entier
	 */
	public int getPosY() {
		int posY = getHauteur()/50;
		return posY;
	}

	/**
	 * Mutateur qui permet de modifier l'instance taillePolice
	 * @param calcul un entier
	 */
	public void setTaillePolice(int calcul) {
		this.taillePolice = getLargeur()/calcul;
	}

	/**
	 * getTaillePoliceTitre est un accesseur a l'instance taillePolice
	 * @return un entier
	 */
	public int getTaillePoliceTitre() {
		return this.taillePolice;
	}

	/**
	 * drawTitre est une methode qui permet d'ecrire un titre
	 * @param g une instance de Graphics
	 * @param titre une instance de String
	 */
	public void drawTitre(Graphics g, String titre) {
		this.setTaillePolice(10);
		int x = getPosX();
		int y = getPosY() + this.getTaillePoliceTitre();

		g.setFont(new Font("Times new Roman", Font.PLAIN, this.getTaillePoliceTitre()));
	}
	/**	
	 * Redefinition de la methode paintComponent qui dessine les methodes drawTitre et drawMises
	 * @param g une instance de Graphics
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);int x = getPosX();

		this.jetons = String.valueOf(this.player.getJeton());
		this.mise = String.valueOf(this.player.getMise());

		int y = getPosY() + this.getTaillePoliceTitre()+this.getHauteur()/3;
		this.setTaillePolice(30);
		g.setFont(new Font("Times new Roman", Font.PLAIN, taillePolice));
		g.drawString("Mise:"+this.mise, x, y + 3);
		g.drawString("Jetons Dispo:"+this.jetons, x, y+2*this.taillePolice + 3);
		
		String nom = ""+this.player;
		g.drawString(nom, x, y+3*this.taillePolice + 3);
	}

	/**
	 * Methode qui met a jour la vue
	 * @param o une instance de Observable
	 * @param arg une instance de Object
	 */
	@Override
	public void update(Observable o, Object arg){
		//this.validate();
		this.repaint();

	}
}
