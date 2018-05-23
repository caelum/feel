package br.com.caelum.feel.feedback.questions.domain.models.vo;

import org.springframework.util.Assert;

import br.com.caelum.feel.feedback.questions.domain.models.CategoryType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @Enumerated(EnumType.STRING)
	private CategoryType categoryType;

    /**
     * @deprecated frameworks only
     */
    @Deprecated(since = "1.0.0")
    Affirmation(){}

    public Affirmation(String statement, String descriptionOfLowerValue, String descriptionOfHighestValue, CategoryType categoryType) {
        this.categoryType = categoryType;
		Assert.hasText(statement, "Statement required");
        Assert.hasText(descriptionOfLowerValue, "Description of lower value required");
        Assert.hasText(descriptionOfHighestValue, "Description of highest value required");
        Assert.notNull(categoryType,"O tipo de questão não pode ser nula");

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
    
    public CategoryType getCategoryType() {
		return categoryType;
	}
}
