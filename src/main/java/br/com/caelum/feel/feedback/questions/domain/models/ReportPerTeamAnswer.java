package br.com.caelum.feel.feedback.questions.domain.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;
import br.com.caelum.feel.feedback.companyteams.domain.models.LastCompanyTeamVersion;
import br.com.caelum.feel.feedback.cycles.domain.models.Cycle;

@Entity
public class ReportPerTeamAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int answersCount;
	private int peopleCount;
	@Column(scale=4,precision=19)
	private BigDecimal percentAnswer;
	@ManyToOne
	@NotNull
	private Question question;
	@NotBlank
	private String questionStatement;
	@ManyToOne
	@NotNull
	private CompanyTeam team;
	@NotBlank
	private String teamName;
	@ManyToOne
	@NotNull
	private FeedbackAnswer feedbackAnswer;
	@ManyToOne
	@NotNull
	private Cycle cycle;	
	private int sumValue;
	@Column(scale=4,precision=19)
	private BigDecimal percentValue;
	
	/**
	 * @deprecated
	 */
	public ReportPerTeamAnswer() {

	}

	public ReportPerTeamAnswer(FeedbackAnswer feedbackAnswer, Number answersCount,Number sumValue,
			LastCompanyTeamVersion lastCompanyTeamVersion) {
		this.cycle = feedbackAnswer.getQuestion().getCycle();
		this.feedbackAnswer = feedbackAnswer;
		this.answersCount = answersCount.intValue();
		this.sumValue = sumValue.intValue();
		this.percentValue = new BigDecimal(this.sumValue).divide(
				new BigDecimal(this.answersCount), 3,
				RoundingMode.HALF_EVEN);
		this.peopleCount = lastCompanyTeamVersion.getTotalExpectedPeople();
		this.percentAnswer = new BigDecimal(answersCount.intValue()).divide(
				new BigDecimal(lastCompanyTeamVersion.getTotalExpectedPeople()), 3,
				RoundingMode.HALF_EVEN);

		this.question = feedbackAnswer.getQuestion();
		this.questionStatement = feedbackAnswer.getQuestion().getStatement();
		this.team = feedbackAnswer.getTeam();
		this.teamName = feedbackAnswer.getTeam().getName();		
	}

	public int getAnswersCount() {
		return answersCount;
	}

	public int getPeopleCount() {
		return peopleCount;
	}

	public BigDecimal getPercentAnswer() {
		return percentAnswer;
	}

	public Question getQuestion() {
		return question;
	}

	public String getQuestionStatement() {
		return questionStatement;
	}

	public CompanyTeam getTeam() {
		return team;
	}

	public String getTeamName() {
		return teamName;
	}
	
	public BigDecimal getPercentValue() {
		return percentValue;
	}

	public boolean belongs(CompanyTeam otherTeam, Question otherQuestion) {
		return this.question.equals(otherQuestion) && this.team.equals(otherTeam);
		
	}
}
