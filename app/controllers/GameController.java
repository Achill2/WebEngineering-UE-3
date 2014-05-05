package controllers;

import models.Category;
import models.Player;
import models.Question;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.quiz;

public class GameController extends Controller {
	
	
	public static Result index() {
		
		// generate new Game
		
		
		
		
		return ok(quiz.render(new Player("P1"), new Player("P2")));
	}
	
}
