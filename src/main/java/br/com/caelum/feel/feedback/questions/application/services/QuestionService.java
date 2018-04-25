package br.com.caelum.feel.feedback.questions.application.services;

import br.com.caelum.feel.feedback.questions.application.forms.OpenCloseStateForm;
import br.com.caelum.feel.feedback.questions.application.forms.QuestionForm;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.models.vo.QuestionState;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class QuestionService {

    private final Questions questions;

    public QuestionService(Questions questions) {
        this.questions = questions;
    }

    public Page<Question> getAllPaged(Integer currentPage) {
        return questions.findAll(PageRequest.of(currentPage, 5));
    }

    public void saveBy(QuestionForm form) {

        var id = form.getId();
        var optionalQuestion = ofNullable(id).flatMap(questions::findById);

        optionalQuestion.ifPresent(question -> question.updateFromForm(form));

        var question = optionalQuestion.orElseGet(form::toQuestion);

        questions.save(question);

    }

    public void fillFormOnlyWhenIdIsPresent(Optional<Long> optionalId, QuestionForm form) {
        optionalId
                .flatMap(questions::findById)
                    .ifPresent(form::fromQuestion);
    }

    public Optional<Question> removeById(Long id) {

        var question = questions.findById(id);

        question.ifPresent(questions::delete);

        return question;

    }

    public Optional<Question> tryTransitState(Long id, QuestionState targetState) {

        var optionalQuestion = questions.findById(id);

        optionalQuestion.ifPresent(question -> {
            if (targetState.isOpen())
                question.open();
            else
                question.close();

            questions.save(question);
        });

        return optionalQuestion;
    }
}
