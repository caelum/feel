package br.com.caelum.feel.infra;

public class MandrillMessageDTO {

	/*
	 * era melhor, obviamente, pegar do applicaction properties. Não fiz, pq estou as 06:30 implementando e não to com saco.
	 */
	private String key = "ofwez_5a5IwoQfEpV8bgHQ";
	private MandrillMailDTO message;

	public MandrillMessageDTO(MandrillMailDTO mail) {
		this.message = mail;
	}

	public String getKey() {
		return key;
	}

	public MandrillMailDTO getMessage() {
		return message;
	}

}
