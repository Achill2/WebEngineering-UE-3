package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

//@Entity
public class Category {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	private List<Question> questions;

	public Category() {
		name = "";
		questions = new ArrayList<>();
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public Category(String name, List<Question> questions) {
		super();
		this.name = name;
		this.questions = questions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void addQuestion(Question question) {
		if (!questions.contains(question)) {
			questions.add(question);
			if (question.getCategory() != this) {
				question.setCategory(this);
			}
		}
	}

	public void removeQuestion(Question question) {
		if (questions.contains(question)) {
			questions.remove(question);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((questions == null) ? 0 : questions.hashCode());
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
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", questions=" + questions
				+ "]";
	}


}
