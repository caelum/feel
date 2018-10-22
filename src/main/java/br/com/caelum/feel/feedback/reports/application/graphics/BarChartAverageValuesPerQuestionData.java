package br.com.caelum.feel.feedback.reports.application.graphics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BarChartAverageValuesPerQuestionData<T> {
	
	private List<String[]> labels = new ArrayList<>();
	private List<BigDecimal> values = new ArrayList<>();
	private List<String> colors = new ArrayList<>();
	private String[] colorsOptions = {"#f73232","#ffa016" ,"#fff002","#4bf84c","#3ec714","#95f442","pink","#ff9999","#ffb399","#ffcc99","#ffe699","#ffff99","#e6ff99","#ccff99","#99ffff","#9999ff","#b399ff","#cc99ff","#e699ff","#ff99ff","#ff99e6","#ff99cc","#ff99b3","#ff9999","#fff2e6","#4d2600","#000000"};

	public BarChartAverageValuesPerQuestionData(List<T> results,Function<T, GraphicData> mapper) {
		
		for(int i=0;i<results.size();i++) {
			T item = results.get(i);
			GraphicData graphicData = mapper.apply(item);
			labels.add(splitLabelPerLineToShowCorrect(graphicData.getLabel()));
			values.add(graphicData.getValue());
			colors.add(graphicData.getHealthColor().getHexa());
		}
	}

	private String[] splitLabelPerLineToShowCorrect(String label) {
		return label.split(" ");
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
