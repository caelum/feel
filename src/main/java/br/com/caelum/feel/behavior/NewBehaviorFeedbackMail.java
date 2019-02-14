package br.com.caelum.feel.behavior;

import br.com.caelum.feel.infra.mail.TemplateMailProcessor;
import br.com.caelum.feel.security.SystemUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

import static br.com.caelum.feel.security.Role.GOOD_BEHAVIOR;

@Service
public class NewBehaviorFeedbackMail {

    private final Logger log = LoggerFactory.getLogger(NewBehaviorFeedbackMail.class);

    private final SystemUserDao systemUserDao;
    private final MailSender mailSender;
    private final TemplateMailProcessor templateMailProcessor;

    public NewBehaviorFeedbackMail(SystemUserDao systemUserDao, MailSender mailSender, TemplateMailProcessor templateMailProcessor) {
        this.systemUserDao = systemUserDao;
        this.mailSender = mailSender;
        this.templateMailProcessor = templateMailProcessor;
    }

    public void sendAsyncMail(BehaviorFeedback behaviorFeedback) {

        var message = new SimpleMailMessage();
        message.setSubject("Novo feedback de comportamento");
        message.setText(getHtmlFromTemplate(behaviorFeedback));

        setRecipientsTo(message);
        message.setFrom("feel@caelum.com.br");

        mailSender.send(message);
    }

    private String getHtmlFromTemplate(BehaviorFeedback behaviorFeedback) {
        var feedbackHash = behaviorFeedback.getHash();
        return templateMailProcessor.getHTMLFromThymeleaf("new-feedback-behavior", Map.of("feedbackHash", feedbackHash));
    }

    private void setRecipientsTo(SimpleMailMessage message) {
        systemUserDao.findByRole(GOOD_BEHAVIOR.getName())
                .stream()
                .peek(systemUser -> log.info("email: " + systemUser.getEmail()))
                .forEach(systemUser -> message.setTo(systemUser.getEmail()));
    }
}
