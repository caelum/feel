package br.com.caelum.feel.feedback.application.admin.questions.forms;

import br.com.caelum.feel.feedback.domain.questions.models.Question;
import br.com.caelum.feel.feedback.domain.questions.models.vo.Affirmation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class QuestionForm {

    private Long id;

    @NotEmpty
    private String explanation;

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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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

    public void fromQuestion(Question question){
        id = question.getId();
        explanation = question.getExplanation();
        statement = question.getStatement();
        descriptionOfLowerValue = question.getDescriptionOfLowerValue();
        descriptionOfHighestValue = question.getDescriptionOfHighestValue();
        dueDate = question.getDueDate();
    }

    public Question toQuestion() {
        var affirmation = createAffirmation();
        return new Question(explanation, affirmation, dueDate);
    }

    public Affirmation getAffirmation(){
        return createAffirmation();
    }

    private Affirmation createAffirmation(){
        return new Affirmation(statement, descriptionOfLowerValue, descriptionOfHighestValue);
    }

}
