package br.com.caelum.feel.feedback.companyteams.application.forms;

import javax.validation.constraints.NotBlank;

import br.com.caelum.feel.feedback.companyteams.domain.models.Cycle;

public class CycleForm {

	@NotBlank
	private String name;
	@NotBlank
	private String welcomeText;
	@NotBlank
	private String closingText;
	private boolean isNew = true;
	
	

	/**
	 * Indica que aquele formulário está populado com dados de um formulário
	 */
	public void dirty() {
		this.isNew = false;
	}	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWelcomeText() {
		return welcomeText;
	}

	public void setWelcomeText(String welcomeText) {
		this.welcomeText = welcomeText;
	}

	public String getClosingText() {
		return closingText;
	}

	public void setClosingText(String closingText) {
		this.closingText = closingText;
	}

	public Cycle build() {
		return new Cycle(name,welcomeText,closingText);
	}
	
	public void fill(Cycle cycle) {
		if(this.isNew) {
			this.name = cycle.getName();
			this.welcomeText = cycle.getWelcomeText();
			this.closingText = cycle.getClosingText();
		}
	}	

}
