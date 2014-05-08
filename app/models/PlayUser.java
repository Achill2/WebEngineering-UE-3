package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.jpa.JPA;
import at.ac.tuwien.big.we14.lab2.api.User;

@Entity
public class PlayUser implements User {

	@Id
	@Constraints.Required
	@Constraints.MinLength(4)
	@Constraints.MaxLength(8)
	public String userName;

	@Constraints.Required
	@Constraints.MinLength(4)
	@Constraints.MaxLength(8)
	public String password;

	public String lastName;
	public String firstName;

	@Formats.DateTime(pattern = "dd.mm.yyyy")
	public Date birthDay;
	//public String gender;

	/**
	 * Finds user from the database relative to its username
	 * 
	 * @param uName
	 *            username of the user to search
	 * @return PlayUser if found, null otherwise
	 */
	public static PlayUser findByUserName(String uName) {

		return JPA.em().find(PlayUser.class, uName);
	}
	
	public static User authenticate(String userName, String password){
		
		PlayUser tmp = JPA.em().find(PlayUser.class, userName);
		
		if(tmp != null)
			if(tmp.password.equals(password))
				return tmp;
		
		return null;
	}

	/**
	 * Saves User to the database
	 */
	public void save() {
		
		JPA.em().persist(this);
	}

	@Override
	public String getName() {
		return userName;
	}

	@Override
	public void setName(String userName) {
		this.userName = userName;
	}
}
