package ru.golovnev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.golovnev.dao.CounterAgentRepository;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.service.CounterAgentCrudService;
import ru.golovnev.service.CounterAgentFinderService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестирование сервиса по поиску контрагентов
 */
@SpringBootTest
public class FinderServiceTest {
    @Autowired
    private CounterAgentCrudService crudService;

    @Autowired
    private CounterAgentFinderService finderService;

    @Autowired
    private CounterAgentRepository repository;

    @Test
    @DisplayName("Testing a finder-method findAll()")
    @Transactional
    void testFindAll() {
        repository.deleteAll();
        int counter = 0;
        for (int i = 0; i < Math.random() * 20; i++) {
            CounterAgent agent = CounterAgent.builder()
                    .name("test" + i)
                    .inn("123")
                    .kpp("123")
                    .numberAccount("123")
                    .bik("123")
                    .build();
            crudService.save(agent);
            counter++;
        }
        assertEquals(counter, finderService.findAll().size());
        repository.deleteAll();
    }

    @Test
    @DisplayName("Testing a finder-method findByName()")
    @Transactional
    void testFindById() {
        CounterAgent agent = CounterAgent.builder()
                .name("test99")
                .inn("123")
                .kpp("765")
                .numberAccount("345")
                .bik("123")
                .build();
        crudService.save(agent);
        agent = finderService.findByName("test99");
        assertEquals(agent, finderService.findById(agent.getId()));
        crudService.deleteById(agent.getId());
    }

    @Test
    @DisplayName("Testing a finder-method findByName()")
    @Transactional
    void testFindByName() {
        CounterAgent agent = CounterAgent.builder()
                .name("test11")
                .inn("123")
                .kpp("125553")
                .numberAccount("2222")
                .bik("777")
                .build();
        crudService.save(agent);
        agent = finderService.findByBikAndNumberAccount(agent.getBik(), agent.getNumberAccount());
        assertEquals(agent, finderService.findByName(agent.getName()));
        crudService.deleteById(agent.getId());
    }

    @Test
    @DisplayName("Testing a finder-method findByBikAndNumberAccount()")
    @Transactional
    void testFindByBikAndAccount() {
        CounterAgent agent = CounterAgent.builder()
                .name("113test")
                .inn("98796")
                .kpp("7567")
                .numberAccount("2452346")
                .bik("23523452345")
                .build();
        crudService.save(agent);
        agent = finderService.findByName(agent.getName());
        assertEquals(agent, finderService.findByBikAndNumberAccount(agent.getBik(), agent.getNumberAccount()));
        crudService.deleteById(agent.getId());
    }
}
