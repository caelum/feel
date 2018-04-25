package br.com.caelum.feel.feedback.questions.application.forms;

import br.com.caelum.feel.feedback.questions.domain.models.vo.QuestionState;

public class OpenCloseStateForm {

    private QuestionState state;

    public void setState(QuestionState state) {
        this.state = state;
    }

    public QuestionState getState() {
        return state;
    }
}
