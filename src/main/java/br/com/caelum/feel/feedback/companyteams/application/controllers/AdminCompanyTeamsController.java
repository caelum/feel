package br.com.caelum.feel.feedback.companyteams.application.controllers;

import static java.util.Optional.empty;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.feel.feedback.companyteams.application.forms.TeamForm;
import br.com.caelum.feel.feedback.companyteams.application.services.SaveTeamService;
import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;
import br.com.caelum.feel.feedback.questions.domain.actions.UpdateQuestionsForTeamAction;
import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.feedback.questions.domain.respositories.FeedbackAnswerRepository;
import br.com.caelum.feel.feedback.reports.application.endpoints.UpdateQuestionsReportEndpoint;
import br.com.caelum.feel.infra.TransactionalRunner;

@Controller
@RequestMapping("admin/company-teams")
public class AdminCompanyTeamsController {


	@Autowired
    private SaveTeamService service;
	@Autowired
    private UpdateQuestionsForTeamAction updateQuestionsForTeamAction;
	@Autowired
    private TransactionalRunner transactionalRunner;	
	@Autowired
	private FeedbackAnswerRepository feedbackAnswerRepository;
	@Autowired
	private UpdateQuestionsReportEndpoint updateQuestionsReportEndpoint;	

    @GetMapping
    public ModelAndView list(Optional<Integer> page){
        var view = new ModelAndView("admin/company-teams/list");
        var currentPage = page.orElse(0);
        view.addObject("teams", service.getAllPaged(currentPage));

        return view;

    }

    @GetMapping(value = {"new", "{optionalId}"})
    public ModelAndView form(@PathVariable Optional<Long> optionalId, TeamForm form){
        var view = new ModelAndView("admin/company-teams/form");

        service.fillFormOnlyWhenIdIsPresent(optionalId, form);

        view.addObject("teamForm", form);

        return view;
    }


    @PostMapping
    public ModelAndView save(@Valid TeamForm form, BindingResult result, RedirectAttributes redirect){
        var view = new ModelAndView("redirect:/admin/company-teams");

        if (result.hasErrors()) {
            return form(empty(), form);
        }

        CompanyTeam registeredTeam = transactionalRunner.run(() -> service.saveByForm(form));
        transactionalRunner.run(() -> updateQuestionsForTeamAction.executeForAllQuestions());

        //TODO qual classe eu deveria ter criado para isolar esse trecho de código? Deveria mesmo ter criado?
        Optional<FeedbackAnswer> possibleAnswer = feedbackAnswerRepository.findLastAnswerPerTeam(registeredTeam.getId());
        possibleAnswer.ifPresent(updateQuestionsReportEndpoint :: execute);
        
        redirect.addFlashAttribute("msg", String.format("Time %s salvo com sucesso!", form.getName()));

        return view;
    }


    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<CompanyTeam> delete(@PathVariable Long id){
        var removedTeam = service.removeById(id);
        return removedTeam.map(ResponseEntity.accepted()::body).orElseGet(ResponseEntity.noContent()::build);
    }
}
