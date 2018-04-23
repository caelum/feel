package br.com.caelum.feel.feedback.domain.questions.models.vo;

import org.springframework.util.Assert;

import java.util.Objects;

public class Affirmation {
    private final String statement;
    private final String descriptionOfLowerValue;
    private final String descriptionOfHighestValue;

    public Affirmation(String statement, String descriptionOfLowerValue, String descriptionOfHighestValue) {
        Assert.hasText(statement, "Statement required");
        Assert.hasText(descriptionOfLowerValue, "Description of lower value required");
        Assert.hasText(descriptionOfHighestValue, "Description of highest value required");

        this.statement = statement;
        this.descriptionOfLowerValue = descriptionOfLowerValue;
        this.descriptionOfHighestValue = descriptionOfHighestValue;
    }

    public String getStatement() {
        return statement;
    }

    public String getDescriptionOfLowerValue() {
        return descriptionOfLowerValue;
    }

    public String getDescriptionOfHighestValue() {
        return descriptionOfHighestValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Affirmation that = (Affirmation) o;
        return Objects.equals(statement, that.statement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement);
    }
}
