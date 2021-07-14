package sber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sber.entity.CounterAgent;
import sber.entity.CounterAgentForm;
import sber.service.CounterAgentService;

import java.util.List;

@RestController
public class CounterAgentController {

    @Autowired
    private CounterAgentService counterAgentService;

    @RequestMapping(path = "/counteragents", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {

        List<CounterAgent> agentList = counterAgentService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("counteragents");

        modelAndView.addObject("counterAgentsFromServer", agentList);
        return modelAndView;
    }


//    @RequestMapping(path = "/users/test", method = RequestMethod.GET)
//    public ModelAndView getAllUsersBy() {
//
//        List<User> userList = userService.test();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("users");
//
//
//        List<Car> cars = userList.stream().flatMap(s -> s.getCars().stream())
//                .collect(Collectors.toList());
//
//        modelAndView.addObject("usersFromServer", userList);
//        modelAndView.addObject("carsFromServer", cars);
//        return modelAndView;
//    }

    @RequestMapping(path = "counteragents", method = RequestMethod.POST)
    public ModelAndView addUser(CounterAgentForm agentForm) {
        CounterAgent newAgent = CounterAgent.from(agentForm);
        counterAgentService.save(newAgent);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("counteragents");
        List<CounterAgent> agentList = counterAgentService.findAll();
        modelAndView.addObject("counterAgentsFromServer", agentList);
        return modelAndView;
    }
}
