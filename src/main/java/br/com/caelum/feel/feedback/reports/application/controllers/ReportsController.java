package br.com.caelum.feel.feedback.reports.application.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.caelum.feel.feedback.companyteams.domain.repositories.Teams;
import br.com.caelum.feel.feedback.questions.domain.actions.SaveReportPerTeamAction;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.respositories.FeedbackAnswerRepository;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;

@Controller
public class ReportsController {
	
	@Autowired
	private Questions questionRepository;
	@Autowired
	private Teams teamRepository;
	@Autowired
	private SaveReportPerTeamAction saveReportPerTeamAction;
	@Autowired
	private FeedbackAnswerRepository feedbackAnswerRepository;

	@GetMapping("/admin/reports/compare-teams")
	public String dashboardCompareTeams(Model model,@RequestParam("cycleId") Integer cycleId) {
		List<Question> questions = questionRepository.findAllQuestionsByCycleIdOrderedByDateAsc(cycleId, PageRequest.of(0, 6));
		model.addAttribute("questionsList",questions);
		model.addAttribute("teamsList", teamRepository.findAll());
		return "admin/reports/compare-teams";
	}
	
	@PostMapping("/admin/reports/views/per-team/{answerId}")
	public HttpEntity<?> saveRepostAnswerPerTeam(@PathVariable("answerId") Integer answerId){
		saveReportPerTeamAction.execute(feedbackAnswerRepository.findById(answerId).get());
		return ResponseEntity.ok("");
	}
}
