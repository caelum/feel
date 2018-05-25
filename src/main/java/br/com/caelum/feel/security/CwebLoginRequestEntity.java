package br.com.caelum.feel.security;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class CwebLoginRequestEntity {

	public static RequestEntity<?> configure(String login, String password) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("login", login);
		body.add("senha", password);

		return new RequestEntity<>(body, headers, HttpMethod.POST, URI
				.create("https://caelumweb.caelum.com.br/caelumweb/login/notificador/asnjdofasjndfoi"));		
	}
}
