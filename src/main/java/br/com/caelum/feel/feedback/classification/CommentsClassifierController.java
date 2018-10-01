package br.com.caelum.feel.feedback.classification;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.feel.feedback.reports.application.controllers.FeedbackReportsController;
import br.com.caelum.feel.security.SystemUser;

@Controller
public class CommentsClassifierController {
	
	@Autowired
	private FeedbackReportsController feedbackReportsController;

	@PostMapping("/admin/comments/categories")
	public String saveCategory(Model model, @Valid NewCategoryCommentForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes,@AuthenticationPrincipal SystemUser currentUser) {
		if (bindingResult.hasErrors()) {			
			return feedbackReportsController.rawAnswersList(model,form.rebuildSearchForm(), currentUser);
		}


		redirectAttributes.addFlashAttribute("msg", "Nova categoria criada com sucesso.");
		return "redirect:/reports/feedback/raw-answers/search?"+form.serializeSearchParms();
	}

}
