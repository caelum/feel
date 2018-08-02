package br.com.caelum.feel.feedback.reports.application.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;
import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.feedback.questions.domain.respositories.FeedbackAnswerRepository;
import br.com.caelum.feel.feedback.reports.application.csv.CsvAnserwsDTO;
import br.com.caelum.feel.feedback.reports.application.csv.CsvWriter;
import br.com.caelum.feel.feedback.reports.application.forms.ReportCycleForm;

@Controller
public class CsvReportsController {

	@Autowired
	private CycleRepository cycleRepository;
	@Autowired
	private FeedbackAnswerRepository feedbackAnswerRepository;
	@Autowired
	private CsvWriter csvWriter;

	@GetMapping("/reports/feedback/csv/allAnswers/form")
	public String allAnswersForm(Model model, ReportCycleForm form) {
		model.addAttribute("cycleList", cycleRepository.findAll());
		return "admin/reports/csv-questions-cycle";
	}

	@PostMapping(value = "/reports/feedback/csv/allAnswers", produces = "text/x-csv")
	@ResponseBody
	public String generateCsvAllAnswers(Model model, @Valid ReportCycleForm form) {

		List<FeedbackAnswer> answers = feedbackAnswerRepository
				.findByQuestionCycleId(form.getCycleId());

		List<CsvAnserwsDTO> lines = CsvAnserwsDTO.from(answers);

		return csvWriter.write(lines, CsvAnserwsDTO.header(), CsvAnserwsDTO.processors());

	}
}
