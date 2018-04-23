package br.com.caelum.feel.feedback.application.admin.questions.controllers;

import br.com.caelum.feel.feedback.application.admin.questions.forms.QuestionForm;
import br.com.caelum.feel.feedback.application.admin.questions.services.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("admin/questions")
public class AdminQuestionsController {


    private final QuestionService service;

    public AdminQuestionsController(QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView list(Optional<Integer> page){
        var view = new ModelAndView("admin/questions/list");

        var currentPage = page.orElse(0);
        view.addObject("questions", service.getAllPaged(currentPage));

        return view;
    }

    @GetMapping("new")
    public ModelAndView form(QuestionForm form){
        var view = new ModelAndView("admin/questions/form");

        view.addObject("questionForm", form);

        return view;
    }


    @PostMapping
    public ModelAndView save(@Valid QuestionForm form, BindingResult result, RedirectAttributes redirect){

        if (result.hasErrors()){
            return form(form);
        }

        service.saveBy(form);

        redirect.addFlashAttribute("msg", "Questão salva com sucesso!");

        return new ModelAndView("redirect:/admin/questions");

    }
}
