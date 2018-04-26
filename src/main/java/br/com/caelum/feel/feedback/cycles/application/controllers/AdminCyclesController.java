package br.com.caelum.feel.feedback.cycles.application.controllers;

import br.com.caelum.feel.feedback.cycles.application.forms.CycleForm;
import br.com.caelum.feel.feedback.cycles.domain.models.Cycle;
import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
public class AdminCyclesController {

	@Autowired
	private CycleRepository cycleRepository;

	@GetMapping("/admin/cycles/form")
	public String newForm(CycleForm form) {
		return "admin/cycles/new-form";
	}

	@PostMapping("/admin/cycles")
	public String save(@Valid CycleForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return newForm(form);
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
	public String updateForm(CycleForm form, @PathVariable("id") Integer id) {
		Cycle cycle = cycleRepository.findById(id).get();
		form.fill(cycle);
		return "admin/cycles/update-form";
	}

	@PostMapping("/admin/cycles/{id}")
	@Transactional
	public String update(@Valid CycleForm form, BindingResult bindingResult,
			@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			form.dirty();
			return updateForm(form, id);
		}
		
		Cycle updatedCycle = form.build();
		cycleRepository.findById(id).get().update(updatedCycle);

		redirectAttributes.addFlashAttribute("msg", "Ciclo editado com sucesso");
		return "redirect:/admin/cycles";
	}
}
