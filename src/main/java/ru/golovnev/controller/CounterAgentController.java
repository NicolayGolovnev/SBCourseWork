package ru.golovnev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.service.CounterAgentCrudService;
import ru.golovnev.service.CounterAgentFinderService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class CounterAgentController {

    @Autowired
    private CounterAgentCrudService crudService;
    @Autowired
    private CounterAgentFinderService finderService;

    @GetMapping("/counteragents")
    public ModelAndView getAllUsers(Model model) {
        List<CounterAgent> agentList = finderService.findAll();
        model.addAttribute("counterAgentsFromServer", agentList);
        model.addAttribute("deleteAgent", new CounterAgent());
        log.info("[GET /counteragents]\tReturn page with two model: CounterAgent and List<CounterAgent>");
        return new ModelAndView("/counteragents");
    }

    @GetMapping("/counteragents/new")
    public ModelAndView newAgent(@ModelAttribute("agentForm") CounterAgent counterAgent) {
        log.info("[GET /counteragents/new]\tReturn page with model CounterAgent");
        return new ModelAndView("/counteragents/new");
    }

    @PostMapping("/counteragents/new")
    public ModelAndView addAgent(@ModelAttribute("agentForm") @Valid CounterAgent agentForm,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()){
            log.error("[POST /counteragents/new]\tBindingResult: errors of validation CounterAgent");
            for (var err: bindingResult.getAllErrors()) {
                log.error(err.toString());
            }
            model.addAttribute("agentForm", agentForm);
            return new ModelAndView("/counteragents/new");
        }

        crudService.save(agentForm);
        log.info("[POST /counteragents/new]\tRedirect to page /counteragents");
        return new ModelAndView("redirect:/counteragents");
    }

    @GetMapping("/counteragents/update/{id}")
    public ModelAndView createAgentUpdateForm(@PathVariable("id") Long id, Model model) {
        CounterAgent agent = finderService.findById(id);
        model.addAttribute("updateAgent", agent);
        log.info("[GET /counteragents/update/" + id + "]\tReturn page /update with CounterAgent");
        return new ModelAndView("/counteragents/update");
    }

    @GetMapping("/counteragents/update")
    public ModelAndView updateAgent(@ModelAttribute("updateAgent") CounterAgent agent) {
        log.info("[GET /counteragents/update]\tReturn page with model CounterAgent");
        return new ModelAndView("/counteragents/update");
    }

    @PostMapping("/counteragents/update")
    public ModelAndView updateAgentPost(@ModelAttribute("updateAgent") @Valid CounterAgent agent,
                                        BindingResult bindingResult,
                                        Model model) {
        List<FieldError> errors = bindingResult.getFieldErrors().stream()
                .filter(ferr -> !ferr.getField().equals("duplicateName"))
                .collect(Collectors.toList());
        BindingResult newBinding = new BeanPropertyBindingResult(agent, "updateAgent");
        for (var err : errors) {
            newBinding.addError(err);
        }

        if (newBinding.hasErrors()) {
            log.error("[POST /counteragents/update]\tBindingResult: errors of validation CounterAgent");
            for (var err: newBinding.getAllErrors()) {
                log.error(err.toString());
            }
            model.addAttribute("updateAgent", agent);
            return new ModelAndView("/counteragents/update");
        }
        crudService.update(agent);
        log.info("[POST /counteragents/update]\tRedirect to page /counteragents");
        return new ModelAndView("redirect:/counteragents");
    }

    @GetMapping("/counteragents/delete/{id}")
    public ModelAndView deleteAgent(@PathVariable("id") Long id) {
        crudService.deleteById(id);
        log.info("[GET /counteragents/delete/" + id + "]\tRedirect to page /counteragents");
        return new ModelAndView("redirect:/counteragents");
    }

    @GetMapping("/find")
    public ModelAndView goToFindPage(Model model) {
        model.addAttribute("findByName", new CounterAgent());
        model.addAttribute("findByBikAndNumberAccount", new CounterAgent());
        log.info("[GET /find]\tReturn page with two model of CounterAgent");
        return new ModelAndView("/counteragents/find");
    }

    @PostMapping("/find/{search}")
    public ModelAndView findAgent(@ModelAttribute("findByName") CounterAgent agent,
                                  @PathVariable("search") String field,
                                  Model model) {
        CounterAgent finder = new CounterAgent();
        if (field.equals("byName")) {
            finder = finderService.findByName(agent.getName());
        }
        else if (field.equals("byBikAndNumberAccount")) {
            finder = finderService.findByBikAndNumberAccount(agent.getBik(), agent.getNumberAccount());
        }

        model.addAttribute("finderAgent", finder);
        log.info("[POST /find/" + field + "]\tReturn page /showResult");
        return new ModelAndView("/counteragents/showResult");
    }

    @PostMapping("/deleteByName")
    @Transactional
    public ModelAndView deleteAgentByName(@ModelAttribute("deleteAgent") CounterAgent agent) {
        crudService.deleteByName(agent.getName());
        log.info("[POST /counteragents/deleteByName]\tRedirect to page /counteragents");
        return new ModelAndView("redirect:/counteragents");
    }

}
