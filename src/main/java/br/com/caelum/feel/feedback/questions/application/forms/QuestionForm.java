package br.com.caelum.feel.feedback.questions.application.forms;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.models.vo.Affirmation;
import br.com.caelum.feel.feedback.questions.domain.models.vo.QuestionState;

public class QuestionForm {

	private Long id;

	@NotEmpty
	private String statement;

	@NotEmpty
	private String descriptionOfLowerValue;

	@NotEmpty
	private String descriptionOfHighestValue;

	@NotNull
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;

	private boolean lastOne;

	@NotNull
	private Integer cycleId;

	public boolean isLastOne() {
		return lastOne;
	}

	public void setLastOne(boolean lastOne) {
		this.lastOne = lastOne;
	}

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getDescriptionOfLowerValue() {
		return descriptionOfLowerValue;
	}

	public void setDescriptionOfLowerValue(String descriptionOfLowerValue) {
		this.descriptionOfLowerValue = descriptionOfLowerValue;
	}

	public String getDescriptionOfHighestValue() {
		return descriptionOfHighestValue;
	}

	public void setDescriptionOfHighestValue(String descriptionOfHighestValue) {
		this.descriptionOfHighestValue = descriptionOfHighestValue;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void fromQuestion(Question question) {
		id = question.getId();
		statement = question.getStatement();
		descriptionOfLowerValue = question.getDescriptionOfLowerValue();
		descriptionOfHighestValue = question.getDescriptionOfHighestValue();
		dueDate = question.getDueDate();
		cycleId = question.getCycle().getId();
		lastOne = question.isLastOne();
	}

	public Question toQuestion(CycleRepository cycleRepository) {
		var affirmation = createAffirmation();
		Question question = new Question(affirmation, dueDate, QuestionState.OPEN,
				cycleRepository.findById(this.cycleId).get(),lastOne);
		
		return question;
	}

	public Affirmation getAffirmation() {
		return createAffirmation();
	}

	private Affirmation createAffirmation() {
		return new Affirmation(statement, descriptionOfLowerValue, descriptionOfHighestValue);
	}

}
