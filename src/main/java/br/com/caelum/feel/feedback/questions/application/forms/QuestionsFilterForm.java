package br.com.caelum.feel.feedback.questions.application.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;
import br.com.caelum.feel.feedback.questions.domain.models.CategoryType;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.respositories.QuestionSearchExample;
import br.com.caelum.feel.infra.ApplicationContextHolder;

public class QuestionsFilterForm {

	private Integer cycleId;
	private CategoryType categoryType;
	@Autowired
	private CycleRepository cycleRepository;

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

	public Example<Question> build() {
		ApplicationContextHolder.autorwire(this);
		QuestionSearchExample example = new QuestionSearchExample();
		if (cycleId != null) {
			example.setCycle(cycleRepository.findById(cycleId).get());
		}
		if (categoryType != null) {
			example.setCategoryType(categoryType);
		}

		return example.build();

	}

}
