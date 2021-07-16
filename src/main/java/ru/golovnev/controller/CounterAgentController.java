package ru.golovnev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.service.CounterAgentCrudService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class CounterAgentController {

    @Autowired
    private CounterAgentCrudService counterAgentCrudService;

//    @Autowired
//    @Qualifier("counterAgentValidator")
//    private Validator counterAgentValidator;

    @GetMapping("/counteragents")
    public ModelAndView getAllUsers(@ModelAttribute("agentForm") CounterAgent agentForm, Model model) {
        List<CounterAgent> agentList = counterAgentCrudService.findAll();
        model.addAttribute("counterAgentsFromServer", agentList);
        return new ModelAndView("/counteragents", model.asMap());
    }

    @PostMapping("/counteragents/new")
    public ModelAndView addAgent(@ModelAttribute("agentForm") @Valid CounterAgent agentForm,
                                 BindingResult bindingResult,
                                 Model model) {
        log.error("CREATE NEW AGENT");
        if (bindingResult.hasErrors()){
            log.error("AGENT HAS TROUBLES IN FIELDS");
            return new ModelAndView("/counteragents/new");
        }

        counterAgentCrudService.save(agentForm);
        model.addAttribute("agentForm", agentForm);
        return new ModelAndView("redirect:/counteragents", model.asMap());
    }

    @GetMapping("/counteragents/update/{id}")
    public ModelAndView createAgentUpdateForm(@PathVariable("id") Long id, Model model) {
        CounterAgent agent = counterAgentCrudService.findById(id);
        model.addAttribute("updateAgent", agent);
        return new ModelAndView("/counteragents/update");
    }

    @PostMapping("/counteragents/update")
    public ModelAndView updateAgent(@ModelAttribute("updateAgent") CounterAgent agent) {
        counterAgentCrudService.update(agent);
        return new ModelAndView("redirect:/counteragents");
    }

    @GetMapping("/counteragents/delete/{id}")
    public ModelAndView deleteAgent(@PathVariable("id") Long id) {
        counterAgentCrudService.deleteById(id);
        return new ModelAndView("redirect:/counteragents");
    }
}
