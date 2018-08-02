package br.com.caelum.feel.feedback.reports.application.csv;

import java.util.List;
import java.util.stream.Collectors;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;

public class CsvAnserwsDTO {

	private String questionTitle;
	private int value;
	private String comment;
	private String teamName;

	public CsvAnserwsDTO(String questionTitle, int value, String comment, String teamName) {
		super();
		this.questionTitle = questionTitle;
		this.value = value;
		this.comment = comment;
		this.teamName = teamName;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public int getValue() {
		return value;
	}

	public String getComment() {
		return comment;
	}

	public String getTeamName() {
		return teamName;
	}

	public static List<CsvAnserwsDTO> from(List<FeedbackAnswer> answers) {
		return answers.stream()
				.map(answer -> new CsvAnserwsDTO(answer.getQuestion().getStatement(),
						answer.getValue(), answer.getComments(), answer.getTeam().getName()))
				.collect(Collectors.toList());
	}
	
	public static String[] header() {
		return new String[] {"questionTitle","value","comment","teamName"};
	}

	public static CellProcessor[] processors() {
		return new CellProcessor[] { new NotNull(), new NotNull(),
				new NotNull(), new NotNull() };
	}

}
