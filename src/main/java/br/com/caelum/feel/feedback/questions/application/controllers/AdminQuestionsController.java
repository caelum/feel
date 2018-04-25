package br.com.caelum.feel.feedback.questions.application.controllers;

import br.com.caelum.feel.feedback.questions.application.forms.OpenCloseStateForm;
import br.com.caelum.feel.feedback.questions.application.forms.QuestionForm;
import br.com.caelum.feel.feedback.questions.application.services.QuestionService;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

import static java.util.Optional.empty;

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

    @GetMapping({"new", "{optionalId}"})
    public ModelAndView form(@PathVariable Optional<Long> optionalId, QuestionForm form){
        var view = new ModelAndView("admin/questions/form");

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

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<?> changeState(@PathVariable Long id, @RequestBody OpenCloseStateForm form){
        var targetState = form.getState();

        try {
            return
            service.tryTransitState(id, targetState)
                            .map(ResponseEntity::ok)
                                .orElseGet(ResponseEntity.noContent()::build);
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.GONE).body(e.getMessage());
        }
    }
}
