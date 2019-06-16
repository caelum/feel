package br.com.caelum.feel.behavior;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.feel.infra.MandrillMailer;

@Service
public class NewBehaviorReplyService {

	@Autowired
	private BehaviorReplyRepository behaviorReplyRepository;
	@Autowired
	private MandrillMailer mailer;

	public void execute(BehaviorReply reply) {
		behaviorReplyRepository.save(reply);
		mailer.send("Nova resposta sobre uma denuncia", reply.getComment(), "sistemarh@caelum.com.br", "Sistema RH", reply.getContactEmails());
	}
}
