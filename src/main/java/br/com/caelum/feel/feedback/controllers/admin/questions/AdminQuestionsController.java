package br.com.caelum.feel.feedback.controllers.admin.questions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/questions")
public class AdminQuestionsController {


    @GetMapping("new")
    public ModelAndView form(){
        return new ModelAndView("admin/questions/form");
    }
}
