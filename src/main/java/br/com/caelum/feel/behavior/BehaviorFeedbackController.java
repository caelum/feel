package br.com.caelum.feel.behavior;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BehaviorFeedbackController {

	
	@GetMapping("/behavior/feedback/anonimous/form")
	public String form(Model model, NewBehaviorFeedbackForm form) {
		return "complains/new-form";
	}
	
	@PostMapping("/behavior/feedback/anonimous")
	public String methodName(Model model, @Valid NewBehaviorFeedbackForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return form(model, form);
		}

		//business rule

		redirectAttributes.addFlashAttribute("msg", "Seu feedback foi registrado com sucesso. Vamos entrar em contato");
		return "redirect:/behavior/feedback/anonimous/form";
	}
}
