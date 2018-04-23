package br.com.caelum.feel.feedback.controllers.admin.questions;

import br.com.caelum.feel.feedback.domain.questions.respositories.Questions;
import br.com.caelum.feel.feedback.forms.QuestionForm;
import org.springframework.data.domain.PageRequest;
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


    private final Questions questions;

    public AdminQuestionsController(Questions questions) {
        this.questions = questions;
    }

    @GetMapping
    public ModelAndView list(Optional<Integer> page){
        var view = new ModelAndView("admin/questions/list");

        var currentPage = page.orElse(0);
        view.addObject("questions", questions.findAll(PageRequest.of(currentPage, 10)));

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

        var optionalQuestion = Optional.ofNullable(form.getId()).flatMap(questions::findById);

        optionalQuestion.ifPresent(question -> question.updateFromForm(form));

        var question = optionalQuestion.orElseGet(form::toQuestion);

        questions.save(question);

        redirect.addFlashAttribute("msg", "Questão salva com sucesso!");

        return new ModelAndView("redirect:/admin/questions");

    }
}
