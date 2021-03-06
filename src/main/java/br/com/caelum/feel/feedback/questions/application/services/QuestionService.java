package br.com.caelum.feel.feedback.questions.application.services;

import static java.util.Optional.ofNullable;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;
import br.com.caelum.feel.feedback.questions.application.forms.QuestionForm;
import br.com.caelum.feel.feedback.questions.domain.actions.UpdateQuestionsForTeamAction;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;

@Service
public class QuestionService {

	private final Questions questions;
	private CycleRepository cycleRepository;
	private UpdateQuestionsForTeamAction updateQuestionsForTeamAction;

	public QuestionService(Questions questions, CycleRepository cycleRepository,
			UpdateQuestionsForTeamAction updateQuestionsForTeamAction) {
		this.questions = questions;
		this.cycleRepository = cycleRepository;
		this.updateQuestionsForTeamAction = updateQuestionsForTeamAction;
	}

	@Transactional
	public void saveBy(QuestionForm form) {

		var id = form.getId();
		var optionalQuestion = ofNullable(id).flatMap(questions::findById);

		var formQuestion = updateQuestionsForTeamAction.execute(form.toQuestion(cycleRepository));

		if (optionalQuestion.isPresent()) {
			optionalQuestion.get().updateFromForm(formQuestion);
		} else {
			questions.save(formQuestion);
		}

	}

	public void fillFormOnlyWhenIdIsPresent(Optional<Long> optionalId, QuestionForm form) {
		optionalId.flatMap(questions::findById).ifPresent(form::fromQuestion);
	}
}
