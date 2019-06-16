package br.com.caelum.feel.behavior;

import java.util.List;

public enum BehaviorFeedbackType {
	INTERNAL("Interna",List.of("luisa.aguirra@caelum.com.br","fernanda.sindeaux@caelum.com.br")),EXTERNAL("Externa",List.of("gustavo.fujimoto@caelum.com.br","claudio.abbate@caelum.com.br"));
	
	private String label;
	private List<String> contactEmails;

	private BehaviorFeedbackType(String label,List<String> contactEmails) {
		this.label = label;
		this.contactEmails = contactEmails;
	}
	
	public String getLabel() {
		return label;
	}
	
	public List<String> getContactEmails() {
		return contactEmails;
	}
	

}
