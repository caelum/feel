package br.com.caelum.feel.feedback.reports.application.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;
import br.com.caelum.feel.feedback.questions.domain.models.AverageValuePerQuestionResult;
import br.com.caelum.feel.feedback.questions.domain.models.AverageValuePerTeamnResult;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;
import br.com.caelum.feel.feedback.questions.domain.respositories.ReportPerTeamAnswerRepository;
import br.com.caelum.feel.feedback.reports.application.forms.SearchValuesPerTeamForm;
import br.com.caelum.feel.feedback.reports.application.graphics.BarChartAverageValuesPerQuestionData;
import br.com.caelum.feel.feedback.reports.application.graphics.GraphicData;

@Controller
public class GraphicsReportController {

	@Autowired
	private CycleRepository cycleRepository;
	@Autowired
	private ReportPerTeamAnswerRepository reportPerTeamAnswerRepository;
	@Autowired
	private Questions questions;

	@GetMapping("/admin/reports/feedback/values/barchart")
	public String valuesBarChart(Model model, Integer cycleId) {
		model.addAttribute("cycle", cycleRepository.findById(cycleId).get());
		return "admin/reports/bar-chart-question-avg-all";
	}

	@GetMapping("/admin/reports/feedback/values/barchart/data/{cycleId}")
	@ResponseBody
	public BarChartAverageValuesPerQuestionData<AverageValuePerQuestionResult> valuesBarChart(
			@PathVariable("cycleId") Integer cycleId) {

		List<AverageValuePerQuestionResult> results = reportPerTeamAnswerRepository
				.averagePerQuestion(cycleId);

		return new BarChartAverageValuesPerQuestionData<AverageValuePerQuestionResult>(results,avgQuestion -> {
			return new GraphicData() {
				
				@Override
				public BigDecimal getValue() {
					return new BigDecimal(avgQuestion.getValue().doubleValue());
				}
				
				@Override
				public String getLabel() {
					return avgQuestion.getQuestion().getStatement();
				}
			};
		});
	}

	@GetMapping("/admin/reports/feedback/values/barchart/search/team/form")
	public String valuesBarChartPerTeamSearchForm(Model model, SearchValuesPerTeamForm form) {
		model.addAttribute("questionList",
				questions.findByCycleIdOrderByDueDateAsc(form.getCycleId()));
		return "admin/reports/bar-chart-question-avg-team";
	}

	@GetMapping("/admin/reports/feedback/values/barchart/search/team")
	public String valuesBarChartPerTeamSearch(Model model, @Valid SearchValuesPerTeamForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			return valuesBarChartPerTeamSearchForm(model, form);
		}

		model.addAttribute("dataUrl",
				"/admin/reports/feedback/values/barchart/search/team/data?cycleId="
						+ form.getCycleId() + "&questionId=" + form.getQuestionId());

		return valuesBarChartPerTeamSearchForm(model, form);
	}

	@GetMapping("/admin/reports/feedback/values/barchart/search/team/data")
	@ResponseBody
	public BarChartAverageValuesPerQuestionData<AverageValuePerTeamnResult> valuesBarChartPerTeamSearchData(
			@Valid SearchValuesPerTeamForm form) {

		List<AverageValuePerTeamnResult> results = reportPerTeamAnswerRepository
				.averagePerTeam(form.getCycleId(),form.getQuestionId());

		return new BarChartAverageValuesPerQuestionData<AverageValuePerTeamnResult>(results,avgValue ->  {
			return new GraphicData() {
				
				@Override
				public BigDecimal getValue() {
					return new BigDecimal(avgValue.getValue().doubleValue());
				}
				
				@Override
				public String getLabel() {
					return avgValue.getTeam().getName();
				}
			};
		});
	}
}
