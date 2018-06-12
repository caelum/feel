package br.com.caelum.feel.feedback.questions.application.forms;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.caelum.feel.feedback.questions.domain.models.CategoryType;
import br.com.caelum.feel.feedback.questions.domain.models.Question;

public class QuestionsFilterForm {

	private Integer cycleId;
	private CategoryType categoryType;

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	public Specification<Question> build() {
		return new Specification<Question>() {

			@Override
			public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query,
					CriteriaBuilder builder) {
								
				ArrayList<Predicate> predicates = new ArrayList<>();
				predicates.add(builder.isNull(root.get("cycle").get("deletedInstant")));
				
				if (cycleId != null) {
					predicates.add(builder.equal(root.get("cycle").get("id"), cycleId));
				}
				if (categoryType != null) {
					predicates.add(builder.equal(root.get("affirmation").get("categoryType"), categoryType));
				}				
				return builder.and(predicates.toArray(new Predicate[0]));
			}
		};

	}

}
