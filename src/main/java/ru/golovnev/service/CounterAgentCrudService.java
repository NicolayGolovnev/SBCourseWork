package ru.golovnev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.golovnev.entity.CounterAgentEntity;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.dao.CounterAgentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CounterAgentCrudService {

    @Autowired
    private CounterAgentRepository repository;

    public void save(CounterAgent agent) {
        CounterAgentEntity inputAgent = CounterAgentEntity.from(agent);
        repository.save(inputAgent);
    }

    public void update(CounterAgent agent) {
        Optional<CounterAgentEntity> agentDBO = repository.findById(agent.getId());
        log.error(agent.toString());
        log.error(agentDBO.toString());
        if (agentDBO.isPresent()) {
            log.error("UPDATE AGENT IS EXIST");
            CounterAgentEntity agentDB = agentDBO.get();
            agentDB.setName(agent.getName());
            agentDB.setInn(agent.getInn());
            agentDB.setKpp(agent.getKpp());
            agentDB.setNumberAccount(agent.getNumberAccount());
            agentDB.setBik(agent.getBik());

            repository.save(agentDB);
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }
}
