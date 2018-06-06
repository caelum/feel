package br.com.caelum.feel.feedback.questions.application.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.feel.feedback.companyteams.domain.repositories.Teams;
import br.com.caelum.feel.feedback.questions.application.forms.AnswerForm;
import br.com.caelum.feel.feedback.questions.application.validators.ClosedQuestionValidator;
import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.respositories.FeedbackAnswerRepository;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;
import br.com.caelum.feel.feedback.reports.application.endpoints.UpdateQuestionsReportEndpoint;
import br.com.caelum.feel.infra.AsyncLocalEndpointExecutor;

@Controller
@RequestMapping("questions")
public class QuestionsController {

	@Autowired
	private Questions questions;
	@Autowired
	private Teams teams;
	@Autowired
	private FeedbackAnswerRepository feedbackAnswerRepository;
	@Autowired
	private UpdateQuestionsReportEndpoint updateQuestionsReportEndpoint;

	@GetMapping("{uuid}")
	public String form(Model view, @PathVariable String uuid, AnswerForm form) {

		var optionalQuestion = questions.findByHash(uuid);

		view.addAttribute("answerForm", form);
		view.addAttribute("allTeams", teams.findAll());
		view.addAttribute("question", optionalQuestion);

		return "questions/form";

	}

	@PostMapping("{uuid}")
	public String save(Model model, @PathVariable String uuid, @Valid AnswerForm form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return form(model, uuid, form);
		}

		if (!new ClosedQuestionValidator(questions).validate(uuid, bindingResult)) {
			return form(model, uuid, form);
		}

		Question currentQuestion = questions.findByHash(uuid);
		FeedbackAnswer feedbackAnswer = feedbackAnswerRepository
				.save(form.toAnswer(currentQuestion, teams));
			
		updateQuestionsReportEndpoint.execute(feedbackAnswer);
		
		redirectAttributes.addFlashAttribute("msg",
				"Resposta salva com sucesso! Obrigado por participar");
		return "redirect:/questions/" + uuid + "?done=true";
	}

}
