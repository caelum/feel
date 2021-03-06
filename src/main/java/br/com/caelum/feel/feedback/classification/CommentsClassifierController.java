package br.com.caelum.feel.feedback.classification;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;
import br.com.caelum.feel.feedback.questions.domain.respositories.FeedbackAnswerRepository;
import br.com.caelum.feel.feedback.reports.application.controllers.FeedbackReportsController;
import br.com.caelum.feel.security.SystemUser;

@Controller
public class CommentsClassifierController {

	// ok, aceito ser julgado aqui hehehe.
	@Autowired
	private FeedbackReportsController feedbackReportsController;
	@Autowired
	private FeedbackAnswerRepository feedbackAnswerRepository;
	@Autowired
	private CategoryInfoRepository categoryInfoRepository;
	@Autowired
	private CategorizedInfoRepository categorizedInfoRepository;

	@PostMapping("/admin/comments/categories")
	public String saveCategory(Model model, @Valid NewCategoryCommentForm form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal SystemUser currentUser) {
		if (bindingResult.hasErrors()) {
			return feedbackReportsController.rawAnswersList(model, form.getSearchForm(),currentUser);
		}

		CategoryInfo categoryInfo = form.build();
		categoryInfoRepository.save(categoryInfo);

		categorizedInfoRepository.save(new CategorizedInfo(categoryInfo,
				feedbackAnswerRepository.findById(form.getAnswerId()).get()));

		redirectAttributes.addFlashAttribute("msg", "Nova categoria criada e associada com sucesso.");
		return "redirect:/reports/feedback/raw-answers/search?" + form.getSearchForm().serializeParms();
	}
	
	@PostMapping("/admin/comments/select/category")
	public String methodName(Model model, @Valid ChooseCategoryInfoForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes,@AuthenticationPrincipal SystemUser currentUser) {
		if (bindingResult.hasErrors()) {
			return  feedbackReportsController.rawAnswersList(model, form.getSearchForm(), currentUser);
		}

		CategoryInfo categoryInfo = categoryInfoRepository.findById(form.getCategoryInfoId()).get();
		FeedbackAnswer feedbackAnswer = feedbackAnswerRepository.findById(form.getAnswerId()).get();
		categorizedInfoRepository.save(new CategorizedInfo(categoryInfo,feedbackAnswer));
		
		redirectAttributes.addFlashAttribute("msg", "Categoria associada com sucesso");
		return "redirect:/reports/feedback/raw-answers/search?" + form.getSearchForm().serializeParms();
	}

}
