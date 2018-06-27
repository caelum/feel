package br.com.caelum.feel.feedback.questions.application.views;

import org.springframework.stereotype.Component;

import br.com.caelum.feel.feedback.questions.application.forms.AnswerForm;
import br.com.caelum.feel.feedback.questions.domain.models.Question;

@Component
public class QuestionFormScripts {

	public String generate(Question currentQuestion,AnswerForm form) {
		StringBuilder scripts = new StringBuilder();
		if(currentQuestion.isClosed()) {
			scripts.append("$('#myModal').modal();");
		}
		if(form.isDone()) {			
			scripts.append("$('#answeredModal').modal();");
		}
		return scripts.toString();
	}
}
