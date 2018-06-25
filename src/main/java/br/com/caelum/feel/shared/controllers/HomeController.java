package br.com.caelum.feel.shared.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String methodName() {
		return "redirect:/admin/cycles";
	}
}
