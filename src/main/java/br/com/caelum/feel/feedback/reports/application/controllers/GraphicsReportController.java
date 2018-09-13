package br.com.caelum.feel.feedback.reports.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;
import br.com.caelum.feel.feedback.questions.domain.models.AverageValuePerQuestionResult;
import br.com.caelum.feel.feedback.questions.domain.respositories.ReportPerTeamAnswerRepository;
import br.com.caelum.feel.feedback.reports.application.graphics.BarChartAverageValuesPerQuestionData;

@Controller
public class GraphicsReportController {

	@Autowired
	private CycleRepository cycleRepository;
	@Autowired
	private ReportPerTeamAnswerRepository reportPerTeamAnswerRepository;

	@GetMapping("/admin/reports/feedback/values/barchart")
	public String valuesBarChart(Model model, Integer cycleId) {
		model.addAttribute("cycle", cycleRepository.findById(cycleId).get());
		return "admin/reports/bar-chart-question-values";
	}

	@GetMapping("/admin/reports/feedback/values/barchart/data/{cycleId}")
	@ResponseBody
	public BarChartAverageValuesPerQuestionData valuesBarChart(@PathVariable("cycleId") Integer cycleId) {
		
		List<AverageValuePerQuestionResult> results = reportPerTeamAnswerRepository.averagePerQuestion(cycleId);
		
		return new BarChartAverageValuesPerQuestionData(results);
	}
}
