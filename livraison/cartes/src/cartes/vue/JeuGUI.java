package cartes.vue;

import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.awt.Dimension;

import cartes.modele.*;
import cartes.controleur.Controleur;

/**
 * JeuGUI est une classe qui permet d'afficher une fenetre contenant une Vue de la Pioche et des cartes du joueur
 * Cette classe herite JFrame et implemente Observer
 */
public class JeuGUI extends JFrame implements Observer{
	public Paquet paquet;
	public Paquet paquetvisible;
	public VueCarte pioche;
	public VueCarte carteMain;

	/**
	 * Constructeur de la classe JeuGUI qui permet l'affichage de la fenetre en faisait ces differentes configurations
	 * C'est dedans que nous ajoutons les différentes Vue des paquets
	 */
	public JeuGUI() {

		this.setTitle("Partie de Carte");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int taillesy = (int) screenSize.getHeight();
		this.setResizable(false);
		this.setFocusable(true);

		int taillesx = (int)screenSize.getWidth();
		this.setSize(taillesx,taillesy);

		this.setLocationRelativeTo(null); //centre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.paquet = new Paquet();
		this.paquet.initialiser();
		this.paquet.melangeCarte();

		this.paquetvisible = new Paquet(); //a changer le nombre de cate quand on aura le fnctionnement du jeu
		this.pioche = new VuePaquet(this.paquet, "Pioche");
		this.carteMain = new VueCarteJoueur(this.paquetvisible, "Vos cartes en main");

		this.paquet.addObserver(this);
		this.paquetvisible.addObserver(this);

		Controleur controle = new Controleur(carteMain,pioche);
		this.pioche.addMouseListener(controle);

		GridLayout grid = new GridLayout(2,1);
		grid.setHgap(2);
		grid.setVgap(2);
		this.getContentPane().add(this.pioche);
		this.getContentPane().add(this.carteMain); 
		this.setLayout(grid);
		this.setVisible(true);
	}

	/**
	 * Methode qui met a jour la fenetre en fonction de la vue de la pioche et de la vue des cartes du joueur
	 * @param arg0 une instance de Observable
	 * @param arg1 une instance de Object
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		this.pioche.repaint();
		this.carteMain.repaint();
		this.invalidate();
		this.validate();
	}

}
