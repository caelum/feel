package br.com.caelum.feel.feedback.domain.questions.models;

import br.com.caelum.feel.feedback.domain.questions.models.vo.Affirmation;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class Question {

    private String explanation;

    private Affirmation affirmation;
    private LocalDate dueDate;

    public Question(String explanation, Affirmation affirmation, LocalDate dueDate) {
        Assert.hasText(explanation, "Explanation required");
        Assert.notNull(affirmation, "Affirmation required");
        Assert.notNull(dueDate, "Due date required");
        Assert.isTrue(dueDate.isAfter(LocalDate.now()), "Due date should be in the future");

        this.explanation = explanation;
        this.affirmation = affirmation;
        this.dueDate = dueDate;
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
}
