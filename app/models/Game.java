package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {

	public static final int NUMBEROFROUNDS = 5;
	public static final int NUMBEROFQUESTIONSPERROUND = 3;

	
	private Player player1;
	private Player player2;
	
	private List<Round> rounds;

	private Random randomGenerator;

	
	
	/**
	 * basic constructor 
	 */
	public Game() {
		// TODO remove - used for testing
		player1 = new Player("player1");
		player2 = new Player("player2");
		
		rounds = new ArrayList<Round>();

		randomGenerator = new Random();
		
		
		
	}
	
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	/**
	 * @return the current round
	 */
	public Round getCurrentRound() {
		return rounds.get(getNumberOfCurrentRound() - 1);
	}

	/**
	 * 
	 * @return number of current Round
	 */
	public int getNumberOfCurrentRound() {
		return rounds.size();
	}
	
	
}
