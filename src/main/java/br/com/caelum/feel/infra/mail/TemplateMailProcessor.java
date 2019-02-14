package br.com.caelum.feel.infra.mail;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static java.lang.String.format;

@Component
public class TemplateMailProcessor {

    private final TemplateEngine templateEngine;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ServletContext servletContext;

    public TemplateMailProcessor(TemplateEngine templateEngine, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
        this.templateEngine = templateEngine;
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;
    }

    public String getHTMLFromThymeleaf(String templateName, Map<String, Object> templateVariables) {
        WebContext context = new WebContext(request, response, servletContext);
        templateVariables.forEach(context::setVariable);
        return templateEngine.process(format("email/%s.html", templateName), context);
    }
}
