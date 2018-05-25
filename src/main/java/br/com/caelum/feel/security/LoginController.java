package br.com.caelum.feel.security;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public ResponseEntity<String> login(String username, String password,HttpServletResponse servletResponse) throws IOException {
		tryToRegisterUserAction.execute(username, password);
		return SystemLogin.execute(username,password);
	}
}
