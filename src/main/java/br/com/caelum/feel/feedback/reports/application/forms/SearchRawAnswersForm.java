package br.com.caelum.feel.feedback.reports.application.forms;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.Specification;

import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;

public class SearchRawAnswersForm {

	private Long teamId;
	private Long questionId;
	@NotNull
	private Integer cycleId;

	private Integer maximumValue;
	private boolean showModal;
	
	/**
	 * @deprecated
	 */
	public SearchRawAnswersForm() {

	}
	
	

	public SearchRawAnswersForm(@NotNull Integer cycleId) {
		super();
		this.cycleId = cycleId;
	}



	public Integer getMaximumValue() {
		return maximumValue;
	}

	public void setMaximumValue(Integer maximumValue) {
		this.maximumValue = maximumValue;
	}

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
	
	public boolean hasQuestion() {
		return questionId != null;
	}

	public Specification<FeedbackAnswer> build() {
		return new Specification<FeedbackAnswer>() {

			@Override
			public Predicate toPredicate(Root<FeedbackAnswer> root, CriteriaQuery<?> query,
					CriteriaBuilder builder) {
								
				ArrayList<Predicate> predicates = new ArrayList<>();
				
				predicates.add(builder.equal(root.get("question").get("cycle").get("id"),cycleId));
				
				if(questionId != null) {
					predicates.add(builder.equal(root.get("question").get("id"),questionId));
				}
				
				if (teamId != null) {
					predicates.add(builder.equal(root.get("team").get("id"), teamId));
				}
				if (maximumValue != null) {
					predicates.add(builder.lessThanOrEqualTo(root.get("value"), maximumValue));
				}				
				return builder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}



	public void showModal() {
		this.showModal = true;
	}
	
	public boolean isShowModal() {
		return showModal;
	}

}
