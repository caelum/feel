package br.com.caelum.feel.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
			try {
				RequestEntity<?> cwebEntity = CwebLoginRequestEntity.configure(login, password);
				ResponseEntity<String> response = rest.exchange(cwebEntity, String.class);
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
