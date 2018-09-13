package br.com.caelum.feel.feedback.reports.application.graphics;

import br.com.caelum.feel.feedback.questions.domain.models.Question;

public interface AverageValuePerQuestionResult {

	Number getValue();
	
	Question getQuestion();

}
