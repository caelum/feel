package br.com.caelum.feel.feedback.questions.application.forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;
import br.com.caelum.feel.feedback.companyteams.domain.repositories.Teams;
import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.feedback.questions.domain.models.Question;

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

	public FeedbackAnswer toAnswer(Question question, Teams teamRepository) {		
		CompanyTeam team = teamRepository.findById(this.teamId).get();		
		return new FeedbackAnswer(value,comments,question,team);
	}

}
