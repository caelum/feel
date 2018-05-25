package br.com.caelum.feel.security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Transactional
public class GenerateSecurityConfigurationController {

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping("/magic/generate/roles")
	@ResponseBody
	public String generateRoles() {
		entityManager.persist(Role.PEOPLE);
		entityManager.persist(Role.READER);
		return "Roles geradas. Por favor, não execute essa url novamente.";
	}
}
