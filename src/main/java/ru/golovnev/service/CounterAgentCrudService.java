package ru.golovnev.service;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.golovnev.entity.CounterAgentEntity;
import ru.golovnev.exception.AgentNotFoundException;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.dao.CounterAgentRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CounterAgentCrudService {

    @Autowired
    private CounterAgentRepository repository;

    @Autowired
    private MapperFacade mapperFacade;

    public void save(CounterAgent agent) {
        CounterAgentEntity saveAgent = mapperFacade.map(agent, CounterAgentEntity.class);
        repository.save(saveAgent);
        log.info("[CrudService]\tConverted next agent (by Orika): " + agent);
        log.info("[CrudService]\tRepository: save agent - " + saveAgent);
    }

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

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
            log.info("[CrudService]\tRepository: delete agent by id - " + id);
        }
        catch (Exception e) {
            throw new AgentNotFoundException("Agent[id = " + id + "] could not find in repository");
        }
    }

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
