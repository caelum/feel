package br.com.caelum.feel.behavior;

public enum BehaviorFeedbackType {
	INTERNAL("Interna"),EXTERNAL("Externa");
	
	private String label;

	private BehaviorFeedbackType(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	

}
