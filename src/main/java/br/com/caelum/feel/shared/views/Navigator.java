package br.com.caelum.feel.shared.views;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class Navigator {
	
	@Autowired
	private HttpServletRequest request;

	public String activeLinkClass(String urlPart) {
		return request.getRequestURI().contains(urlPart) ? "active" : "";
	}
}
