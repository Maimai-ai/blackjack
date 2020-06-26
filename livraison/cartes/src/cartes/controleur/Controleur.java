package cartes.controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cartes.vue.*;
import cartes.modele.*;

/**
 * Controleur est une classe qui herite de MouseAdapter
 * Elle permet de pouvoir creer une action
 */
public class Controleur extends MouseAdapter{

	public VueCarte paquet;
	public VueCarte joueur;

	/**
	 * Constructeur de la classe Controleur
	 * @param joueur une instance de VueCarteJoueur
	 * @param paquet une instance de VuePaquet
	 */
	public Controleur(VueCarte joueur, VueCarte paquet){
		this.paquet = paquet;
		this.joueur = joueur;

	}

	/**
	 * une methode qui lors du clic sur la pioche, retire sa premiere carte et l ajoute au carte du joueur
	 * @param e 
	 *		une instance de MouseEvent
	 */
	@Override 
	public void mouseClicked(MouseEvent e){
		Carte c = this.paquet.getPaquet().retire();
		if(c!=null) {
			this.joueur.getPaquet().addCardTop(c);
		}
	}
}