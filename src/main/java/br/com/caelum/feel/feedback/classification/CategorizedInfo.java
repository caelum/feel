package br.com.caelum.feel.feedback.classification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;

import br.com.caelum.feel.feedback.cycles.domain.models.Cycle;
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
	@BatchSize(size=20)
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
	
	public Cycle getCycle() {
		return feedbackAnswer.getQuestion().getCycle();
	}

	public FeedbackAnswer getFeedbackAnswer() {
		return feedbackAnswer;
	}
	
	
}
