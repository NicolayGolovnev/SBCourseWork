package ru.golovnev.service;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.golovnev.entity.CounterAgentEntity;
import ru.golovnev.exception.AgentNotFoundException;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.dao.CounterAgentRepository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Сервис для работы с БД контрагентов
 */
@Service
@Slf4j
public class CounterAgentCrudService {

    @Autowired
    private CounterAgentRepository repository;

    @Autowired
    private MapperFacade mapperFacade;

    /**
     * Метод сохранения контрагента
     * @param agent сохраняемый контрагент
     */
    public void save(CounterAgent agent) {
        CounterAgentEntity saveAgent = mapperFacade.map(agent, CounterAgentEntity.class);
        repository.save(saveAgent);
        log.info("[CrudService]\tConverted next agent (by Orika): " + agent);
        log.info("[CrudService]\tRepository: save agent - " + saveAgent);
    }

    /**
     * Метод обновления контрагента по ИД
     * @param agent обновляемый контрагент
     */
    @Transactional
    public void update(CounterAgent agent) {
        Optional<CounterAgentEntity> agentDBO = repository.findById(agent.getId());

        if (agentDBO.isPresent()) {
            CounterAgentEntity agentDB = agentDBO.get();
            agentDB.setName(agent.getName());
            agentDB.setInn(agent.getInn());
            agentDB.setKpp(agent.getKpp());
            agentDB.setNumberAccount(agent.getNumberAccount());
            agentDB.setBik(agent.getBik());

            repository.save(agentDB);
            log.info("[CrudService]\tRepository: update agent - " + agentDB);
        }
        else
            throw new AgentNotFoundException("Agent[id = " + agent.getId() + "] could not find in repository");
    }

    /**
     * Метод удаления контрагента по ИД
     * @param id ИД контрагента
     */
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
            log.info("[CrudService]\tRepository: delete agent by id - " + id);
        }
        catch (Exception e) {
            throw new AgentNotFoundException("Agent[id = " + id + "] could not find in repository");
        }
    }

    /**
     * Метод удаления контрагента по имени
     * @param name наименование контрагента
     */
    public void deleteByName(String name) {
        try {
            repository.deleteByName(name);
            log.info("[CrudService]\tRepository: delete agent by name - " + name);
        }
        catch (Exception e) {
            throw new AgentNotFoundException("Agent[name = " + name + "] could not find in repository");
        }
    }
}
