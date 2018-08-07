package br.com.caelum.feel.feedback.reports.application.csv;

import java.math.BigDecimal;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

import br.com.caelum.feel.shared.views.NumbersFormatter;

public class CsvPercentAnswersDTO {

	private String teamName;
	private String percent;
	private NumbersFormatter numbersFormatter = new NumbersFormatter();
	private String statement;

	public CsvPercentAnswersDTO(String statement, String teamName, BigDecimal percent) {
		this.statement = statement;
		this.teamName = teamName;
		this.percent = numbersFormatter.percent(percent, 4);
	}
	
	public String getStatement() {
		return statement;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public String getPercent() {
		return percent;
	}
	
	public static String[] header() {
		return new String[] {"statement","teamName","percent"};
	}

	public static CellProcessor[] processors() {
		return new CellProcessor[] { new NotNull(), new NotNull(), new NotNull()};
	}	

}
