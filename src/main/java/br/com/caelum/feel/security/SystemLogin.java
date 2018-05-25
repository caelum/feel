package br.com.caelum.feel.security;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.feel.infra.UrlBuilder;

public class SystemLogin {

	public static ResponseEntity<String> execute(String username, String password) {
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("username", username);
		params.add("password", password);

		RequestEntity<LinkedMultiValueMap<String, String>> requestEntity = RequestEntity
			.post(URI.create(UrlBuilder.buildForLocalhost("/login")))
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.body(params);
		
		return new RestTemplate().exchange(requestEntity, String.class);		
	}

	
}
