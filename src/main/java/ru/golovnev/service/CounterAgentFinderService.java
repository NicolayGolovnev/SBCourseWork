package ru.golovnev.service;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.golovnev.dao.CounterAgentRepository;
import ru.golovnev.entity.CounterAgentEntity;
import ru.golovnev.exception.AgentNotFoundException;
import ru.golovnev.model.CounterAgent;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для поиска контрагентов в БД
 */
@Service
@Slf4j
public class CounterAgentFinderService {

    @Autowired
    private CounterAgentRepository repository;

    @Autowired
    private MapperFacade mapperFacade;

    /**
     * Метод для поиска всех контрагентов в БД
     * @return список контрагентов
     */
    public List<CounterAgent> findAll() {
        List<CounterAgentEntity> agentList = repository.findAll();
        List<CounterAgent> agents = mapperFacade.mapAsList(agentList, CounterAgent.class);
        log.info("[FinderService]\tFind the next agents: " + agentList);
        log.info("[FinderService]\tConverted next agents (by Orika): " + agents);
        return agents;
    }

    /**
     * Метод поиска контрагента по ИД
     * @param id ИД контрагента
     * @return объект модели контрагента
     * @throws AgentNotFoundException если не нашли контрагента по ИД
     */
    public CounterAgent findById(Long id) {
        Optional<CounterAgentEntity> agentDAO = repository.findById(id);
        if (agentDAO.isPresent()) {
            log.info("[FinderService]\tFind the next agent by id: " + agentDAO);
            CounterAgent finderAgent = mapperFacade.map(agentDAO.get(), CounterAgent.class);
            log.info("[FinderService]\tConverted to the agent (by Orika): " + finderAgent);
            return finderAgent;
        }
        else
            throw new AgentNotFoundException("Agent[id = " + id + "] could not find in repository");
    }

    /**
     * Метод поиска контрагента по наименованию
     * @param name наименование контрагента
     * @return объект модели контрагента
     * @throws AgentNotFoundException если не нашли контрагента по наименованию
     */
    public CounterAgent findByName(String name) {
        Optional<CounterAgentEntity> agentDAO = repository.findFirstByName(name);
        if (agentDAO.isPresent()) {
            log.info("[FinderService]\tFind the next agent by name:" + agentDAO);
            CounterAgent finderAgent = mapperFacade.map(agentDAO.get(), CounterAgent.class);
            log.info("[FinderService]\tConverted to the agent (by Orika): " + finderAgent);
            return finderAgent;
        }
        else
            throw new AgentNotFoundException("Agent[name = " + name + "] could not find in repository");
    }

    /**
     * Метод поиска контрагента по БИКу и номеру счета
     * @param bik БИК контрагента
     * @param numberAccount номер счета контрагента
     * @return объект модели контрагента
     * @throws AgentNotFoundException если не нашли контрагента по БИКу и номеру счета
     */
    public CounterAgent findByBikAndNumberAccount(String bik, String numberAccount) {
        Optional<CounterAgentEntity> agentDAO = repository.findFirstByBikAndNumberAccount(bik, numberAccount);
        if (agentDAO.isPresent()) {
            log.info("[FinderService]\tFind the next agent by bik and account:" + agentDAO);
            CounterAgent finderAgent = mapperFacade.map(agentDAO.get(), CounterAgent.class);
            log.info("[FinderService]\tConverted to the agent (by Orika): " + finderAgent);
            return finderAgent;
        }
        else
            throw new AgentNotFoundException("Agent[bik = " + bik + ", account = " + numberAccount +
                    "] could not find in repository");
    }
}
