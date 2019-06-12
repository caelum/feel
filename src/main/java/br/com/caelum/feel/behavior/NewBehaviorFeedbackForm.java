package br.com.caelum.feel.behavior;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewBehaviorFeedbackForm {

	private String name;
	@NotBlank
	private String comment;
	@NotNull
	private BehaviorFeedbackType feedbackType;
	
	

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
	
	public BehaviorFeedbackType getFeedbackType() {
		return feedbackType;
	}
	
	public void setFeedbackType(BehaviorFeedbackType feedbackType) {
		this.feedbackType = feedbackType;
	}

	public BehaviorFeedback toBehaviorFeedback() {
		return new BehaviorFeedback(name,comment,feedbackType);
	}

}
