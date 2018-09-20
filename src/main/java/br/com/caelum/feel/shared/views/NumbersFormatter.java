package br.com.caelum.feel.shared.views;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.springframework.stereotype.Component;

@Component
public class NumbersFormatter {

	public String percent(BigDecimal value) {
		return percent(value,2);
	}
	
	public String percent(BigDecimal value,int digits) {
		NumberFormat defaultFormat = NumberFormat.getPercentInstance();
		defaultFormat.setMinimumFractionDigits(digits);
		return defaultFormat.format(value);
	}
}
