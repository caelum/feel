package br.com.caelum.feel.behavior;

import br.com.caelum.feel.infra.mail.BehaviorFeedbackMailProcessor;
import br.com.caelum.feel.security.SystemUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static br.com.caelum.feel.security.Role.GOOD_BEHAVIOR;

@Service
public class NewBehaviorFeedbackMail {

    private final Logger log = LoggerFactory.getLogger(NewBehaviorFeedbackMail.class);

    private final SystemUserDao systemUserDao;
    private final MailSender mailSender;
    private final BehaviorFeedbackMailProcessor behaviorFeedbackMailProcessor;

    public NewBehaviorFeedbackMail(SystemUserDao systemUserDao, MailSender mailSender, BehaviorFeedbackMailProcessor behaviorFeedbackMailProcessor) {
        this.systemUserDao = systemUserDao;
        this.mailSender = mailSender;
        this.behaviorFeedbackMailProcessor = behaviorFeedbackMailProcessor;
    }

    public void sendMail(BehaviorFeedback behaviorFeedback) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Novo feedback de comportamento");
        message.setText(behaviorFeedbackMailProcessor.getHTMLFromThymeleaf(behaviorFeedback));

        setRecipientsTo(message);

        message.setFrom("feel@caelum.com.br");

        mailSender.send(message);
    }

    private void setRecipientsTo(SimpleMailMessage message) {
        systemUserDao.findByRole(GOOD_BEHAVIOR.getName())
                .stream()
                .peek(systemUser -> log.info("email: " + systemUser.getEmail()))
                .forEach(systemUser -> message.setTo(systemUser.getEmail()));
    }

}
