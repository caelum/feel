package br.com.caelum.feel.infra;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MandrillMailer {

	@Async
	public void send(String subject, String rawText, String from, String fromName,
			List<String> emailsToSend) {

		MandrillMailDTO mail = new MandrillMailDTO(subject, rawText, from, fromName, emailsToSend);
		MandrillMessageDTO message = new MandrillMessageDTO(mail);
		new RestTemplate().postForEntity("https://mandrillapp.com/api/1.0/messages/send.json",
				message, String.class);
	}

}
