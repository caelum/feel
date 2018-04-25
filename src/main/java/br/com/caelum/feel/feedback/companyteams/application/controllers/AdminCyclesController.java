package br.com.caelum.feel.feedback.companyteams.application.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.feel.feedback.companyteams.application.forms.EditCycleForm;
import br.com.caelum.feel.feedback.companyteams.application.forms.NewCycleForm;
import br.com.caelum.feel.feedback.companyteams.domain.models.Cycle;
import br.com.caelum.feel.feedback.companyteams.domain.repositories.CycleRepository;

@Controller
public class AdminCyclesController {

	@Autowired
	private CycleRepository cycleRepository;

	@GetMapping("/admin/cycles/form")
	public String newForm(Model model, NewCycleForm form) {
		return "admin/cycles/new-form";
	}

	@PostMapping("/admin/cycles")
	public String save(Model model, @Valid NewCycleForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return newForm(model, form);
		}

		cycleRepository.save(form.build());
		redirectAttributes.addFlashAttribute("msg", "Novo ciclo gravado com sucesso");

		return "redirect:/admin/cycles";
	}

	@GetMapping("/admin/cycles")
	public String list(Model model) {
		model.addAttribute("cycleList", cycleRepository.findAll());
		return "admin/cycles/list";
	}

	@GetMapping("/admin/cycles/{id}")
	public String updateForm(Model model, EditCycleForm form, @PathVariable("id") Integer id) {
		Cycle cycle = cycleRepository.findById(id).get();
		form.fill(cycle);
		return "admin/cycles/update-form";
	}

	@PostMapping("/admin/cycles/{id}")
	@Transactional
	public String update(Model model, @Valid EditCycleForm form, BindingResult bindingResult,
			@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			form.dirty();
			return updateForm(model, form, id);
		}
		
		Cycle updatedCycle = form.build();
		cycleRepository.findById(id).get().update(updatedCycle);

		redirectAttributes.addFlashAttribute("msg", "Ciclo editado com sucesso");
		return "redirect:/admin/cycles";
	}
}
