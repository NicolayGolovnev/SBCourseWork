package ru.golovnev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.golovnev.entity.CounterAgentEntity;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.dao.CounterAgentRepository;

import java.util.Optional;

@Service
@Slf4j
public class CounterAgentCrudService {

    @Autowired
    private CounterAgentRepository repository;

    public void save(CounterAgent agent) {
        CounterAgentEntity inputAgent = CounterAgentEntity.from(agent);
        repository.save(inputAgent);
        log.info("[CrudService]\tRepository: save agent - " + inputAgent);
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
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
        log.info("[CrudService]\tRepository: delete agent by id - " + id);
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
        log.info("[CrudService]\tRepository: delete agent by name - " + name);
    }
}
