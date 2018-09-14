package br.com.caelum.feel.feedback.reports.application.views;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.feedback.questions.domain.models.Question;

public class AllAnsewrs {

	private List<FeedbackAnswer> answers;
	private LinkedHashSet<Question> questions = new LinkedHashSet<>();

	public AllAnsewrs(List<FeedbackAnswer> answers) {
		this.answers = answers;
		this.answers.forEach(answer -> {
			questions.add(answer.getQuestion());
		});
	}

	public List<Question> getQuestions() {
		return new ArrayList<>(questions);
	}

	public List<FeedbackAnswer> findAnswers(Question currentQuestion) {
		return filterAnswersPerQuestion(currentQuestion).collect(Collectors.toList());
	}

	public BigDecimal avg(Question currentQuestion) {
		Stream<FeedbackAnswer> filteredAnswers = filterAnswersPerQuestion(currentQuestion);
		
		BigDecimal sum = filteredAnswers.map(answer -> new BigDecimal(answer.getValue()))
				.reduce(BigDecimal.ZERO, (current, value) -> current.add(value));
		
		//aqui eu to fazendo o filtro de novo... o melhor é já deixar calculado o número de respostas por pergunta na criação do objeto... quem vai fazer?
		return sum.divide(new BigDecimal(filterAnswersPerQuestion(currentQuestion).count()),2,RoundingMode.HALF_EVEN);
	}

	private Stream<FeedbackAnswer> filterAnswersPerQuestion(Question currentQuestion) {
		Stream<FeedbackAnswer> filteredAnswers = answers.stream().filter(answer -> answer.getQuestion().equals(currentQuestion));
		return filteredAnswers;
	}

}
