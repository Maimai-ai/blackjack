package blackjack.vue;
import java.awt.event.*;
import javax.swing.*;

import blackjack.Main;

import java.awt.*;
/**
	* Win est un JDialog qui implemente l'interface ActionListener et Runnable
	* Elle permet d'afficher une fenetre de dialogue
*/
public class WinOver extends JDialog implements ActionListener {
	private JButton exit;
	//private JButton rejouer;
	public String image;
	public JFrame parent;
	
	/**
		* Second contructeur de la classe, elle affiche les boutons et image de la classe
		* @param parent une JFrame
		* @param titre une String
		* @param message une String
		* @param image une String
	*/
	public WinOver(JFrame parent, String titre, String message, String image) {
		super(parent, titre);
		
		this.parent = parent;
		this.setLocation(this.parent.getWidth()/4,0);
		this.setUndecorated(true);
        JPanel placementDuMessage = new JPanel();
        placementDuMessage.add(new JLabel(message));
        placementDuMessage.setOpaque(true);
        
        JPanel contour = new JPanel();
        
        contour.setLayout(new BorderLayout());
        contour.setOpaque(false);
        contour.add(placementDuMessage, BorderLayout.NORTH);
        
        JPanel placementBoutons = new JPanel();
        placementBoutons.setOpaque(false);
        this.exit = new JButton("Quitter");
        
        placementBoutons.add(this.exit);
        this.exit.setBackground(Color.BLACK);
        this.exit.setForeground(new Color(255,53,53));
        this.exit.addActionListener(this);
        
        contour.add(placementBoutons, BorderLayout.PAGE_END); // en bas
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //ferme le dialogue
        
        JPanel fond = new JPanel(){
			String chemin = image;
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				ImageIcon m = new ImageIcon(chemin);
				Image im = m.getImage();
				g.drawImage(im,100,100,400,400,this);

			} 
		};
		
		fond.setLayout(new FlowLayout());
		fond.add(contour);
        
        this.setContentPane(fond);
        
        this.pack();
        this.setVisible(true);
	}
	
    
    /**
		* Methode qui fait l'action lors du clic sur un bouton comme exit ou reset
		* @param e 
		* 		une instance de ActionEvent
	*/
	public void actionPerformed(ActionEvent e){
		Object appuiesur = e.getSource();
		if (appuiesur == this.exit){
			this.parent.dispose();
		}/*
		if(appuiesur == this.rejouer){
			this.parent.dispose();
			BlackjackGui frame = new BlackjackGui();
			
		}*/
	}
}
