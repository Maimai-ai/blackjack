package blackjack.vue;
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.*;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import cartes.modele.Paquet;
import cartes.vue.VuePaquet;
import cartes.vue.VueCarteJoueur;
import blackjack.modele.*;
/**
 * Methode qui permet de creer une fenetre, elle herite de JFrame et implemente Observer ainsi que ActionListener
 * Cette classe fait partie de la Vue mais elle est aussi le controleur
 */
public class BlackjackGui extends JFrame implements Observer, ActionListener{
	//Creation de differentes VueMise
	public VueMises vueMise;
	public VueMises vueMiseA;
	public VueMises vueMiseB;
	public VueMises vueMiseC;

	//Creation des Paquets des differents joueurs ainsi que de la pioche
	public Paquet paquet;
	public Paquet paquetPlayer;
	public Paquet paquetRandomA;
	public Paquet paquetRandomB;
	public Paquet paquetRandomC;
	public Paquet paquetCroupier;

	//Creation des instances de Blackjack pour les differents joueurs
	public Blackjack jeu;
	public Blackjack jeuRandomA;
	public Blackjack jeuRandomB;
	private Blackjack jeuRandomC;
	private Blackjack jeuCroupier;

	//Creation des differents joueurs
	public GamePlayer player;
	public GamePlayer playerRandomA;
	public GamePlayer playerRandomB;
	public GamePlayer playerRandomC;
	public GamePlayer playerCroupier;

	//Les differentes vues
	public VuePaquet pioche;
	public VueCarteJoueur carteMain;
	public VueRandom carteRandomA;
	public VueRandom carteRandomB;
	public VueRandom carteRandomC;
	public VueCroupier carteCroupier;

	//Les boutons
	private JButton play;
	private JButton doublerMise;
	private JButton stop;
	private JButton exit;
	private JButton replay;
	private JButton b1;
	private JButton b2;
	private JButton b5;
	private JButton b10;
	private JButton b25;
	private JButton b50;
	private JButton valider;

	private JPanel panel;
	JPanel lesBoutonsJeu;
	JPanel lesBoutonsMise;

