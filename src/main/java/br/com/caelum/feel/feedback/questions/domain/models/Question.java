package br.com.caelum.feel.feedback.questions.domain.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;
import br.com.caelum.feel.feedback.companyteams.domain.models.LastCompanyTeamVersion;
import br.com.caelum.feel.feedback.cycles.domain.models.Cycle;
import br.com.caelum.feel.feedback.questions.domain.models.vo.Affirmation;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Affirmation affirmation;

	@NotEmpty
	private String hash;

	@NotNull
	@Column(name = "due_date")
	private LocalDate dueDate;

	@ManyToOne
	@NotNull
	private Cycle cycle;

	private boolean lastOne;

	@ManyToMany
	private Set<LastCompanyTeamVersion> teams = new HashSet<>();
	
	/**
	 * @deprecated frameworks only
	 */
	@Deprecated(since = "1.0.0")
	public Question() {
	}

	public Question(Affirmation affirmation, LocalDate dueDate, Cycle cycle,
			boolean lastOne) {
		Assert.notNull(affirmation, "Affirmation required");
		Assert.notNull(dueDate, "Due date required");
		Assert.notNull(cycle, "Cycle é obrigatório");

		this.affirmation = affirmation;
		this.dueDate = dueDate;
		this.hash = UUID.randomUUID().toString();
		this.cycle = cycle;
		this.lastOne = lastOne;
	}

	public boolean isLastOne() {
		return lastOne;
	}

	public Cycle getCycle() {
		return cycle;
	}

	public String getStatement() {
		return affirmation.getStatement();
	}

	public String getDescriptionOfLowerValue() {
		return affirmation.getDescriptionOfLowerValue();
	}

	public String getDescriptionOfHighestValue() {
		return affirmation.getDescriptionOfHighestValue();
	}
	
	

	public CategoryType getCategoryType() {
		return affirmation.getCategoryType();
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public Long getId() {
		return id;
	}

	public String getHash() {
		return hash;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Question question = (Question) o;
		return Objects.equals(affirmation, question.affirmation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(affirmation);
	}

	// TODO testes :P
	public void updateFromForm(Question otherQuestion) {
		affirmation = otherQuestion.affirmation;
		cycle = otherQuestion.cycle;
		lastOne = otherQuestion.lastOne;
		dueDate = otherQuestion.dueDate;

		updateTeams(otherQuestion.teams);
	}

	public boolean isClosed() {
		return LocalDate.now().compareTo(dueDate) > 0;
	}

	public boolean isOpen() {
		return !isClosed();
	}

	public void addTeams(Set<LastCompanyTeamVersion> lastVersionOfTeams) {
		this.teams.addAll(lastVersionOfTeams);
	}
	
	public void updateTeams(Set<LastCompanyTeamVersion> lastVersionOfTeams) {
		this.teams.clear();
		addTeams(lastVersionOfTeams);
	}

	public boolean isFirst() {
		return cycle.isFirstQuestion(this);
	}

	public LastCompanyTeamVersion findCurrentVersionOfTeam(CompanyTeam otherTeam) {
		Set<LastCompanyTeamVersion> foundTeams = teams.stream()
				.filter(version -> version.getTeam().equals(otherTeam)).collect(Collectors.toSet());
		
		Assert.state(!foundTeams.isEmpty(), "Por algum motivo o time em questão não esta associado com a questao "+this.id+". Será que ele foi adicionado depois? Atualiza a questão talvez resolva");
		Assert.state(foundTeams.size() == 1, "Apenas um time deveria estar associado com a questao");
		
		return foundTeams.iterator().next();
	}

}
