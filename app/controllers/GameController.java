package controllers;

import java.util.ArrayList;
import java.util.List;

import models.AnswerData;
import akka.util.Collections;
import at.ac.tuwien.big.we14.lab2.api.*;
import at.ac.tuwien.big.we14.lab2.api.impl.*;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.index;
import views.html.quiz;
import views.html.quizover;
import views.html.roundover;
import play.api.i18n.*;

@Security.Authenticated(Secured.class)
public class GameController extends Controller {
	
	
	private static QuizGame game; // TODO sinnvoll hier?
	
	public static Result index(){
		
		return ok(index.render(request().username()));
	}
	
	/**
	 * shows the current question or starts a new game
	 * @return
	 */
	public static Result showQuestion() {
		Logger.info("request username: " + request().username());


		// check if a game is running
		if (game == null || game.isGameOver()) {
			// if not generate new Game
			User user = new SimpleUser(); 		// TODO besser einbinden - ist jetzt einfach an dieser Stelle zum Testen 
			user.setName(request().username());
			
			// generate new game TODO path angabe ausbessern?!
//			Logger.info("sprache " + Messages.get("start_game"));
			
			String browser_language = play.api.i18n.Lang.defaultLang().language().toString();
			String json_path = "conf/data.en.json";
			if (browser_language.equals("de")) {
				json_path = "conf/data.de.json";
			}
			
			
			QuizFactory factory = new PlayQuizFactory(json_path, user);
			game = factory.createQuizGame();
			// start first round
			game.startNewRound();
		}
		
		// if the current Round is over start a new one
		if (game.isRoundOver()) {
			game.startNewRound();
		}
		Round round = game.getCurrentRound();
		
		return ok(quiz.render(round, game.getPlayers().get(0), game.getPlayers().get(1)));
	}
	
	
	/**
	 * if the round is not finished the next question is shown
	 * When the round is over the roundover-view is shown, when there is 
	 * no round left, the game ends
	 * @return
	 */
	public static Result answerQuestion() {

		Form<AnswerData> answerDataForm = Form.form(AnswerData.class).bindFromRequest();
		AnswerData answerData = answerDataForm.get();
		User player1 = game.getPlayers().get(0);
		

		// the answer
		List<Choice> selected_choices = new ArrayList<Choice>();
		// the question
		Question question = game.getCurrentRound().getCurrentQuestion(player1);
		// all choices for this question
		List<Choice> all_choices = question.getAllChoices();
		
		
		if (answerData.choices_ids == null) {
			// no choices selected
		} else {
			// iterates over all choices; compares the IDs of these choices
			// with the IDs in the answer-list. If the answer contains the id the 
			// specific choice is added to the selected_choices-list
			for (Choice c : all_choices) {
			       if (answerData.choices_ids.contains(c.getId() + "/")) { // TODO warum ist hinte ein /?!
			    	   selected_choices.add(c);
			       }
			}
		}
		
		Logger.info("time: " + answerData.time + ", selected: " + selected_choices.size());
		
		game.answerCurrentQuestion(player1, selected_choices, question.getMaxTime()-answerData.time);
		
		// if round is over show the round over view
		// if not then show the next question
		if (game.isRoundOver()) {
			if (game.isGameOver()) {
				return ok(quizover.render(game));
			} else {
				return ok(roundover.render(game));
			}
			
		} else {
			return ok(quiz.render(game.getCurrentRound(), game.getPlayers().get(0), game.getPlayers().get(1)));
		}
			
	}
	
	
}
