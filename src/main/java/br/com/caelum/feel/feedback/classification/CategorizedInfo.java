package br.com.caelum.feel.feedback.classification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;

@Entity
public class CategorizedInfo {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@NotNull
	private CategoryInfo categoryInfo;
	@ManyToOne
	@NotNull
	private FeedbackAnswer feedbackAnswer;
	
	/**
	 * @deprecated
	 */
	public CategorizedInfo() {

	}
	
	public CategorizedInfo(CategoryInfo categoryInfo, FeedbackAnswer feedbackAnswer) {
		this.categoryInfo = categoryInfo;
		this.feedbackAnswer = feedbackAnswer;
	}

	public String getName() {
		return categoryInfo.getName();
	}
	
	
	
}
