package br.com.caelum.feel.feedback.application.questions.controllers;

import br.com.caelum.feel.feedback.application.questions.form.AnswerForm;
import br.com.caelum.feel.feedback.domain.companyteams.repositories.Teams;
import br.com.caelum.feel.feedback.domain.questions.models.Question;
import br.com.caelum.feel.feedback.domain.questions.respositories.Questions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
