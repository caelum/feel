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
import br.com.caelum.feel.feedback.questions.domain.models.ReportPerTeamAnswer;
import br.com.caelum.feel.feedback.questions.domain.respositories.FeedbackAnswerRepository;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;
import br.com.caelum.feel.feedback.questions.domain.respositories.ReportPerTeamAnswerRepository;
import br.com.caelum.feel.feedback.reports.application.views.ReportPerTeamAnswerTable;

@Controller
public class FeedbackReportsController {

	@Autowired
	private SaveReportPerTeamAction saveReportPerTeamAction;
	@Autowired
	private FeedbackAnswerRepository feedbackAnswerRepository;
	@Autowired
	private ReportPerTeamAnswerRepository reportPerTeamAnswerRepository;
	@Autowired
	private Questions questionRepository;
	@Autowired
	private Teams teamRepository;

	@GetMapping("/admin/reports/feedbak/compare-number-answers")
	public String dashboardCompareAnswersPercent(Model model, @RequestParam("cycleId") Integer cycleId) {

		List<ReportPerTeamAnswer> answers = reportPerTeamAnswerRepository.listCurrentView(cycleId);
		model.addAttribute("answersList", new ReportPerTeamAnswerTable(answers));
		model.addAttribute("questionsList", questionRepository
				.findAllQuestionsByCycleIdOrderedByDateAsc(cycleId, PageRequest.of(0, 6)));
		model.addAttribute("teamsList", teamRepository.findAll());
		return "admin/reports/compare-teams";
	}
	
	@GetMapping("/admin/reports/feedbak/compare-number-answers-values")
	public String dashboardCompareValuesPercent(Model model, @RequestParam("cycleId") Integer cycleId) {
		
		List<ReportPerTeamAnswer> answers = reportPerTeamAnswerRepository.listCurrentView(cycleId);
		model.addAttribute("answersList", new ReportPerTeamAnswerTable(answers));
		model.addAttribute("questionsList", questionRepository
				.findAllQuestionsByCycleIdOrderedByDateAsc(cycleId, PageRequest.of(0, 6)));
		model.addAttribute("teamsList", teamRepository.findAll());
		return "admin/reports/compare-answers-values";
	}

	@PostMapping("/admin/reports/feedback/views/per-team/{answerId}")
	public HttpEntity<?> saveRepostAnswerPerTeam(@PathVariable("answerId") Integer answerId) {
		saveReportPerTeamAction.execute(feedbackAnswerRepository.findById(answerId).get());
		return ResponseEntity.ok("");
	}
}
