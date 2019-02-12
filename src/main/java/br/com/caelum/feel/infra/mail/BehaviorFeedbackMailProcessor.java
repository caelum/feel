package br.com.caelum.feel.infra.mail;

import br.com.caelum.feel.behavior.BehaviorFeedback;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BehaviorFeedbackMailProcessor {

    private final TemplateEngine templateEngine;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ServletContext servletContext;

    public BehaviorFeedbackMailProcessor(TemplateEngine templateEngine, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
        this.templateEngine = templateEngine;
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;
    }

    public String getHTMLFromThymeleaf(BehaviorFeedback behaviorFeedback) {
        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("feedbackHash", behaviorFeedback.getHash());
        return templateEngine.process("email/new-feedback-behavior.html", context);
    }
}
