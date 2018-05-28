package br.com.caelum.feel.feedback.reports.application.forms;

import javax.validation.constraints.NotNull;

public class SearchRawAnswersForm {

	private Long teamId;
	@NotNull
	private Long questionId;
	@NotNull
	private Integer cycleId;

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public boolean hasTeamId() {
		return teamId != null;
	}

}
