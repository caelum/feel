package br.com.caelum.feel.feedback.questions.application.controllers;

import br.com.caelum.feel.feedback.questions.application.forms.AnswerForm;
import br.com.caelum.feel.feedback.companyteams.domain.repositories.Teams;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("questions")
public class QuestionsController {


    private final Questions questions;
    private final Teams teams;

    public QuestionsController(Questions questions, Teams teams) {
        this.questions = questions;
        this.teams = teams;
    }

    @GetMapping("{uuid}")
    public ModelAndView form(@PathVariable String uuid, AnswerForm form){

        var optionalQuestion = questions.findByHash(uuid);

        if (optionalQuestion.isPresent()) {
            var view = new ModelAndView("questions/form");

            view.addObject("answerForm", form);
            view.addObject("allTeams", teams.findAll());
            view.addObject("question", optionalQuestion.get());

            return view;
        }

        return new ModelAndView("redirect:/question-not-found");
    }






}
