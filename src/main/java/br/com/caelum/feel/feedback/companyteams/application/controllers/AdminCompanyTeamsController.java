package br.com.caelum.feel.feedback.companyteams.application.controllers;

import br.com.caelum.feel.feedback.companyteams.application.forms.TeamForm;
import br.com.caelum.feel.feedback.companyteams.application.services.SaveTeamService;
import br.com.caelum.feel.feedback.companyteams.domain.models.CompanyTeam;
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
@RequestMapping("admin/company-teams")
public class AdminCompanyTeamsController {


    private final SaveTeamService service;

    public AdminCompanyTeamsController(SaveTeamService service) {
        this.service = service;
    }


    @GetMapping
    public ModelAndView list(Optional<Integer> page){
        var view = new ModelAndView("admin/company-teams/list");
        var currentPage = page.orElse(0);
        view.addObject("teams", service.getAllPaged(currentPage));

        return view;

    }

    @GetMapping(value = {"new", "{optionalId}"})
    public ModelAndView form(@PathVariable Optional<Long> optionalId, TeamForm form){
        var view = new ModelAndView("admin/company-teams/form");

        service.fillFormOnlyWhenIdIsPresent(optionalId, form);

        view.addObject("teamForm", form);

        return view;
    }


    @PostMapping
    public ModelAndView save(@Valid TeamForm form, BindingResult result, RedirectAttributes redirect){
        var view = new ModelAndView("redirect:/admin/company-teams");

        if (result.hasErrors()) {
            return form(empty(), form);
        }

        service.saveByForm(form);

        redirect.addFlashAttribute("msg", String.format("Time %s salvo com sucesso!", form.getName()));

        return view;
    }


    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<CompanyTeam> delete(@PathVariable Long id){
        var removedTeam = service.removeById(id);
        return removedTeam.map(ResponseEntity.accepted()::body).orElseGet(ResponseEntity.noContent()::build);
    }
}
