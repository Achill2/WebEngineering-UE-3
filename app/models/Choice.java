package models;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//@Entity
public class Choice {
	
	// TODO Constraints
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String text;

	private Question question;

	public Choice() {
		text = "";
		question = null;
	}

	public Choice(String text, Question question) {
		super();
		this.text = text;
		this.question = question;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Choice other = (Choice) obj;
		if (id != other.id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""
				+ "Choice [id=" + id + ", text=" + text + ", question="
				+ ((question != null) ? "notnull" : "null") + "]";
	}


}
