package br.com.caelum.feel.behavior;

import java.time.format.DateTimeFormatter;

public class BehaviorFeedbackMessageDTO {

	private Integer id;
	private String date;
	private String from;
	private String comment;

	public BehaviorFeedbackMessageDTO(BehaviorFeedback feedback) {
		this.id = feedback.getId();
		this.date = feedback.getInstant().format(DateTimeFormatter.ofPattern("kk:mm dd/MM/yyyy"));
		this.from = feedback.getName().orElse("Anônimo");
		this.comment = feedback.getComment();
	}

	public Integer getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getFrom() {
		return from;
	}

	public String getComment() {
		return comment;
	}

}
