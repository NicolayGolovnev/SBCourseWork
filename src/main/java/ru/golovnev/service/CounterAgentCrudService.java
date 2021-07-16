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
    private CounterAgentRepository counterAgentRepository;

    public List<CounterAgent> findAll() {
        List<CounterAgentEntity> agentList = counterAgentRepository.findAll();
        List<CounterAgent> agents = new ArrayList<>();
        for (var agent: agentList) {
            agents.add(CounterAgent.builder()
                    .id(agent.getId())
                    .name(agent.getName())
                    .inn(agent.getInn())
                    .kpp(agent.getKpp())
                    .numberAccount(agent.getNumberAccount())
                    .bik(agent.getBik())
                    .build());
        }
        return agents;
    }

    public void save(CounterAgent agent) {
        CounterAgentEntity inputAgent = CounterAgentEntity.from(agent);
        CounterAgentEntity savedAgent = counterAgentRepository.save(inputAgent);
    }

    public void update(CounterAgent agent) {
        Optional<CounterAgentEntity> agentDBO = counterAgentRepository.findById(agent.getId());
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

            counterAgentRepository.save(agentDB);
        }
    }

    public void deleteById(Long id) {
        counterAgentRepository.deleteById(id);
    }

    //убрать в конечном счете из круд-сервиса
    public CounterAgent findById(Long id) {
        Optional<CounterAgentEntity> agentDAO = counterAgentRepository.findById(id);
        return CounterAgent.builder()
                .id(agentDAO.get().getId())
                .name(agentDAO.get().getName())
                .inn(agentDAO.get().getInn())
                .kpp(agentDAO.get().getKpp())
                .numberAccount(agentDAO.get().getNumberAccount())
                .bik(agentDAO.get().getBik())
                .build();
    }
}
