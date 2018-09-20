package br.com.caelum.feel.feedback.reports.application.views;

import java.math.BigDecimal;
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

	public String decideColorPercent(BigDecimal value) {
		return decideColorValue(
				value.divide(PROPORTIONAL_DIVISOR, value.scale(), RoundingMode.HALF_EVEN));

	}
}
