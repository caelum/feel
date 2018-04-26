package br.com.caelum.feel.feedback.questions.application.validators;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.caelum.feel.feedback.questions.application.forms.QuestionForm;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;

public class JustOneLastQuestionValidator implements Validator {

	private Questions questions;

	public JustOneLastQuestionValidator(Questions questions) {
		this.questions = questions;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(QuestionForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		QuestionForm form = (QuestionForm) target;

		if (form.isLastOne()) {
			Optional<Question> possibleQuestion = questions.findByLastOneAndCycleId(true,
					form.getCycleId());
			
			if (possibleQuestion.isPresent()
					&& !possibleQuestion.get().getId().equals(form.getId())) {

				errors.rejectValue("lastOne", "", "A questão "
						+ possibleQuestion.get().getStatement() + " já está marcada como última");
			}
		}
	}

}
