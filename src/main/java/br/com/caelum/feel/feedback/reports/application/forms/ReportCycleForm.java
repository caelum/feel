package br.com.caelum.feel.feedback.reports.application.forms;

import javax.validation.constraints.NotNull;

public class ReportCycleForm {

	@NotNull
	private Integer cycleId;

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

}
