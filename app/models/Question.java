package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

//@Entity
public class Question {

	// TODO Constraints
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String text;

	private long maxTime;

	private List<Choice> wrongChoices;

	private List<Choice> correctChoices;

	private Category category;

	public Question() {
		this.text = "";
		this.maxTime = 60;
		this.wrongChoices = new ArrayList<>();
		this.correctChoices = new ArrayList<>();
		this.category = null;
	}

	public Question(int id, String text, long maxTime, Category category) {
		super();
		this.id = id;
		this.text = text;
		this.maxTime = maxTime;
		this.wrongChoices = new ArrayList<>();
		this.correctChoices = new ArrayList<>();
		this.category = category;
	}

	public Question(int id, String text, long maxTime, List<Choice> wrongChoices,
			List<Choice> correctChoices, Category category) {
		super();
		this.id = id;
		this.text = text;
		this.maxTime = maxTime;
		this.wrongChoices = wrongChoices;
		this.correctChoices = correctChoices;
		this.category = category;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public List<Choice> getAllChoices() {
		List<Choice> allChoices = new ArrayList<>();

		allChoices.addAll(correctChoices);
		allChoices.addAll(wrongChoices);

		Collections.shuffle(allChoices);

		return allChoices;
	}

	public List<Choice> getCorrectChoices() {
		return correctChoices;
	}

	public void addChoice(Choice choice, boolean isCorrect) {
		if (isCorrect) {
			correctChoices.add(choice);
			choice.setQuestion(this);
		} else {
			wrongChoices.add(choice);
			choice.setQuestion(this);
		}
	}

	public void removeChoice(Choice choice) {
		if (correctChoices.contains(choice)) {
			correctChoices.remove(choice);
		}
		if (wrongChoices.contains(choice)) {
			wrongChoices.remove(choice);
		}
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;

		boolean updateCategory = true;

		for (Question question : category.getQuestions()) {
			if (question == this) {
				updateCategory = false;
				break;
			}
		}

		if (updateCategory) {
			category.addQuestion(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((correctChoices == null) ? 0 : correctChoices.hashCode());
		result = prime * result + (int) (maxTime ^ (maxTime >>> 32));
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result
				+ ((wrongChoices == null) ? 0 : wrongChoices.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (correctChoices == null) {
			if (other.correctChoices != null)
				return false;
		} else if (!correctChoices.equals(other.correctChoices))
			return false;
		if (maxTime != other.maxTime)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (wrongChoices == null) {
			if (other.wrongChoices != null)
				return false;
		} else if (!wrongChoices.equals(other.wrongChoices))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [text=" + text + ", maxTime=" + maxTime
				+ ", wrongChoices=" + wrongChoices + ", correctChoices="
				+ correctChoices + ", category=" + ((category != null)? "notnull" : "null") + "]";
	}
	
}
