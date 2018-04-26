package br.com.caelum.feel.feedback.questions.domain.models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.caelum.feel.feedback.cycles.domain.models.Cycle;
import br.com.caelum.feel.feedback.questions.domain.models.vo.Affirmation;
import br.com.caelum.feel.feedback.questions.domain.models.vo.QuestionState;

@Entity(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NotEmpty
    private String explanation;

    @NotNull
    private Affirmation affirmation;

    @NotEmpty
    private String hash;

    @Future
    @NotNull
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private QuestionState currentState;
    
    @ManyToOne
    @NotNull
    private Cycle cycle;
    
    private boolean lastOne;

    /**
     * @deprecated frameworks only
     */
    @Deprecated(since = "1.0.0")
    Question(){}

    public Question(String explanation, Affirmation affirmation, LocalDate dueDate, QuestionState state,Cycle cycle, boolean lastOne) {        
		Assert.hasText(explanation, "Explanation required");
        Assert.notNull(affirmation, "Affirmation required");
        Assert.notNull(dueDate, "Due date required");
        Assert.isTrue(dueDate.isAfter(LocalDate.now()), "Due date should be in the future");
        Assert.notNull(state, "Initial state required");
        Assert.notNull(cycle, "Cycle é obrigatório");

        this.explanation = explanation;
        this.affirmation = affirmation;
        this.dueDate = dueDate;
        this.hash = UUID.randomUUID().toString();
        this.currentState = state;
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

    public String getExplanation() {
        return explanation;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(affirmation, question.affirmation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(affirmation);
    }

    public void updateFromForm(Question otherQuestion) {
        explanation = otherQuestion.explanation;
        affirmation = otherQuestion.affirmation;
        cycle = otherQuestion.cycle;
        lastOne = otherQuestion.lastOne;        
        dueDate = otherQuestion.dueDate;
    }

    public QuestionState getCurrentState() {
        return currentState;
    }


    public void open(){
        if (currentState.isOpen()){
            throw new IllegalStateException("Question is already open");
        }

        this.currentState = QuestionState.OPEN;
    }


    public void close(){
        if (currentState.isClosed()){
            throw new IllegalStateException("Question is already closed");
        }

        this.currentState = QuestionState.CLOSE;
    }
}
