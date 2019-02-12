package br.com.caelum.feel.behavior;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BehaviorFeedbackController {

	private final BehaviorFeedbackRepository behaviorFeedbackRepository;

	private final NewBehaviorFeedbackService newBehaviorFeedback;

	public BehaviorFeedbackController(BehaviorFeedbackRepository behaviorFeedbackRepository, NewBehaviorFeedbackService newBehaviorFeedback) {
		this.behaviorFeedbackRepository = behaviorFeedbackRepository;
		this.newBehaviorFeedback = newBehaviorFeedback;
	}

	@GetMapping("/admin/behavior/feedbacks")
	public String list(Model model) {
		model.addAttribute("list",behaviorFeedbackRepository.findAll());
		return "complains/list";
	}

	@GetMapping("/behavior/feedback/anonimous/form")
	public String form(Model model, NewBehaviorFeedbackForm form,
			@RequestParam(required = false, defaultValue = "true") boolean info) {

		if (info) {
			model.addAttribute("infoMsg",
					"Nos conte o que aconteceu da maneira mais detalhada possível para que possamos entender a situação. \n"
							+ "Nos comprometemos a dar um retorno em até 2 dias úteis.\n"
							+ "Após clicar em enviar, será gerado um link para que você tenha acesso ao nosso retorno e, se necessário,\n"
							+ "conversaremos um pouco mais para ter mais detalhes, tudo de forma anônima caso não se sinta confortável em se \n"
							+ "identificar.");
		}
		return "complains/new-form";
	}

	@PostMapping("/behavior/feedback/anonimous")
	public String save(Model model, @Valid NewBehaviorFeedbackForm form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return form(model, form, true);
		}

		BehaviorFeedback newFeedback = form.toBehaviorFeedback();
		newBehaviorFeedback.save(newFeedback);

		redirectAttributes.addFlashAttribute("msg", "Seu feedback foi registrado com sucesso. "
				+ " Para que você siga essa conversa, copie o link http://people.caelum.com.br/behavior/anonimous/timeline/"
				+ newFeedback.getHash()
				+ ". É importante não perder esse link, pois será nosso único meio de comunicação com você.\n"
				+ "Acesse esse endereço dentro de 2 dias úteis e a gente já vai ter um retorno inicial para você.");

		return "redirect:/behavior/feedback/anonimous/form?info=false";
	}
}
