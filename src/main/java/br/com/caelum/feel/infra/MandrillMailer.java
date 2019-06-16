package br.com.caelum.feel.infra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MandrillMailer {
	
	@Autowired
	private Environment env;

	@Async
	public void send(String subject, String rawText, String from, String fromName,
			List<String> emailsToSend) {

		
		//aqui poderiam ser duas clasess, funcoes de producao e tal... mas desse jeito ta de boa por enquanto.
		if(env.acceptsProfiles("production")) {		
			MandrillMailDTO mail = new MandrillMailDTO(subject, rawText, from, fromName, emailsToSend);
			MandrillMessageDTO message = new MandrillMessageDTO(mail);
			new RestTemplate().postForEntity("https://mandrillapp.com/api/1.0/messages/send.json",
					message, String.class);
		} else {
			System.out.println("Enviando emails para "+emailsToSend);
			System.out.println("Conteúdo:"+rawText);
		}
	}

}
