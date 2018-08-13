package br.com.caelum.feel.behavior;

import javax.validation.constraints.NotBlank;

public class NewBehaviorFeedbackForm {

	private String name;
	@NotBlank
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
