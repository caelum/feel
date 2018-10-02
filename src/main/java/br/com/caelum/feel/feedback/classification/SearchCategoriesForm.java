package br.com.caelum.feel.feedback.classification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.Specification;

import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;

public class SearchCategoriesForm {

	private Integer cycleId;
	@Size(min = 1)
	private List<Integer> categoryIds = new ArrayList<>();

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public List<Integer> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Integer> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public Specification<CategorizedInfo> build() {
		return (root,query,builder) -> {
			ArrayList<Predicate> predicates = new ArrayList<>();			
			predicates.add(root.get("id").in(categoryIds));
			
			if(cycleId != null) {
				predicates.add(builder.equal(root.get("feedbackAnswer").get("question").get("cycle").get("id"), cycleId));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));			
		};
	}

}
