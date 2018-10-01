package br.com.caelum.feel.feedback.classification;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.caelum.feel.feedback.questions.domain.respositories.FeedbackAnswerRepository;
import br.com.caelum.feel.feedback.reports.application.forms.SearchRawAnswersForm;

public class NewCategoryCommentForm {

	@NotNull
	private Integer answerId;
	@NotBlank
	private String categoryName;
	@NotNull
	private Integer cycleId;
	private Integer maximumValue;
	private Long questionId;
	private Long teamId;

	/**
	 * @deprecated
	 */
	public NewCategoryCommentForm() {

	}

	public NewCategoryCommentForm(SearchRawAnswersForm form) {
		this.cycleId = form.getCycleId();
		this.maximumValue = form.getMaximumValue();
		this.questionId = form.getQuestionId();
		this.teamId = form.getTeamId();
	}

	public Integer getMaximumValue() {
		return maximumValue;
	}

	public void setMaximumValue(Integer maximumValue) {
		this.maximumValue = maximumValue;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public SearchRawAnswersForm rebuildSearchForm() {
		SearchRawAnswersForm form = new SearchRawAnswersForm(this.cycleId);
		form.setMaximumValue(this.maximumValue);
		form.setQuestionId(this.questionId);
		form.setTeamId(this.teamId);
		return form;
	}

	public String serializeSearchParms() {		
		return "cycleId="+this.cycleId+"&teamId="+valueOrEmpty(this.teamId)+"&questionId="+valueOrEmpty(this.questionId)+"&maximumValue="+valueOrEmpty(this.maximumValue);
	}

	private String valueOrEmpty(Object value) {
		return Optional.ofNullable(value).map(Object::toString).orElse("");
	}

	public CategoryInfo build() {
		return new CategoryInfo(this.categoryName);
	}

}
