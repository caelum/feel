package br.com.caelum.feel.feedback.questions.domain.models.vo;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Embeddable
public class Affirmation {

    @NotEmpty
    private String statement;

    @NotEmpty
    @Column(name = "description_of_lower_value")
    private String descriptionOfLowerValue;

    @NotEmpty
    @Column(name = "description_of_highest_value")
    private String descriptionOfHighestValue;

    /**
     * @deprecated frameworks only
     */
    @Deprecated(since = "1.0.0")
    Affirmation(){}

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
