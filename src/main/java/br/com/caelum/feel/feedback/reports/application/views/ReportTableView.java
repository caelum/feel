package br.com.caelum.feel.feedback.reports.application.views;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class ReportTableView {

	private static final BigDecimal PROPORTIONAL_DIVISOR = new BigDecimal(20);

	public String decideColorValue(BigDecimal value) {
		double rounded = value.doubleValue();

		if (rounded >= 5) {
			return "report-positive-1";
		} else {
			if (rounded >= 4.5) {
				return "report-positive-2";
			} else {
				if (rounded >= 4) {
					return "report-positive-3";
				} else {
					if (rounded >= 3) {
						return "report-warning";
					} else {
						return "report-danger";
					}
				}
			}
		}

	}

	/**
	 * 
	 * @param percentValue o valor percentual. Ex: 0.03
	 * @return
	 */
	public String decideColorPercent(BigDecimal percentValue) {
		MathContext mathContext = new MathContext(percentValue.scale(), RoundingMode.HALF_EVEN);
		return decideColorValue(percentValue.multiply(new BigDecimal(100), mathContext)
				.divide(PROPORTIONAL_DIVISOR, mathContext));

	}
}
