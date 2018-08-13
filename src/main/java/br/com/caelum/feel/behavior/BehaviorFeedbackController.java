package br.com.caelum.feel.behavior;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BehaviorFeedbackController {

	@Autowired
	private BehaviorFeedbackRepository behaviorFeedbackRepository;

	@GetMapping("/behavior/feedback/anonimous/form")
	public String form(Model model, NewBehaviorFeedbackForm form) {
		return "complains/new-form";
	}

	@PostMapping("/behavior/feedback/anonimous")
	public String save(Model model, @Valid NewBehaviorFeedbackForm form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return form(model, form);
		}

		BehaviorFeedback newFeedback = form.toBehaviorFeedback();
		behaviorFeedbackRepository.save(newFeedback);

		redirectAttributes.addFlashAttribute("msg",
				"Seu feedback foi registrado com sucesso. "
						+ " Para que você siga essa conversa, copie o link http://people.caelum.com.br/behavior/anonimous/timeline/"
						+ newFeedback.getHash()+". Acesse esse endereço dentro das próximas 48 horas e a gente já vai ter um retorno inicial para você. ");
		
		return "redirect:/behavior/feedback/anonimous/form";
	}
}
