package br.com.caelum.feel.behavior;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.feel.infra.MandrillMailer;

@Service
public class NewBehaviorFeedbackService {
	
	@Autowired
	private BehaviorFeedbackRepository behaviorFeedbackRepository;
	@Autowired
	private MandrillMailer mailer;
	

	public void execute(BehaviorFeedback newFeedback) {
		behaviorFeedbackRepository.save(newFeedback);
		List<String> emailsToSend = List.of("luisa.aguirra@caelumm.com.br","fernanda.sindeaux@caelum.com.br");
		//mailer.send("Nova denunca registrada","Você pode conferir a denuncia em "+newFeedback.getAccessLink(),"sistemarh@caelum.com.br","Sistema RH",emailsToSend);
	}

	
}
