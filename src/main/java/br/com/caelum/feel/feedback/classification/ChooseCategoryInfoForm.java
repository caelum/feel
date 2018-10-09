package br.com.caelum.feel.feedback.classification;

import javax.validation.constraints.NotNull;

import br.com.caelum.feel.feedback.reports.application.forms.SearchRawAnswersForm;

public class ChooseCategoryInfoForm {

	private SearchRawAnswersForm searchForm = new SearchRawAnswersForm();
	@NotNull
	private Integer categoryInfoId;
	private Integer answerId;

	/**
	 * @deprecated
	 */
	public ChooseCategoryInfoForm() {

	}
	
	

	public Integer getAnswerId() {
		return answerId;
	}



	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}



	public ChooseCategoryInfoForm(SearchRawAnswersForm form) {
		this.searchForm = form;
	}

	public SearchRawAnswersForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(SearchRawAnswersForm searchForm) {
		this.searchForm = searchForm;
	}

	public Integer getCategoryInfoId() {
		return categoryInfoId;
	}

	public void setCategoryInfoId(Integer categoryInfoId) {
		this.categoryInfoId = categoryInfoId;
	}

}
