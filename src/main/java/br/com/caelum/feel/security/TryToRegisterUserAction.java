package br.com.caelum.feel.security;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class TryToRegisterUserAction {

	@Autowired
	private SystemUserDao systemUserDao;
	@Autowired
	private RoleDao roleDao;

	public Optional<SystemUser> execute(String login, String password) {
		Optional<SystemUser> user = systemUserDao.findByEmail(login);

		if (user.isPresent()) {
			return user;
		}

		else {
			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
			body.add("login", login);
			body.add("senha", password);

			RequestEntity<?> requestEntity = new RequestEntity<>(body, headers, HttpMethod.POST, URI
					.create("https://caelumweb.caelum.com.br/caelumweb/login/notificador/asnjdofasjndfoi"));

			try {
				ResponseEntity<String> response = rest.exchange(requestEntity, String.class);
				if (response.getStatusCode().is2xxSuccessful()) {
					SystemUser newUser = systemUserDao.save(new SystemUser(login + "_usuariocweb",
							login, Password.buildWithRawText(password), roleDao.findByName(Role.READER.getName())));
					return Optional.of(newUser);
				}

				return Optional.empty();
			} catch (RestClientException e) {
				return Optional.empty();
			}
		}

	}
}
