package br.com.caelum.feel.feedback.questions.domain.actions;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.feel.feedback.companyteams.domain.models.LastCompanyTeamVersion;
import br.com.caelum.feel.feedback.companyteams.domain.repositories.LastCompanyTeamVersionRepository;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;

@Service
public class UpdateQuestionsForTeamAction {

	@Autowired
	private LastCompanyTeamVersionRepository lastCompanyTeamVersionRepository;
	@Autowired
	private Questions questionRepository;
	
	public Collection<Question> executeForAllQuestions() {
        List<Question> allQuestions = questionRepository.findAllCurrentOpenQuestions();
        Set<LastCompanyTeamVersion> lastVersionOfTeams = lastCompanyTeamVersionRepository.listLastVersions();
        allQuestions.forEach(question -> question.updateTeams(lastVersionOfTeams));
        return allQuestions;
        
	}
	
	public Question execute(Question question) {
        Set<LastCompanyTeamVersion> lastVersionOfTeams = lastCompanyTeamVersionRepository.listLastVersions();
        question.updateTeams(lastVersionOfTeams);
        return question;
	}
}
