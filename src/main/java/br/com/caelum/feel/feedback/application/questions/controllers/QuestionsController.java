package br.com.caelum.feel.feedback.application.questions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("questions")
public class QuestionsController {



    @GetMapping("new")
    public String form(){
        return "questions/form";
    }
}
