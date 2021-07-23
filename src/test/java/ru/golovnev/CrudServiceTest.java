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
 * Тестирование CRUD-сервиса приложения
 */
@SpringBootTest
public class CrudServiceTest {
    @Autowired
    private CounterAgentCrudService crudService;

    @Autowired
    private CounterAgentFinderService finderService;

    @Autowired
    private CounterAgentRepository repository;

    @Test
    @DisplayName("Testing a crud-method save()")
    @Transactional
    void testSave() {
        List<CounterAgent> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CounterAgent agent = CounterAgent.builder()
                    .name("test" + i)
                    .inn("123")
                    .kpp("123")
                    .numberAccount("123")
                    .bik("123")
                    .build();
            list.add(agent);
            crudService.save(agent);
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(repository.findFirstByName(list.get(i).getName()).isPresent());
            repository.deleteByName(list.get(i).getName());
        }
    }

    @Test
    @DisplayName("Testing a crud-method update()")
    @Transactional
    void testUpdate() {
        CounterAgent agent = CounterAgent.builder()
                .name("123")
                .inn("123")
                .kpp("123")
                .numberAccount("123")
                .bik("123")
                .build();
        crudService.save(agent);
        agent = finderService.findByName("123");
        CounterAgent agentUpdate = CounterAgent.builder()
                .id(agent.getId())
                .name("456")
                .inn("456")
                .kpp("456")
                .numberAccount("456")
                .bik("456")
                .build();
        crudService.update(agentUpdate);
        assertTrue(repository.findById(agentUpdate.getId()).isPresent());
        assertEquals(agentUpdate.getName(), repository.findById(agentUpdate.getId()).get().getName());
        assertEquals(agentUpdate.getInn(), repository.findById(agentUpdate.getId()).get().getInn());
        assertEquals(agentUpdate.getKpp(), repository.findById(agentUpdate.getId()).get().getKpp());
        assertEquals(agentUpdate.getNumberAccount(), repository.findById(agentUpdate.getId()).get().getNumberAccount());
        assertEquals(agentUpdate.getBik(), repository.findById(agentUpdate.getId()).get().getBik());
        repository.deleteByName(agentUpdate.getName());
    }

    @Test
    @DisplayName("Testing a crud-method deleteById()")
    @Transactional
    void testDeleteById() {
        CounterAgent agent = CounterAgent.builder()
                .name("123")
                .inn("123")
                .kpp("123")
                .numberAccount("123")
                .bik("123")
                .build();
        crudService.save(agent);
        agent = finderService.findByName("123");
        CounterAgent finalAgent = agent;
        assertDoesNotThrow(() -> crudService.deleteById(finalAgent.getId()));
    }

    @Test
    @DisplayName("Testing a crud-method deleteByName()")
    @Transactional
    void testDeleteByName() {
        CounterAgent agent = CounterAgent.builder()
                .name("456")
                .inn("987")
                .kpp("987")
                .numberAccount("987")
                .bik("987")
                .build();
        crudService.save(agent);
        agent = finderService.findByBikAndNumberAccount("987", "987");
        CounterAgent finalAgent = agent;
        assertDoesNotThrow(() -> crudService.deleteByName(finalAgent.getName()));
    }
}
