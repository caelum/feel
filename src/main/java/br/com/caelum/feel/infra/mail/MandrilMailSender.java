package br.com.caelum.feel.infra.mail;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
public class MandrilMailSender implements MailSender {

    private final Logger log = LoggerFactory.getLogger(MandrilMailSender.class);

    @Value("${mandrill.api.key}")
    private String apiKey;

    @Override
    public void send(SimpleMailMessage message) throws MailException {
        var mandrillApi = new MandrillApi(apiKey);

        var recipients = Arrays.asList(message.getTo())
                .stream()
                .map(email -> {
                    var recipient = new Recipient();
                    recipient.setEmail(email);
                    return recipient;
                }).collect(toList());

        var mandrillMessage = new MandrillMessage();
        mandrillMessage.setSubject(message.getSubject());
        mandrillMessage.setHtml(message.getText());
        mandrillMessage.setTo(recipients);
        mandrillMessage.setFromEmail(message.getFrom());
        try {
            MandrillMessageStatus[] messagesStatus = mandrillApi.messages().send(mandrillMessage, true);
            stream(messagesStatus)
                    .forEach(messageStatus -> log.info("mail status: " + messageStatus.getStatus() +
                            " - rejected reason: " + messageStatus.getRejectReason()));
        } catch (MandrillApiError e) {
            log.error("error: " + e.getMandrillErrorAsJson());
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void send(SimpleMailMessage... simpleMailMessages) throws MailException {
        stream(simpleMailMessages).forEach(this::send);
    }
}
