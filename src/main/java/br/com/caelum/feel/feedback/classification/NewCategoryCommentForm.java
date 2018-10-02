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
	private SearchRawAnswersForm searchForm = new SearchRawAnswersForm();

	/**
	 * @deprecated
	 */
	public NewCategoryCommentForm() {

	}

	public NewCategoryCommentForm(SearchRawAnswersForm form) {
		this.searchForm = form;
	}
	
	public SearchRawAnswersForm getSearchForm() {
		return searchForm;
	}
	
	public void setSearchForm(SearchRawAnswersForm searchForm) {
		this.searchForm = searchForm;
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

	public CategoryInfo build() {
		return new CategoryInfo(this.categoryName);
	}

}
