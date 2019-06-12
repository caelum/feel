package br.com.caelum.feel.infra;

import java.util.List;
import java.util.stream.Collectors;

public class MandrillMailDTO {

	private List<MandrilMessageToDTO> to;
	private String subject;
	private String text;
	private String from_email;
	private String from_name;
	private String html;

	public MandrillMailDTO(String subject, String rawText, String from, String fromName,
			List<String> emailsToSend) {
		this.subject = subject;
		this.text = rawText;
		this.html = rawText;
		this.from_email = from;
		this.from_name = fromName;
		this.to = emailsToSend.stream().map(MandrilMessageToDTO::new).collect(Collectors.toList());
	}
	
	public String getHtml() {
		return html;
	}

	public List<MandrilMessageToDTO> getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}

	public String getText() {
		return text;
	}

	public String getFrom_email() {
		return from_email;
	}

	public String getFrom_name() {
		return from_name;
	}

}
