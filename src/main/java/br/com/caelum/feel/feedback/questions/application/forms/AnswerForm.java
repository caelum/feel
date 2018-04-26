package br.com.caelum.feel.feedback.questions.application.forms;

import org.hibernate.validator.constraints.Range;

import br.com.caelum.feel.feedback.companyteams.domain.models.LastCompanyTeamVersion;
import br.com.caelum.feel.feedback.companyteams.domain.repositories.LastCompanyTeamVersionRepository;
import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;

import javax.validation.constraints.NotNull;

public class AnswerForm {

    @NotNull
    private Long teamId;

    @NotNull
    @Range(min = 1, max=5)
    private Integer value;

    private String comments;


    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "AnswerForm{" +
                "teamId=" + teamId +
                ", value=" + value +
                ", comments='" + comments + '\'' +
                '}';
    }

	public FeedbackAnswer toAnswer(LastCompanyTeamVersionRepository lastCompanyTeamVersionRepository) {		
		LastCompanyTeamVersion lastVersionOfTeam = lastCompanyTeamVersionRepository.findByLastVersion(teamId);		
		return new FeedbackAnswer(value,comments,lastVersionOfTeam);
	}

}
