package br.com.caelum.feel.shared.views;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.springframework.stereotype.Component;

@Component
public class NumbersFormatter {

	public String percent(BigDecimal value) {
		NumberFormat defaultFormat = NumberFormat.getPercentInstance();
		defaultFormat.setMinimumFractionDigits(2);
		return defaultFormat.format(value);
	}
}
