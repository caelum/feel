package br.com.caelum.feel.feedback.reports.application.views;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.feedback.questions.domain.models.Question;

public class AllAnsewrsPerTeam {

	private List<FeedbackAnswer> answers;
	private LinkedHashSet<Question> questions = new LinkedHashSet<>();

	public AllAnsewrsPerTeam(List<FeedbackAnswer> answers) {
		this.answers = answers;
		this.answers.forEach(answer -> {			
			questions.add(answer.getQuestion());
		});
	}
	
	public List<Question> getQuestions(){
		return new ArrayList<>(questions);
	}
	
	public List<FeedbackAnswer> findAnswers(Question currentQuestion){
		return answers.stream().filter(answer -> {
			return answer.getQuestion().equals(currentQuestion);
		}).collect(Collectors.toList());
	}
	
	

}
