package br.com.caelum.feel.behavior;

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
		mailer.send("Nova denunca registrada","O conteúdo da denuncia é: "+newFeedback.getComment()+". <br/> Você pode conferir acessando o link "+newFeedback.getAccessLink(),"sistemarh@caelum.com.br","Sistema RH",newFeedback.getFeedbackType().getContactEmails());
	}

	
}
