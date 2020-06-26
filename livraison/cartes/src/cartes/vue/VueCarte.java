package cartes.vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import cartes.modele.Carte;
import cartes.modele.Paquet;

/**
 * VueCarte est une classe abstraite des cartes du joueur, il herite de JPanel et implement Observer
 */
public abstract class VueCarte extends JPanel implements Observer{
	public Paquet paquet;
	public ArrayList<Carte> listeCarte;
	private int taillePolice;
	private String titre;
	private int taillePaquet;
	private String tete = "";
	private String figure = "";

	/**	
	 * Constructeur de VueCarte qui prend le paquet du joueur
	 * @param paquet une instance de Paquet
	 * @param titre unn String titre
	 */
	public VueCarte(Paquet paquet, String titre){
		this.paquet = paquet;
		this.listeCarte = paquet.getList();
		this.titre=titre;
	}

	/**
	 * Accesseur au paquet
	 * @return un Paquet
	 */
	public Paquet getPaquet(){
		return this.paquet;
	}


	/**
	 * Methode qui permet d'avoir une position en fonction de la largeur de l'ecran
	 * @return un entier
	 */
	public int getPosX() {
		int posX = this.getWidth()/50;
		return posX;
	}

	/**
	 * Methode qui permet d'avoir une position en fonction de la hauteur de l'ecran
	 * @return un entier
	 */
	public int getPosY() {
		int posY = this.getHeight()/50;
		return posY;
	}

	/**
	 * Methode qui permet de connaitre la taille de la pioche
	 * @return un entier
	 */
	public int getEpaisseur() {
		return this.listeCarte.size();
	}

	/**
	 * Methode qui permet d'avoir une liste de Carte dans le Paquet
	 * @return une liste de Carte
	 */
	public ArrayList<Carte> getListeDesCartes() {
		this.listeCarte=this.paquet.getList();
		return this.listeCarte;
	}

	/**
	 * Methode qui permet de modifier la taille de la police
	 * @param calcul un entier
	 */
	public void setTaillePolice(int calcul) {
		this.taillePolice = this.getWidth()/calcul;
	}

	/**
	 * Accesseur qui permet de recuperer la taille de la police
	 * @return un entier
	 */
	public int getTaillePolice() {
		return this.taillePolice;
	}

	/**
	 * Methode qui permet d'ecrire un titre
	 * @param g une instance de Graphics
	 * @param titre une String
	 */
	public void drawTitre(Graphics g, String titre) {
		setTaillePolice(40);
		int x = getPosX();
		int y = getPosY() + getTaillePolice();

		g.setFont(new Font("Times new Roman", Font.PLAIN, getTaillePolice()));
		g.drawString(titre, x , y);
	}
	/**
	 * Methode qui permet de dessiner la paquet de carte
	 * @param g une instance de Graphics
	 */

	public abstract void drawCartes(Graphics g);

	/**
	 * Redefinition de la methode paintComponent qui dessine les methodes drawTitre et de drawCartes
	 * @param g une instance de Graphics
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawTitre(g, this.titre);
		drawCartes(g);
	}

	public void peindreSymbol(Graphics g, String couleur, String forme, int x1, int y1, int x2, int y2, int largeurCarte, int hauteurCarte, int poX, int poY){
		//Information ecrit sur chacune des cartes sa forme et sa couleur
		if(couleur.equals("pique")||couleur.equals("trefle")) {
			g.setColor(Color.BLACK);
		}
		else {
			g.setColor(Color.RED);
		}


		g.fillRoundRect(poX, poY,largeurCarte,hauteurCarte,5,5);

		g.setColor(Color.YELLOW);
		g.drawRect(poX, poY,largeurCarte,hauteurCarte);

		g.setColor(Color.WHITE);




		if(couleur.equals("trefle")) {
			this.figure = "\u2663";

		}
		else if(couleur.equals("pique")) {
			this.figure = "\u2660";

		}
		else if(couleur.equals("coeur")) {
			this.figure = "\u2665";

		}
		else if(couleur.equals("carreau")) {
			this.figure = "\u2666";

		}

		if(forme.equals("roi")) {
			this.tete = /*"\u265a"+*/" K";
		}
		else if(forme.equals("dame")) {
			this.tete = /*"\u265b"+*/" Q";
		}
		else if(forme.equals("valet")) {
			this.tete =/* "\u265d"+*/" J";
		}
		else if(forme.equals("as")) {
			this.tete =" 1";
		}
		else{
			this.tete = forme;
		}



		g.drawString(this.tete, x1,y1);
		g.drawString(this.figure,x2, y2);

	}

	/**
	 * Methode qui met a jour la vue
	 * @param o une instance de Observable
	 * @param arg une instance de Object
	 */
	@Override
	public void update(Observable o, Object arg){
	}
}
