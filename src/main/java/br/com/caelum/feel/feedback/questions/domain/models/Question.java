package br.com.caelum.feel.feedback.questions.domain.models;

import br.com.caelum.feel.feedback.questions.application.forms.QuestionForm;
import br.com.caelum.feel.feedback.questions.domain.models.vo.Affirmation;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

    @Future
    @NotNull
    @Column(name = "due_date")
    private LocalDate dueDate;

    /**
     * @deprecated frameworks only
     */
    @Deprecated(since = "1.0.0")
    Question(){}

    public Question(String explanation, Affirmation affirmation, LocalDate dueDate) {
        Assert.hasText(explanation, "Explanation required");
        Assert.notNull(affirmation, "Affirmation required");
        Assert.notNull(dueDate, "Due date required");
        Assert.isTrue(dueDate.isAfter(LocalDate.now()), "Due date should be in the future");

        this.explanation = explanation;
        this.affirmation = affirmation;
        this.dueDate = dueDate;
        this.hash = UUID.randomUUID().toString();
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

    public void updateFromForm(QuestionForm form) {
        explanation = form.getExplanation();
        dueDate = form.getDueDate();
        affirmation = form.getAffirmation();
    }
}
