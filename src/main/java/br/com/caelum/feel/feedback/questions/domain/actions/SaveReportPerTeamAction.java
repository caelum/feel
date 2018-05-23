package br.com.caelum.feel.feedback.questions.domain.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.feel.feedback.companyteams.domain.models.LastCompanyTeamVersion;
import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.models.ReportPerTeamAnswer;
import br.com.caelum.feel.feedback.questions.domain.respositories.FeedbackAnswerRepository;
import br.com.caelum.feel.feedback.questions.domain.respositories.ReportPerTeamAnswerRepository;

@Service
public class SaveReportPerTeamAction {

	@Autowired
	private FeedbackAnswerRepository feedbackAnswerRepository;
	@Autowired
	private ReportPerTeamAnswerRepository reportPerTeamAnswerRepository;

	public void execute(FeedbackAnswer feedbackAnswer) {
		Question currentQuestion = feedbackAnswer.getQuestion();
		
		Number answersCount = feedbackAnswerRepository.countByQuestionIdAndTeamId(currentQuestion.getId(),feedbackAnswer.getTeam().getId());
		Number sumValuesOfQuestion = feedbackAnswerRepository.sumValuesOfQuestion(feedbackAnswer.getQuestion().getId(),feedbackAnswer.getTeam().getId());
		LastCompanyTeamVersion lastCompanyTeamVersion = currentQuestion.findCurrentVersionOfTeam(feedbackAnswer.getTeam());
		
		reportPerTeamAnswerRepository.save(new ReportPerTeamAnswer(feedbackAnswer,answersCount,sumValuesOfQuestion,lastCompanyTeamVersion));		
	}

}
