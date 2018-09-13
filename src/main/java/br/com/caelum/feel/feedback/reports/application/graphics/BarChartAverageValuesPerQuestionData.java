package br.com.caelum.feel.feedback.reports.application.graphics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.feel.feedback.questions.domain.models.AverageValuePerQuestionResult;

public class BarChartAverageValuesPerQuestionData {
	
	private List<String[]> labels = new ArrayList<>();
	private List<BigDecimal> values = new ArrayList<>();
	private List<String> colors = new ArrayList<>();
	private String[] colorsOptions = {"red","blue","yellow","green","gray","purple","black","orange"};

	public BarChartAverageValuesPerQuestionData(List<AverageValuePerQuestionResult> results) {
		
		for(int i=0;i<results.size();i++) {
			AverageValuePerQuestionResult item = results.get(i);
			labels.add(splitLabelPerLineToShowCorrect(item));
			values.add(new BigDecimal(item.getValue().doubleValue()));
			colors.add(colorsOptions[i]);
		}
	}

	private String[] splitLabelPerLineToShowCorrect(AverageValuePerQuestionResult item) {
		return item.getQuestion().getStatement().split(" ");
	}
	
	public List<String[]> getLabels() {
		return labels;
	}
	
	public List<BigDecimal> getValues() {
		return values;
	}
	
	public List<String> getColors() {
		return colors;
	}

}
