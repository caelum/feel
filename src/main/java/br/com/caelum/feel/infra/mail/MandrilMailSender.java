package br.com.caelum.feel.infra.mail;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
public class MandrilMailSender {

    private final Logger log = LoggerFactory.getLogger(MandrilMailSender.class);

    @Value("${mandrill.api.key}")
    private String apiKey;

    public void send(String subject, String text, String from, String... to) {
        var mandrillApi = new MandrillApi(apiKey);

        var recipients = Arrays.asList(to)
                .stream()
                .map(email -> {
                    var recipient = new Recipient();
                    recipient.setEmail(email);
                    return recipient;
                }).collect(toList());

        var mandrillMessage = new MandrillMessage();
        mandrillMessage.setSubject(subject);
        mandrillMessage.setHtml(text);
        mandrillMessage.setTo(recipients);
        mandrillMessage.setFromEmail(from);
        try {
            MandrillMessageStatus[] messagesStatus = mandrillApi.messages().send(mandrillMessage, true);
            stream(messagesStatus)
                    .forEach(messageStatus -> log.info("mail status: " + messageStatus.getStatus() +
                            " - rejected reason: " + messageStatus.getRejectReason()));
        } catch (MandrillApiError e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
