package br.com.caelum.feel.feedback.controllers.admin.companyteams;

import br.com.caelum.feel.feedback.domain.companyteams.models.CompanyTeam;
import br.com.caelum.feel.feedback.domain.companyteams.repositories.Teams;
import br.com.caelum.feel.feedback.forms.TeamForm;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

import static java.util.Optional.*;

@Controller
@RequestMapping("admin/company-teams")
public class AdminCompanyTeamsController {


    private final Teams teams;

    public AdminCompanyTeamsController(Teams teams) {
        this.teams = teams;
    }


    @GetMapping
    public ModelAndView list(Optional<Integer> page){
        var view = new ModelAndView("admin/company-teams/list");
        var pageRequest = PageRequest.of(page.orElse(0), 5);

        view.addObject("teams", teams.findAll(pageRequest));

        return view;

    }

    @GetMapping(value = {"new", "{optionalId}"})
    public ModelAndView form(@PathVariable Optional<Long> optionalId, TeamForm form){
        var view = new ModelAndView("admin/company-teams/form");

        optionalId.flatMap(teams::findById).ifPresent(form::fillFrom);

        view.addObject("teamForm", form);

        return view;
    }


    @PostMapping
    public ModelAndView save(@Valid TeamForm form, BindingResult result, RedirectAttributes redirect){
        var view = new ModelAndView("redirect:/admin/company-teams");

        if (result.hasErrors()) {
            return form(empty(), form);
        }

        teams.save(form.toEntity());

        redirect.addFlashAttribute("msg", String.format("Time %s salvo com sucesso!", form.getName()));

        return view;
    }


    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<CompanyTeam> delete(@PathVariable Long id){
        var team = teams.findById(id);

        team.ifPresent(teams::delete);

        return team.map(ResponseEntity.accepted()::body).orElseGet(ResponseEntity.noContent()::build);
    }
}
