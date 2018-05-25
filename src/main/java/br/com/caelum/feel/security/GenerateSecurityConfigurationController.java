package br.com.caelum.feel.security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Transactional
public class GenerateSecurityConfigurationController {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private RoleDao roleDao;

	@RequestMapping("/magic/generate/roles")
	@ResponseBody
	public String generateRoles() {
		entityManager.persist(Role.PEOPLE);
		entityManager.persist(Role.READER);
		return "Roles geradas. Por favor, não execute essa url novamente.";
	}
	
	@GetMapping("/magic/generate/user")
	@ResponseBody
	public String generatePeopleUsers(String email,String password) {
		SystemUser user = new SystemUser(email, email, Password.buildWithRawText(password), roleDao.findByName(Role.PEOPLE.getName()));
		entityManager.persist(user);
		return "usuario gerado com uscesso";
	}
}
