package br.com.caelum.feel.security;

import java.util.Optional;

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
	@Autowired
	private SystemUserDao systemUserDao;

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
	
	@GetMapping("/magic/user/add/role")
	@ResponseBody
	@Transactional
	public String addRoleToLogin(String login,String roleName) {
		Optional<SystemUser> user = systemUserDao.findByEmail(login);
		if(!user.isPresent()) {
			throw new RuntimeException("acho que você passou um usuário que não existe...");
		}
		
		Role role = roleDao.findByName(roleName);
		user.get().addRole(role);
		
			
		return "role adicionada com sucesso";
	}
}
