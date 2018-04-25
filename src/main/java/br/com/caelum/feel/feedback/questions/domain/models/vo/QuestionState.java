package br.com.caelum.feel.feedback.questions.domain.models.vo;

public enum  QuestionState {
    OPEN,CLOSE;

    public boolean isOpen(){
        return this.equals(OPEN);
    }

    public boolean isClosed() {
        return this.equals(CLOSE);
    }
}
