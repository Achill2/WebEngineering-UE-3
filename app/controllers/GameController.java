package controllers;

import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.impl.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.quiz;

public class GameController extends Controller {
	
	
	private static QuizGame game; // TODO sinnvoll hier?
	/**
	 * start a new game 
	 * @return
	 */
	public static Result startGame() {
		
		// generate new Game
		// TODO besser einbinden - ist jetzt einfach an dieser Stelle zum Testen ----------
		User user = new SimpleUser();
		user.setName("Test1");
		QuizFactory factory = new PlayQuizFactory("conf/data.de.json", user);
		game = factory.createQuizGame();
		
		game.startNewRound();
		Round round = game.getCurrentRound();
		
		// -----------------------------
		
		return ok(quiz.render(round, game.getPlayers().get(0), game.getPlayers().get(1)));
	}
	
	/**
	 * if the round is not finished the next question is shown
	 * When the round is over the roundover-view is shown, when there is 
	 * no round left, the game ends
	 * @return
	 */
	public static Result answerQuestion() {
		
		// test TODO implement
		
		return ok(quiz.render(game.getCurrentRound(), game.getPlayers().get(0), game.getPlayers().get(1)));
	}
	
}
