package br.com.caelum.feel.feedback.questions.application.controllers;

import static java.util.Optional.empty;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.feel.feedback.cycles.domain.repositories.CycleRepository;
import br.com.caelum.feel.feedback.questions.application.forms.QuestionForm;
import br.com.caelum.feel.feedback.questions.application.forms.QuestionsFilterForm;
import br.com.caelum.feel.feedback.questions.application.services.QuestionService;
import br.com.caelum.feel.feedback.questions.application.validators.JustOneLastQuestionValidator;
import br.com.caelum.feel.feedback.questions.domain.models.CategoryType;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;

@Controller
@RequestMapping("admin/questions")
public class AdminQuestionsController {


    private final QuestionService service;    
    private CycleRepository cycleRepository;
    private Questions questions;

    public AdminQuestionsController(QuestionService service,CycleRepository cycleRepository,Questions questions) {
        this.service = service;
		this.cycleRepository = cycleRepository;
		this.questions = questions;
    }
    
    @InitBinder(value="questionForm")
    public void init(WebDataBinder binder) {
    	binder.addValidators(new JustOneLastQuestionValidator(questions));
    }

    @GetMapping
    public ModelAndView list(QuestionsFilterForm form){
        var view = new ModelAndView("admin/questions/list");
        view.addObject("cycleList",cycleRepository.findAll());
        view.addObject("categoryList",CategoryType.values());
        view.addObject("questions", questions.findAll(form.build()));

        return view;
    }
    
    @GetMapping({"new", "{optionalId}"})
    public ModelAndView form(@PathVariable Optional<Long> optionalId, QuestionForm form){
        var view = new ModelAndView("admin/questions/form");
        view.addObject("cycleList", cycleRepository.findAll());
        view.addObject("categoryTypeList", CategoryType.values());
        service.fillFormOnlyWhenIdIsPresent(optionalId, form);

        view.addObject("questionForm", form);

        return view;
    }


    @PostMapping
    public ModelAndView save(@Valid QuestionForm form, BindingResult result, RedirectAttributes redirect){

        if (result.hasErrors()){
            return form(empty(),form);
        }
       
        service.saveBy(form);

        redirect.addFlashAttribute("msg", "Questão salva com sucesso!");

        return new ModelAndView("redirect:/admin/questions");

    }


    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<Question> delete(@PathVariable Long id){

        var removedQuestion = service.removeById(id);

        return removedQuestion.map(ResponseEntity.accepted()::body).orElseGet(ResponseEntity.noContent()::build);
    }
}
