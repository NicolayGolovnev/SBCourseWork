package ru.golovnev.validations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.service.CounterAgentFinderService;
import ru.golovnev.validations.annotations.ValidName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ValidNameValidator implements ConstraintValidator<ValidName, String> {
    @Autowired
    private CounterAgentFinderService finder;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.error("INPUT IN VALID NAME");
        List<CounterAgent> agentList = finder.findAll();
        List<String> agentNames = new ArrayList<>();
        for (var agent: agentList) {
            agentNames.add(agent.getName());
        }
        log.error(agentList.toString());
        return value != null && !agentNames.contains(value);
    }
}
