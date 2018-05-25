package br.com.caelum.feel.security;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.feel.infra.UrlBuilder;

@Controller
public class LoginController {

	@Autowired
	private TryToRegisterUserAction tryToRegisterUserAction;

	@GetMapping(value = "/login/form")
	public String loginForm() {
		System.out.println();
		return "user/login";
	}

	@PostMapping(value = "/custom/login")
	@ResponseBody
	public void login(String username, String password,HttpServletResponse servletResponse) throws IOException {
		tryToRegisterUserAction.execute(username, password);
		//procede com login normal, pega o retorno e esreve na response.
		
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("username", username);
		params.add("password", password);

		RequestEntity<LinkedMultiValueMap<String, String>> requestEntity = RequestEntity
			.post(URI.create(UrlBuilder.buildForLocalhost("/login")))
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.body(params);
		
		ResponseEntity<String> response = new RestTemplate().exchange(requestEntity, String.class);
		
		System.out.println(response.getHeaders().getLocation());
			
		servletResponse.sendRedirect(response.getHeaders().getLocation().toString());
	}
}
