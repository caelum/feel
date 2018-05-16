package br.com.caelum.feel.feedback.questions.domain.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;
import br.com.caelum.feel.feedback.companyteams.domain.models.LastCompanyTeamVersion;

@Entity
public class FeedbackAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Min(1)
	@Max(5)
	private int value;
	@Lob
	private String comments;
	@ManyToOne
	private CompanyTeam team;
	@NotBlank
	@Column(unique=true)
	private String hash;

	public FeedbackAnswer(int value, String comments,CompanyTeam team) {
		this.value = value;
		this.comments = comments;
		this.team = team;
		this.hash = UUID.randomUUID().toString();
	}

}
