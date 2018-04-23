package br.com.caelum.feel.feedback.controllers.admin.questions;

import br.com.caelum.feel.feedback.forms.QuestionForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

import static java.util.Optional.*;

@Controller
@RequestMapping("admin/questions")
public class AdminQuestionsController {


    @GetMapping
    public ModelAndView list(){
        return new ModelAndView("admin/questions/list");
    }

    @GetMapping("new")
    public ModelAndView form(QuestionForm form){
        var view = new ModelAndView("admin/questions/form");

        view.addObject("questionForm", form);

        return view;
    }


    @PostMapping
    public ModelAndView save(@Valid QuestionForm form, BindingResult result){

        if (result.hasErrors()){
            return form(form);
        }

        System.out.println(form);

        return new ModelAndView("redirect:/admin/questions");

    }
}
