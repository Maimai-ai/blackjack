/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;
import java.util.Random;

import blackjack.vue.*;
import blackjack.modele.*;
import cartes.modele.*;


/**
 * Classe Main, la classe executable du Blackjack
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		Paquet paquet = new Paquet();
		paquet.initialiser();
		paquet.melangeCarte();

		Paquet paquetPlayer = new Paquet(); 
		Paquet paquetRandomA = new Paquet();
		Paquet paquetRandomB = new Paquet();
		Paquet paquetRandomC = new Paquet();
		Paquet paquetCroupier = new Paquet();

		GamePlayer player = new Player(paquetPlayer);
		GamePlayer playerRandomA= new RandomPlayer(new Random(),paquetRandomA);
		GamePlayer playerCroupier= new Croupier(new Random(),paquetCroupier);
		GamePlayer playerRandomB= new RandomPlayer(new Random(),paquetRandomB);
		GamePlayer playerRandomC= new RandomPlayer(new Random(),paquetRandomC);

		Blackjack jeu = new Blackjack(player,paquet);
		Blackjack jeuA = new Blackjack(playerRandomA,paquet);
		Blackjack jeuB = new Blackjack(playerRandomB,paquet);
		Blackjack jeuC = new Blackjack(playerRandomC,paquet);
		Blackjack jeuCroupier = new Blackjack(playerCroupier,paquet);

		//Pour reprendre le jeu avec ses arguments
		player.setBlackjack(jeu);
		playerRandomA.setBlackjack(jeuA);
		playerCroupier.setBlackjack(jeuCroupier);
		playerRandomB.setBlackjack(jeuB);
		playerRandomC.setBlackjack(jeuC);

		Partie partie = new Partie(paquet, jeu,jeuA,jeuB,jeuC,jeuCroupier);

		BlackjackGui frame = new BlackjackGui(partie);
	}

}
