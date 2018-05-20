package br.com.caelum.feel.feedback.reports.application.views;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.models.ReportPerTeamAnswer;

public class ReportPerTeamAnswerTable {

	private List<ReportPerTeamAnswer> answers;

	public ReportPerTeamAnswerTable(List<ReportPerTeamAnswer> answers) {
		this.answers = answers;
	}
	
	public BigDecimal percent(CompanyTeam otherTeam, Question otherQuestion) {
		Optional<ReportPerTeamAnswer> found = answers.stream().filter(answer -> answer.belongs(otherTeam, otherQuestion)).findFirst();
		
		return found.map(answer -> answer.getPercentAnswer()).orElse(BigDecimal.ZERO);
	}

}
