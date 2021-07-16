package sber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sber.entity.CounterAgent;
import sber.model.CounterAgentModel;
import sber.repository.CounterAgentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CounterAgentCrudService {

    @Autowired
    private CounterAgentRepository counterAgentRepository;

    public List<CounterAgentModel> findAll() {
        List<CounterAgent> agentList = counterAgentRepository.findAll();
        List<CounterAgentModel> agents = new ArrayList<>();
        for (var agent: agentList) {
            agents.add(CounterAgentModel.builder()
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

    public void save(CounterAgentModel agent) {
        CounterAgent inputAgent = CounterAgent.from(agent);
        counterAgentRepository.save(inputAgent);
    }

    public void deleteById(Long id) {
        counterAgentRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        counterAgentRepository.deleteByName(name);
    }

    //убрать в конечном счете из круд-сервиса
    public CounterAgentModel findById(Long id) {
        Optional<CounterAgent> agentDAO = counterAgentRepository.findById(id);
        return CounterAgentModel.builder()
                .id(agentDAO.get().getId())
                .name(agentDAO.get().getName())
                .inn(agentDAO.get().getInn())
                .kpp(agentDAO.get().getKpp())
                .numberAccount(agentDAO.get().getNumberAccount())
                .bik(agentDAO.get().getBik())
                .build();
    }
}
