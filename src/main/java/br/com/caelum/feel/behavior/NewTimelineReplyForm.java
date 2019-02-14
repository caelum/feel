package br.com.caelum.feel.behavior;

import javax.validation.constraints.NotBlank;

public class NewTimelineReplyForm {

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

	public BehaviorReply toBehaviorReply(BehaviorFeedback root) {
		return new BehaviorReply(comment, name, root);
	}	
}
