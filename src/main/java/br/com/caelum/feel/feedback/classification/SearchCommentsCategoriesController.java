package br.com.caelum.feel.feedback.classification;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;

@Controller
public class SearchCommentsCategoriesController {

	@Autowired
	private CategoryInfoRepository categoryInfoRepository;
	@Autowired
	private CycleRepository cycleRepository;
	@Autowired
	private CategorizedInfoRepository categorizedInfoRepository;

	@GetMapping("/admin/comments/categories/search/form")
	public String form(Model model, SearchCategoriesForm form) {

		model.addAttribute("cycleList", cycleRepository.findAll());
		model.addAttribute("categoryInfoList", categoryInfoRepository.findAll());
		return "admin/classification/search-categories";
	}

	@GetMapping("/admin/comments/categories/search")
	public String methodName(Model model, @Valid SearchCategoriesForm form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return form(model, form);
		}

		Specification<CategorizedInfo> criteria = form.build();
		List<CategorizedInfo> infos = categorizedInfoRepository.findAll(criteria);
		
		System.out.println(infos.size());
		
		model.addAttribute("allAnswersPerCategory", new AllAnswersPerCategory(infos));

		return form(model,form);
	}
}
