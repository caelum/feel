package br.com.caelum.feel.feedback.reports.application.csv;

import java.math.BigDecimal;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class CsvPercentAnswersDTO {

	private String teamName;
	private BigDecimal percent;

	public CsvPercentAnswersDTO(String teamName, BigDecimal percent) {
		this.teamName = teamName;
		this.percent = percent;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public BigDecimal getPercent() {
		return percent;
	}
	
	public static String[] header() {
		return new String[] {"teamName","percent"};
	}

	public static CellProcessor[] processors() {
		return new CellProcessor[] { new NotNull(), new NotNull()};
	}	

}
