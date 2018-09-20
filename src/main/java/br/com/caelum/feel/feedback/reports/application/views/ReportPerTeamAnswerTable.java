package br.com.caelum.feel.feedback.reports.application.views;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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

	/**
	 * 
	 * @param otherTeam time que deve ser buscado
	 * @param otherQuestion questao que deve ser buscada
	 * @return o valor em percentual. Ex: 0.2. Isso siginifica 20%. 
	 */
	public BigDecimal percentCount(CompanyTeam otherTeam, Question otherQuestion) {
		Optional<ReportPerTeamAnswer> found = findAnswer(otherTeam, otherQuestion);

		return found.map(answer -> answer.getPercentAnswer()).orElse(BigDecimal.ZERO);
	}

	private Optional<ReportPerTeamAnswer> findAnswer(CompanyTeam otherTeam,
			Question otherQuestion) {
		return answers.stream().filter(answer -> answer.belongs(otherTeam, otherQuestion))
				.findFirst();
	}

	public BigDecimal percentValue(CompanyTeam otherTeam, Question otherQuestion) {
		Optional<ReportPerTeamAnswer> found = findAnswer(otherTeam, otherQuestion);

		return found.map(answer -> answer.getPercentValue()).orElse(BigDecimal.ZERO);
	}

	public String peopleCount(CompanyTeam otherTeam, Question otherQuestion) {
		Optional<ReportPerTeamAnswer> found = findAnswer(otherTeam, otherQuestion);
		BigDecimal totalCount = new BigDecimal(
				found.map(answer -> answer.getPeopleCount()).orElse(0));

		int totalAnswers = totalCount.multiply(percentCount(otherTeam, otherQuestion),
				new MathContext(2, RoundingMode.UP)).intValue();

		return totalAnswers + "/" + totalCount;
	}

}
