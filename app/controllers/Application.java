package controllers;

import models.PlayUser;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.authentication;
import views.html.registration;

public class Application extends Controller {

	public static class Login {

		public String userName;
		public String password;

		public String validate() {

			if (PlayUser.authenticate(userName, password) == null)
				return "Invalid user or password";

			return null;
		}
	}

	public static Result login() {

		return ok(authentication.render(form(Login.class)));
	}
	
	@Transactional
	public static Result authenticate(){
		
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		
		if(loginForm.hasErrors()) {
			return badRequest(authentication.render(loginForm));
		}else{
			session("userName", loginForm.get().userName);
			return redirect(routes.GameController.index());
		}
	}
	
	public static Result goToRegistration() {
		
		Form<PlayUser> userForm = form(PlayUser.class);
		return ok(registration.render(userForm));
	}
	
	@Transactional
	public static Result register() {

		Form<PlayUser> userForm = form(PlayUser.class).bindFromRequest();
		if (userForm.hasErrors())
			return badRequest(registration.render(userForm));
		
		if(PlayUser.findByUserName(userForm.get().userName) != null)
			return badRequest(registration.render(userForm));
		
		userForm.get().save();
		
		return ok(authentication.render(form(Login.class)));
	}
	
	public static Result logout(){
		
		session().clear();
		return redirect(routes.Application.login());
	}
}
