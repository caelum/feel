package br.com.caelum.feel.feedback.reports.application.graphics;

import java.math.BigDecimal;

import br.com.caelum.feel.feedback.questions.domain.models.HealthColor;

public interface GraphicData {

	BigDecimal getValue();
	
	String getLabel();

	HealthColor getHealthColor();
}
