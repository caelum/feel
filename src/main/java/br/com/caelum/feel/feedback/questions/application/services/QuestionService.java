package br.com.caelum.feel.feedback.questions.application.services;

import static java.util.Optional.ofNullable;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.caelum.feel.feedback.companyteams.domain.models.LastCompanyTeamVersion;
import br.com.caelum.feel.feedback.companyteams.domain.repositories.LastCompanyTeamVersionRepository;
import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;
import br.com.caelum.feel.feedback.questions.application.forms.QuestionForm;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.models.vo.QuestionState;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;

@Service
public class QuestionService {

    private final Questions questions;
    private CycleRepository cycleRepository;
    private LastCompanyTeamVersionRepository lastCompanyTeamVersionRepository;

    public QuestionService(Questions questions,CycleRepository cycleRepository,LastCompanyTeamVersionRepository lastCompanyTeamVersionRepository) {
        this.questions = questions;
		this.cycleRepository = cycleRepository;
		this.lastCompanyTeamVersionRepository = lastCompanyTeamVersionRepository;
    }

    public Page<Question> getAllPaged(Integer currentPage) {
        return questions.findAll(PageRequest.of(currentPage, 5));
    }

    @Transactional
    public void saveBy(QuestionForm form) {

        var id = form.getId();
        var optionalQuestion = ofNullable(id).flatMap(questions::findById);
        
        var formQuestion = form.toQuestion(cycleRepository);

        if(optionalQuestion.isPresent()) {
        	Question question = optionalQuestion.get();
			question.updateFromForm(formQuestion);
        } else {        	        	
        	Set<LastCompanyTeamVersion> lastVersionOfTeams = lastCompanyTeamVersionRepository.bla();
			formQuestion.addTeams(lastVersionOfTeams);
        	questions.save(formQuestion);        	
        }
        

        


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
