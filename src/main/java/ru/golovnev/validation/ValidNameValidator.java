package ru.golovnev.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.service.CounterAgentFinderService;
import ru.golovnev.validation.annotation.ValidName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-валидатор для валидации поля наименования контрагента
 */
@Slf4j
public class ValidNameValidator implements ConstraintValidator<ValidName, String> {
    @Autowired
    private CounterAgentFinderService finder;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<CounterAgent> agentList = finder.findAll();
        List<String> agentNames = new ArrayList<>();
        for (var agent: agentList) {
            agentNames.add(agent.getName());
        }
        if (!agentNames.contains(value)){
            log.info("[ValidName]\tValidation successful");
            return true;
        }
        else {
            log.error("[ValidName]\tValidation failed");
            return false;
        }
    }
}
