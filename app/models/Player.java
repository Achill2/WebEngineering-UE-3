package models;

import javax.persistence.*;
import play.data.validation.Constraints;
import javax.persistence.GenerationType;

/**
 * Player
 * @author Fabian Filip
 * */
//@Entity
public class Player {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
//	@Constraints.Required
	// TODO length?!
	private String name;
	
	private int wonRounds;
	
	public Player(String name) {
		this.name = name;
		wonRounds = 0;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfWonRounds() {
		return wonRounds;
	}

	public void setNumberOfWonRounds(int number) {
		this.wonRounds = number;
		
	}

	public void incrementNumberOfWonRounds() {
		wonRounds++;
		
	}


}
