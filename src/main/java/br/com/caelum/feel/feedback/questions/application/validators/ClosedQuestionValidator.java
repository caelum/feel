package br.com.caelum.feel.feedback.questions.application.validators;

import org.springframework.validation.Errors;

import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;

public class ClosedQuestionValidator {

	private Questions questions;

	public ClosedQuestionValidator(Questions questions) {
		super();
		this.questions = questions;
	}

	public boolean validate(String uuid,Errors errors) {
		Question question = questions.findByHash(uuid);
		
		if(question.isClosed()) {
			errors.rejectValue("teamId","","Essa questão já está fechada");
			return false;
		}		

		return true;
	}

}
