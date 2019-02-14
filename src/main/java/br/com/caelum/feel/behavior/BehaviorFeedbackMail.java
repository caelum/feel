package br.com.caelum.feel.behavior;

import br.com.caelum.feel.infra.mail.MandrilMailSender;
import br.com.caelum.feel.infra.mail.TemplateMailProcessor;
import br.com.caelum.feel.security.SystemUser;
import br.com.caelum.feel.security.SystemUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static br.com.caelum.feel.security.Role.GOOD_BEHAVIOR;
import static java.util.stream.Collectors.toList;

@Service
public class BehaviorFeedbackMail {

    private final Logger log = LoggerFactory.getLogger(BehaviorFeedbackMail.class);

    private final SystemUserDao systemUserDao;
    private final MandrilMailSender mailSender;
    private final TemplateMailProcessor templateMailProcessor;

    public BehaviorFeedbackMail(SystemUserDao systemUserDao, MandrilMailSender mailSender, TemplateMailProcessor templateMailProcessor) {
        this.systemUserDao = systemUserDao;
        this.mailSender = mailSender;
        this.templateMailProcessor = templateMailProcessor;
    }

    public void sendAsyncNewFeedbackMail(BehaviorFeedback behaviorFeedback) {

        var subject = "Novo feedback de comportamento";
        var text = getHtmlFromTemplate(behaviorFeedback);
        var from = "feel@caelum.com.br";

        mailSender.send(subject, text, from, getRecipients().toArray(new String[]{}));
    }

    public void sendAsyncNewReplyMail(BehaviorReply behaviorReply) {

        var subject = "Nova resposta em um feedback de comportamento";
        var text = getHtmlFromTemplate(behaviorReply.getRoot());
        var from = "feel@caelum.com.br";

        mailSender.send(subject, text, from, getRecipients().toArray(new String[]{}));
    }

    private String getHtmlFromTemplate(BehaviorFeedback behaviorFeedback) {
        var feedbackHash = behaviorFeedback.getHash();
        return templateMailProcessor.getHTMLFromThymeleaf("new-feedback-behavior", Map.of("feedbackHash", feedbackHash));
    }

    private List<String> getRecipients() {
        return systemUserDao.findByRole(GOOD_BEHAVIOR.getName())
                .stream()
                .peek(systemUser -> log.info("email: " + systemUser.getEmail()))
                .map(SystemUser::getEmail)
                .collect(toList());
    }
}
