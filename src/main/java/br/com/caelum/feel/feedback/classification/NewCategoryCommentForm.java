package br.com.caelum.feel.feedback.classification;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewCategoryCommentForm {

	@NotNull
	private Integer answerId;
	@NotBlank
	private String categoryName;
	@NotNull
	private Integer cycleId;

	/**
	 * @deprecated
	 */
	public NewCategoryCommentForm() {

	}

	public NewCategoryCommentForm(@NotNull Integer cycleId) {
		super();
		this.cycleId = cycleId;
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

}
