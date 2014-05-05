package controllers;

import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.impl.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.quiz;

public class GameController extends Controller {
	
	
	public static Result index() {
		
		// generate new Game
		// TODO besser einbinden - ist jetzt einfach an dieser Stelle zum Testen ----------
		User user = new SimpleUser();
		user.setName("Test1");
		QuizFactory factory = new PlayQuizFactory("conf/data.de.json", user);
		QuizGame game = factory.createQuizGame();
		
		game.startNewRound();
		Round round = game.getCurrentRound();
		
		// -----------------------------
		
		return ok(quiz.render(round.getCurrentQuestion(user), game.getPlayers().get(0), game.getPlayers().get(1)));
	}
	
}
