package ru.golovnev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.golovnev.dao.CounterAgentRepository;
import ru.golovnev.entity.CounterAgentEntity;
import ru.golovnev.model.CounterAgent;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
        log.info("[FinderService]\tFind the next agents:" + agentList);
        return agents;
    }

    public CounterAgent findById(Long id) {
        CounterAgentEntity agentDAO = repository.findById(id).orElseThrow();
        log.info("[FinderService]\tFind the next agent by id:" + agentDAO);
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
        log.info("[FinderService]\tFind the next agent by name:" + agentDAO);
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
        log.info("[FinderService]\tFind the next agent by bik and account:" + agentDAO);
        return CounterAgent.builder()
                .name(agentDAO.getName())
                .inn(agentDAO.getInn())
                .kpp(agentDAO.getKpp())
                .numberAccount(agentDAO.getNumberAccount())
                .bik(agentDAO.getBik())
                .build();
    }
}
