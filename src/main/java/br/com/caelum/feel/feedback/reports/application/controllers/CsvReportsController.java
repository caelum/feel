package br.com.caelum.feel.feedback.reports.application.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;
import br.com.caelum.feel.feedback.companyteams.domain.repositories.Teams;
import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;
import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.models.ReportPerTeamAnswer;
import br.com.caelum.feel.feedback.questions.domain.respositories.FeedbackAnswerRepository;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;
import br.com.caelum.feel.feedback.questions.domain.respositories.ReportPerTeamAnswerRepository;
import br.com.caelum.feel.feedback.reports.application.csv.CsvAnserwsDTO;
import br.com.caelum.feel.feedback.reports.application.csv.CsvPercentAnswersDTO;
import br.com.caelum.feel.feedback.reports.application.csv.CsvWriter;
import br.com.caelum.feel.feedback.reports.application.forms.ReportCycleForm;
import br.com.caelum.feel.feedback.reports.application.views.ReportPerTeamAnswerTable;

@Controller
public class CsvReportsController {

	@Autowired
	private CycleRepository cycleRepository;
	@Autowired
	private FeedbackAnswerRepository feedbackAnswerRepository;
	@Autowired
	private CsvWriter csvWriter;
	@Autowired
	private ReportPerTeamAnswerRepository reportPerTeamAnswerRepository;
	@Autowired
	private Questions questionRepository;
	@Autowired
	private Teams teamRepository;	

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
	
	@GetMapping("/reports/feedback/csv/percentAnswers/form")
	public String percentAnswersForm(Model model, ReportCycleForm form) {
		model.addAttribute("cycleList", cycleRepository.findAll());
		return "admin/reports/csv-questions-percent-answers";
	}
	
	@PostMapping(value = "/reports/feedback/csv/percentAnswers", produces = "text/x-csv")
	@ResponseBody
	public String generateCsvPercenAnswers(Model model, @Valid ReportCycleForm form) {
		
		//TODO refatorar essa bagaça, ficou tenso isso aqui. 
		
		List<ReportPerTeamAnswer> answers = reportPerTeamAnswerRepository.listCurrentView(form.getCycleId());
		ReportPerTeamAnswerTable reportPerTeamAnswerTable = new ReportPerTeamAnswerTable(answers);
		List<Question> questions = questionRepository.findByCycleIdOrderByDueDateAsc(form.getCycleId(), PageRequest.of(0, 6));
		List<CompanyTeam> teams = teamRepository.findAll();
		
		List<CsvPercentAnswersDTO> lines = new ArrayList<>();

		for (CompanyTeam team : teams) {			
			for (Question question : questions) {
				BigDecimal percentCount = reportPerTeamAnswerTable.percentCount(team, question);
				lines.add(new CsvPercentAnswersDTO(team.getName(),percentCount.setScale(2, RoundingMode.HALF_EVEN)));
			}
		}
		
		return csvWriter.write(lines, CsvPercentAnswersDTO.header(), CsvPercentAnswersDTO.processors());
	}
}
