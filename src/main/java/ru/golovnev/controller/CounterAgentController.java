package ru.golovnev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.service.CounterAgentCrudService;
import ru.golovnev.service.CounterAgentFinderService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
public class CounterAgentController {

    @Autowired
    private CounterAgentCrudService crudService;
    @Autowired
    private CounterAgentFinderService finderService;

//    @Autowired
//    @Qualifier("counterAgentValidator")
//    private Validator counterAgentValidator;

    @GetMapping("/counteragents")
    public ModelAndView getAllUsers(Model model) {
        List<CounterAgent> agentList = finderService.findAll();
        model.addAttribute("counterAgentsFromServer", agentList);
        return new ModelAndView("/counteragents");
    }

    @GetMapping("/counteragents/new")
    public ModelAndView newAgent(@ModelAttribute("agentForm") CounterAgent counterAgent) {
        log.error("PAGE OF CREATE A AGENT");
        return new ModelAndView("/counteragents/new");
    }

    @PostMapping("/counteragents/new")
    public ModelAndView addAgent(@ModelAttribute("agentForm") @Valid CounterAgent agentForm,
                                 BindingResult bindingResult,
                                 Model model) {
//        counterAgentValidator.validate(agentForm);
        log.error("CREATE NEW AGENT");
        log.error(agentForm.toString());
        log.error(bindingResult.toString());
        if (bindingResult.hasErrors()){
            log.error("AGENT HAS TROUBLES IN FIELDS");
            for (var err: bindingResult.getAllErrors()) {
                log.error(err.toString());
            }
//            List<ObjectError> allErrors = bindingResult.getAllErrors();
            model.addAttribute("agentForm", agentForm);
            return new ModelAndView("/counteragents/new");
        }

        crudService.save(agentForm);
        return new ModelAndView("redirect:/counteragents");
    }

    @GetMapping("/counteragents/update/{id}")
    public ModelAndView createAgentUpdateForm(@PathVariable("id") Long id, Model model) {
        CounterAgent agent = finderService.findById(id);
        model.addAttribute("updateAgent", agent);
        return new ModelAndView("/counteragents/update");
    }

    @PostMapping("/counteragents/update")
    public ModelAndView updateAgentPost(@ModelAttribute("updateAgent") CounterAgent agent) {
        crudService.update(agent);
        return new ModelAndView("redirect:/counteragents");
    }

    @GetMapping("/counteragents/delete/{id}")
    public ModelAndView deleteAgent(@PathVariable("id") Long id) {
        crudService.deleteById(id);
        return new ModelAndView("redirect:/counteragents");
    }

    @GetMapping("/find")
    public ModelAndView goToFindPage(Model model) {
        model.addAttribute("findByName", new CounterAgent());
        model.addAttribute("findByBikAndNumberAccount", new CounterAgent());

        return new ModelAndView("/counteragents/find");
    }

    @PostMapping("/find/{search}")
    public ModelAndView findAgent(@ModelAttribute("findByName") CounterAgent agent, @PathVariable("search") String field, Model model) {
        CounterAgent finder = new CounterAgent();
        if (field.equals("byName")) {
            finder = finderService.findByName(agent.getName());
        }
        else if (field.equals("byBikAndNumberAccount")) {
            finder = finderService.findByBikAndNumberAccount(agent.getBik(), agent.getNumberAccount());
        }

        model.addAttribute("finderAgent", finder);
        log.error(finder.toString());
        return new ModelAndView("/counteragents/showResult");
    }

}
