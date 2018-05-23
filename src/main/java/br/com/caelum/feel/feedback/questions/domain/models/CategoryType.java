package br.com.caelum.feel.feedback.questions.domain.models;

public enum CategoryType {

	EQUILIBRIO_PESSOAL_PROFISSIONAL("Equilibrio pessoal e profissional"), 
	DESAFIO_SATISFACAO_PROFISSIONAL("Desafio e satisfação profissional"), 
	IDENTIFICACAO_EMPRESA("Identificação com a empresa"),
	RECONHECIMENTO("Reconhecimento"),
	RELACIONAMENTO("Relacionamento"),
	LIDERANCA("Liderança");
	
	private String label;

	private CategoryType(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
