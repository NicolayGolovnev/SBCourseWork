package sber.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sber.model.CounterAgentModel;
import sber.service.CounterAgentCrudService;

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
    public ModelAndView getAllUsers(Model model) {
        List<CounterAgentModel> agentList = counterAgentCrudService.findAll();
//        ModelAndView modelAndView = new ModelAndView("counteragents");
//        modelAndView.addObject("counterAgentsFromServer", agentList);
        model.addAttribute("counterAgentsFromServer", agentList);
        return new ModelAndView("/counteragents", model.asMap());
    }

    @GetMapping("/counteragents/new")
    public ModelAndView getAgent(@ModelAttribute("agentForm") CounterAgentModel model) {
        log.error("GET FORM AGENT CREATE");
        return new ModelAndView("/counteragents/new");
    }

    @PostMapping("/counteragents/new")
    public ModelAndView addAgent(@ModelAttribute("agentForm") @Valid CounterAgentModel agentForm,
                                 BindingResult bindingResult,
                                 Model model) {

//        CounterAgentModel.createAgentForm(agentForm);
//        counterAgentValidator.validate(agentForm, bindingResult);
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
    public ModelAndView updateAgent(@PathVariable("id") Long id, Model model) {
        log.error("GET FORM AGENT UPDATE");
        CounterAgentModel agent = counterAgentCrudService.findById(id);
        model.addAttribute("updateAgent", agent);
        log.info(model.toString());
        return new ModelAndView("/counteragents/update");
    }

    @PostMapping("/counteragents/update")
    public ModelAndView updateAgentForm(@ModelAttribute("updateAgent") CounterAgentModel agent) {
        log.error("UPDATED AGENT");
        counterAgentCrudService.save(agent);
        return new ModelAndView("redirect:/counteragents");

    }

    @GetMapping("/counteragents/delete/{id}")
    public ModelAndView deleteAgent(@PathVariable("id") Long id) {
        log.error("GET ACTION AGENT DELETE");
        counterAgentCrudService.deleteById(id);
        return new ModelAndView("redirect:/counteragents");
    }
}
