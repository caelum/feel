package br.com.caelum.feel.infra;

public class MandrilMessageToDTO {

	private String email;
	private String name;
	private String type = "to";

	public MandrilMessageToDTO(String email) {
		this.email = email;
		this.name = email;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

}
