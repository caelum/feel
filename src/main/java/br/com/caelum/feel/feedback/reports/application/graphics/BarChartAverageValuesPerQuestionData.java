package br.com.caelum.feel.feedback.reports.application.graphics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BarChartAverageValuesPerQuestionData {
	
	private List<String> labels = new ArrayList<>();
	private List<BigDecimal> values = new ArrayList<>();

	public BarChartAverageValuesPerQuestionData(List<AverageValuePerQuestionResult> results) {
		results.forEach(item -> {
			labels.add(item.getQuestion().getStatement());
			values.add(new BigDecimal(item.getValue().doubleValue()));
		});
	}
	
	public List<String> getLabels() {
		return labels;
	}
	
	public List<BigDecimal> getValues() {
		return values;
	}

}
