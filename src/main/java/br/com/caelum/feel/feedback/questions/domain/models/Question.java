package br.com.caelum.feel.feedback.questions.domain.models;

import br.com.caelum.feel.feedback.questions.application.forms.QuestionForm;
import br.com.caelum.feel.feedback.questions.domain.models.vo.Affirmation;
import br.com.caelum.feel.feedback.questions.domain.models.vo.QuestionState;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

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

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private QuestionState currentState;

    /**
     * @deprecated frameworks only
     */
    @Deprecated(since = "1.0.0")
    Question(){}

    public Question(String explanation, Affirmation affirmation, QuestionState state) {
        Assert.hasText(explanation, "Explanation required");
        Assert.notNull(affirmation, "Affirmation required");
        Assert.notNull(state, "Initial state required");

        this.explanation = explanation;
        this.affirmation = affirmation;
        this.hash = UUID.randomUUID().toString();
        this.currentState = state;
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

    public void updateFromForm(QuestionForm form) {
        explanation = form.getExplanation();
        affirmation = form.getAffirmation();
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
