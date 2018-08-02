package br.com.caelum.feel.feedback.reports.application.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;
import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.feedback.questions.domain.respositories.FeedbackAnswerRepository;
import br.com.caelum.feel.feedback.reports.application.csv.CsvAnserwsDTO;
import br.com.caelum.feel.feedback.reports.application.forms.ReportCycleForm;

@Controller
public class CsvReportsController {

	@Autowired
	private CycleRepository cycleRepository;
	@Autowired
	private FeedbackAnswerRepository feedbackAnswerRepository;

	@GetMapping("/reports/feedback/csv/allAnswers/form")
	public String allAnswersForm(Model model, ReportCycleForm form) {
		model.addAttribute("cycleList", cycleRepository.findAll());
		return "admin/reports/csv-questions-cycle";
	}

	@PostMapping(value="/reports/feedback/csv/allAnswers",produces="text/x-csv")
	@ResponseBody
	public String generateCsvAllAnswers(Model model, @Valid ReportCycleForm form,
			BindingResult bindingResult) throws IOException {
		if (bindingResult.hasErrors()) {
			return allAnswersForm(model, form);
		}

		List<FeedbackAnswer> answers = feedbackAnswerRepository
				.findByQuestionCycleId(form.getCycleId());

		List<CsvAnserwsDTO> lines = CsvAnserwsDTO.from(answers);

		StringWriter writer = new StringWriter();
		try (ICsvBeanWriter beanWriter = new CsvBeanWriter(writer,
				CsvPreference.EXCEL_PREFERENCE)) {
			
			beanWriter.writeHeader(CsvAnserwsDTO.header());
			for (final CsvAnserwsDTO line : lines) {
				CellProcessor[] processors = new CellProcessor[] {new NotNull(),new NotNull(),new NotNull(),new NotNull()};				
				beanWriter.write(line, CsvAnserwsDTO.header(), processors);
			}

		}

		return writer.toString();
	}
}
