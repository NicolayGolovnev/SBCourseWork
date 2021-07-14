package sber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sber.entity.CounterAgent;
import sber.repository.CounterAgentRepository;

import java.util.List;

@Service
public class CounterAgentService {

    @Autowired
    private CounterAgentRepository counterAgentRepository;

    public List<CounterAgent> findAll() {
        return counterAgentRepository.findAll();
    }

    public void save(CounterAgent agent) {
        counterAgentRepository.save(agent);
    }
}