	static int pos=0;
	static boolean bloq=false;
	Partie partie;
	ArrayList<GamePlayer> listGamePlayer;
	GamePlayer currentPlayer;
	/**
	 * Constructeur de BlackjackGui qui permet l'affichage de notre fenetre avec tous ses paramettres, notamment sa taille et autre
	 * @param partie une partie de blackjack
	 */
	public BlackjackGui(Partie partie) {
		this.setTitle("Partie de Blackjack");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int ecran_y = (int) screenSize.getHeight();
		this.setResizable(false);
		this.setFocusable(true);
		int ecran_x = (int)screenSize.getWidth();
		this.setSize(ecran_x,ecran_y);
		this.setLocationRelativeTo(null); //centre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.partie = partie;

		this.paquet = this.partie.getPaquet();
		this.pioche = new VuePaquet(this.paquet, "Pioche");

		this.player = this.partie.getPlayer();
		this.playerRandomA= this.partie.getRandomA();
		this.playerCroupier= this.partie.getCroupier();
		this.playerRandomB= this.partie.getRandomB();
		this.playerRandomC= this.partie.getRandomC();

		this.paquetPlayer = this.player.getPaquet(); 
		this.paquetRandomA = this.playerRandomA.getPaquet();
		this.paquetRandomB = this.playerRandomB.getPaquet();
		this.paquetRandomC  = this.playerRandomC.getPaquet();
		this.paquetCroupier = this.playerCroupier.getPaquet();

		this.listGamePlayer = new ArrayList<GamePlayer>();

		//les vues des Mises
		this.vueMise = new VueMises(this.player);
		this.vueMiseA = new VueMises(this.playerRandomA);
		this.vueMiseB = new VueMises(this.playerRandomB);
		this.vueMiseC = new VueMises(this.playerRandomC);

		this.carteMain = new VueCarteJoueur(this.paquetPlayer,"Vos cartes");
		this.carteRandomA = new VueRandom(this.paquetRandomA,this.playerRandomA, ""+this.playerRandomA);
		this.carteCroupier = new VueCroupier(this.paquetCroupier,this.playerCroupier, "Croupier");
		this.carteRandomB = new VueRandom(this.paquetRandomB, this.playerRandomB,""+this.playerRandomB);
		this.carteRandomC = new VueRandom(this.paquetRandomC, this.playerRandomC,""+this.playerRandomC);

		this.jeu = new Blackjack(this.player,this.paquet);
		this.jeuRandomA = new Blackjack(this.playerRandomA,this.paquet);
		this.jeuRandomB = new Blackjack(this.playerRandomB,this.paquet);
		this.jeuRandomC = new Blackjack(this.playerRandomC,this.paquet);
		this.jeuCroupier = new Blackjack(this.playerCroupier,this.paquet);

		//Pour reprendre le jeu avec ses arguments
		this.player.setBlackjack(this.jeu);
		this.playerRandomA.setBlackjack(this.jeuRandomA);
		this.playerRandomB.setBlackjack(this.jeuRandomB);
		this.playerRandomC.setBlackjack(this.jeuRandomC);
		this.playerCroupier.setBlackjack(this.jeuCroupier);

		this.listGamePlayer.add(this.playerRandomC);
		this.listGamePlayer.add(this.playerRandomB);
		this.listGamePlayer.add(this.playerRandomA);
		this.listGamePlayer.add(this.player);
		this.currentPlayer=this.listGamePlayer.get(pos);
		// this.partie = new Partie(this.paquet, this.jeuA,this.jeuB,this.jeuD,this.jeuE,this.jeuC);

		this.player.addObserver(this.vueMise);
		this.playerRandomA.addObserver(this.vueMiseA);
		this.playerRandomB.addObserver(this.vueMiseB);
		this.playerRandomC.addObserver(this.vueMiseC);
		this.paquet.addObserver(this);
		this.paquetPlayer.addObserver(this);
		this.paquetRandomA.addObserver(this);
		this.paquetRandomB.addObserver(this);
		this.paquetRandomC.addObserver(this);
		this.paquetCroupier.addObserver(this);

		//LES BOUTONS
		this.play=new JButton("Continue");
		this.stop=new JButton("Stand");
		this.doublerMise=new JButton("Mise x2");
		this.exit=new JButton("EXIT");
		this.replay=new JButton("REJOUER");
		this.doublerMise.addActionListener(this);
		this.stop.addActionListener(this);
		this.play.addActionListener(this);
		this.exit.addActionListener(this);
		this.replay.addActionListener(this);

		JPanel vert = new JPanel(new GridLayout(1,3));
		JPanel buttonDivision = new JPanel(new GridLayout(2,1));
		this.lesBoutonsJeu = new JPanel();
		this.lesBoutonsJeu.add(this.doublerMise);
		this.lesBoutonsJeu.add(this.play);
		this.lesBoutonsJeu.add(this.stop, BorderLayout.CENTER);
		this.lesBoutonsJeu.add(this.exit);
		this.lesBoutonsJeu.add(this.replay);
		this.lesBoutonsJeu.setVisible(false);
		this.lesBoutonsMise = new JPanel();
		String chemin = "src/images/";
		ImageIcon un = new ImageIcon(chemin+ "1.png");
		ImageIcon deux = new ImageIcon(chemin+"2.png");
		ImageIcon cinq = new ImageIcon(chemin+"5.png");
		ImageIcon dix = new ImageIcon(chemin+"10.png");
		ImageIcon vingthcinq = new ImageIcon(chemin+"25.png");
		ImageIcon cinquante = new ImageIcon(chemin+"50.png");

		this.b1 = new JButton(un);
		this.b2 = new JButton(deux);
		this.b5 = new JButton(cinq);
		this.b10 = new JButton(dix);
		this.b25 = new JButton(vingthcinq);
		this.b50 = new JButton(cinquante);
		this.valider = new JButton("VALIDER");

		this.b1.addActionListener(this);
		this.b2.addActionListener(this);
		this.b5.addActionListener(this);
		this.b10.addActionListener(this);
		this.b25.addActionListener(this);
		this.b50.addActionListener(this);
		this.valider.addActionListener(this);
		lesBoutonsMise.add(this.b1);
		lesBoutonsMise.add(this.b2);
		lesBoutonsMise.add(this.b5);
		lesBoutonsMise.add(this.b10);
		lesBoutonsMise.add(this.b25);
		lesBoutonsMise.add(this.b50);
		lesBoutonsMise.add(this.valider);
		buttonDivision.add(lesBoutonsJeu);
		buttonDivision.add(lesBoutonsMise);

		vert.add(this.pioche);
		vert.add(this.carteCroupier);
		vert.add(buttonDivision);
		JPanel bleu = new JPanel(new GridLayout(1,4));
		bleu.add(this.vueMise);
		bleu.add(this.vueMiseA);
		bleu.add(this.vueMiseB);
		bleu.add(this.vueMiseC);
		JPanel rouge = new JPanel(new GridLayout(1,4));
		rouge.add(this.carteMain);
		rouge.add(this.carteRandomA);
		rouge.add(this.carteRandomB);
		rouge.add(this.carteRandomC);
		GridLayout content = new GridLayout(3,1);
		JPanel conteneur = new JPanel();
		conteneur.setLayout(content);
		conteneur.add(vert);
		conteneur.add(bleu);
		conteneur.add(rouge);
		System.out.println(this.paquet.getNbCartes());
		this.setContentPane(conteneur);
		this.setVisible(true);
	}
	/**
	 * Methode qui permet de choisir aleatoirement si un joueur Random veut doubler ou non sa mise
	 * @param player une instance de GamePlayer
	 */
	public void RandomDoubleMise(GamePlayer player) {
		Random randomGenerator = new Random();
		int position = randomGenerator.nextInt(2);
		if(position==0) {
			player.doublerMise();
		}
	}
	/**
	 * Methode qui permet de faire jouer un joueur Random
	 * @param player une instance de GamePlayer
	 */
	public void RandomPlay(GamePlayer player) {
		Blackjack blackjack = player.getBlackjack();
		if(player.continuer(blackjack)) {
			blackjack.ajoutCarte();
		}
		else{
			blackjack.finPartie(true);
			pos++;
			this.currentPlayer=this.listGamePlayer.get(pos);
		}
	}

