package br.com.caelum.feel.feedback.reports.application.forms;

import javax.validation.constraints.NotNull;

public class SearchValuesPerTeamForm {

	@NotNull
	private Integer cycleId;
	@NotNull
	private Long questionId;

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

}
