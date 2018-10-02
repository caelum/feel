package br.com.caelum.feel.feedback.questions.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;

import br.com.caelum.feel.feedback.classification.CategorizedInfo;
import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;

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
	@ManyToOne
	@NotNull
	private Question question;
	@NotNull
	private LocalDateTime instant = LocalDateTime.now();
	@OneToMany(mappedBy="feedbackAnswer")
	@BatchSize(size=20)
	private List<CategorizedInfo> categories = new ArrayList<>();
	
	/**
	 * @deprecated
	 */
	public FeedbackAnswer() {

	}

	public FeedbackAnswer(int value, String comments,Question question, CompanyTeam team) {
		this.value = value;
		this.comments = comments;
		this.question = question;
		this.team = team;
		this.hash = UUID.randomUUID().toString();		
	}

	public CompanyTeam getTeam() {
		return team;
	}

	public Question getQuestion() {
		return question;
	}

	public Integer getId() {
		return id;
	}
	
	public String getComments() {
		return comments;
	}
	
	public LocalDateTime getInstant() {
		return instant;
	}
	
	public int getValue() {
		return value;
	}
	
	public List<CategorizedInfo> getCategories() {
		return categories;
	}

}