	/**
	 * Methode qui permet de faire jouer un joueur
	 * @param player une instance de GamePlayer
	 */
	public void tour(GamePlayer player) {
		if(player instanceof RandomPlayer){
			if(player.getPaquet().getNbCartes()==2) {
				RandomDoubleMise(player);
			}
			RandomPlay(player);

		}
	}

	public void tourCroupier(GamePlayer player) {
		Blackjack blackjack = player.getBlackjack();
		if(blackjack.getTotal()>17) {
			blackjack.finPartie(true);
			this.bloq=true;
			this.pos+=10;
			String str = "Croupier a : " +player.getBlackjack().getTotal();
			this.partie.remiseJeton();
			str += "Gain : "+this.player.gainPerte();
			System.out.println(str);
			Partie game = this.partie.newGame(this.player, this.playerRandomA, this.playerRandomB, this.playerRandomC, this.playerCroupier);
			this.pos = 0;
			BlackjackGui bj = new BlackjackGui(game);
			this.dispose();
		}
		else{
			blackjack.ajoutCarte();
		}
	}

	/**
	 * Methode qui fait un action lors du clic sur un des boutons
	 * @param e une instance de ActionEvent
	 */
	public void actionPerformed(ActionEvent e){
		Object appuiesur = e.getSource();
		if (appuiesur == this.doublerMise && this.jeu.getPaquet().getNbCartes()==2){
			this.player.doublerMise();
			this.doublerMise.setEnabled(false);
		}
		else if (appuiesur == this.play){
			if(!this.jeu.plusDe21()) {
				this.jeu.ajoutCarte();
				this.doublerMise.setEnabled(false);
			}
			else {
				this.play.setEnabled(false);
			}
		}
		else if (appuiesur == this.stop){
			this.doublerMise.setEnabled(false);
			this.play.setEnabled(false);
			this.stop.setEnabled(false);
			this.jeu.finPartie(true);

			this.currentPlayer=this.playerCroupier;
			tourCroupier(this.currentPlayer);
		}
		else if (appuiesur == this.exit){
			this.dispose();
			System.exit(0);
		}
		else if (appuiesur == this.replay){
			this.partie.remiseJeton();
			/*afficheGagnant.setSize(600,600);*/
			Partie game = this.partie.newGame(this.player, this.playerRandomA, this.playerRandomB, this.playerRandomC, this.playerCroupier);
			this.pos = 0;
			this.bloq=false;
			BlackjackGui bj = new BlackjackGui(game);
			this.dispose();

		}
		else if (appuiesur == this.b1 && this.player.getJeton()-1>=0){
			this.player.choixMise(1);
		}
		else if (appuiesur == this.b2 && this.player.getJeton()-2>=0){
			this.player.choixMise(2);
		}
		else if (appuiesur == this.b5 && this.player.getJeton()-5>=0){
			this.player.choixMise(5);
		}
		else if (appuiesur == this.b10 && this.player.getJeton()-10>=0){
			this.player.choixMise(10);
		}
		else if (appuiesur == this.b25 && this.player.getJeton()-25>=0){
			this.player.choixMise(25);
		}
		else if (appuiesur == this.b50 && this.player.getJeton()-50>=0){
			this.player.choixMise(50);
		}
		else if (appuiesur == this.valider){
			this.lesBoutonsJeu.setVisible(true);
			//this.replay.setEnabled(true);
			this.exit.setEnabled(true);
			this.lesBoutonsMise.setVisible(false);
			for(int i =0; i < this.listGamePlayer.size();i++){
				if(this.listGamePlayer.get(i) instanceof RandomPlayer){
					Random randomGenerator = new Random();
					int n = randomGenerator.nextInt(5);
					this.listGamePlayer.get(i).choixMise(n+1);
				}
			}

			//distribution de 2 cartes a chaque joueur
			this.partie.distribution();
			tour(this.currentPlayer);
		}
	}
	/**
	 * Redefinition de la methode update qui permet de mettre a jour les differentes vues
	 * @param arg0 une instance de Observable
	 * @param arg1 une instance de Object
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if(pos < this.listGamePlayer.size() || !this.bloq){
			if(this.currentPlayer instanceof RandomPlayer){
				tour(this.currentPlayer);
			}
		}
		//if(this.debloq){
			if(this.currentPlayer instanceof Croupier){
				if(this.bloq){
					String str = "UPDATE Croupier a : " +player.getBlackjack().getTotal();
					this.partie.remiseJeton();
					str += "Gain : "+this.player.gainPerte();
					System.out.println(str);
					Partie game = this.partie.newGame(this.player, this.playerRandomA, this.playerRandomB, this.playerRandomC, this.playerCroupier);
					this.pos = 0;
					this.bloq=false;
					BlackjackGui bj = new BlackjackGui(game);
					this.dispose();
				}
				else{
					tourCroupier(this.currentPlayer);
				}
			}

		//}
		//Repaint toutes les Vues
		this.pioche.repaint();
		this.carteMain.repaint();
		this.carteRandomA.repaint();
		this.carteRandomB.repaint();
		this.carteRandomC.repaint();
		this.carteCroupier.repaint();
		this.vueMise.repaint();
		this.vueMiseA.repaint();
		this.vueMiseB.repaint();
		this.vueMiseC.repaint();
	}
}
