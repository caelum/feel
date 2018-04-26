package br.com.caelum.feel.feedback.questions.application.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.feel.feedback.companyteams.domain.repositories.LastCompanyTeamVersionRepository;
import br.com.caelum.feel.feedback.companyteams.domain.repositories.Teams;
import br.com.caelum.feel.feedback.questions.application.forms.AnswerForm;
import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.feedback.questions.domain.respositories.FeedbackAnswerRepository;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;

@Controller
@RequestMapping("questions")
public class QuestionsController {

	private final Questions questions;
	private final Teams teams;
	private final LastCompanyTeamVersionRepository lastCompanyTeamVersionRepository;
	private final FeedbackAnswerRepository feedbackAnswerRepository;

	public QuestionsController(Questions questions, Teams teams,
			LastCompanyTeamVersionRepository lastCompanyTeamVersionRepository,
			FeedbackAnswerRepository feedbackAnswerRepository) {
		this.questions = questions;
		this.teams = teams;
		this.lastCompanyTeamVersionRepository = lastCompanyTeamVersionRepository;
		this.feedbackAnswerRepository = feedbackAnswerRepository;
	}

	@GetMapping("{uuid}")
	public String form(Model view, @PathVariable String uuid, AnswerForm form) {

		var optionalQuestion = questions.findByHash(uuid);

		view.addAttribute("answerForm", form);
		view.addAttribute("allTeams", teams.findAll());
		view.addAttribute("question", optionalQuestion);

		return "questions/form";

	}

	@PostMapping("{uuid}")
	public String methodName(Model model, @PathVariable String uuid, @Valid AnswerForm form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return form(model, uuid, form);
		}

		feedbackAnswerRepository.save(form.toAnswer(lastCompanyTeamVersionRepository));
		
		redirectAttributes.addFlashAttribute("msg",
				"Resposta salva com sucesso! Obrigado por participar");
		return "redirect:/questions/" + uuid;
	}

}
