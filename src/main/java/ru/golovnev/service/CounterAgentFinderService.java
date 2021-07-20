package ru.golovnev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.golovnev.dao.CounterAgentRepository;
import ru.golovnev.entity.CounterAgentEntity;
import ru.golovnev.model.CounterAgent;

import java.util.ArrayList;
import java.util.List;

@Service
public class CounterAgentFinderService {

    @Autowired
    private CounterAgentRepository repository;

    public List<CounterAgent> findAll() {
        List<CounterAgentEntity> agentList = repository.findAll();
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

    public CounterAgent findById(Long id) {
        CounterAgentEntity agentDAO = repository.findById(id).orElseThrow();
        return CounterAgent.builder()
                .id(agentDAO.getId())
                .name(agentDAO.getName())
                .inn(agentDAO.getInn())
                .kpp(agentDAO.getKpp())
                .numberAccount(agentDAO.getNumberAccount())
                .bik(agentDAO.getBik())
                .build();
    }

    public CounterAgent findByName(String name) {
        CounterAgentEntity agentDAO = repository.findFirstByName(name).orElseThrow();
        return CounterAgent.builder()
                .name(agentDAO.getName())
                .inn(agentDAO.getInn())
                .kpp(agentDAO.getKpp())
                .numberAccount(agentDAO.getNumberAccount())
                .bik(agentDAO.getBik())
                .build();
    }

    public CounterAgent findByBikAndNumberAccount(String bik, String numberAccount) {
        CounterAgentEntity agentDAO = repository.findFirstByBikAndNumberAccount(bik, numberAccount).orElseThrow();
        return CounterAgent.builder()
                .name(agentDAO.getName())
                .inn(agentDAO.getInn())
                .kpp(agentDAO.getKpp())
                .numberAccount(agentDAO.getNumberAccount())
                .bik(agentDAO.getBik())
                .build();
    }
}
