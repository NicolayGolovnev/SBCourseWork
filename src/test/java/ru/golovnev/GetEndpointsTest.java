package ru.golovnev;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.golovnev.exception.AgentNotFoundException;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.service.CounterAgentCrudService;
import ru.golovnev.service.CounterAgentFinderService;


import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестирование эндпоинтов ГЕТ-запросов приложения
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GetEndpointsTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CounterAgentCrudService crudService;

    @Autowired
    private CounterAgentFinderService finderService;

    @Test
    @DisplayName("[GET /counteragents] Return page with agents")
    void getCounteragents() throws Exception {
        mvc.perform(get("/counteragents")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("[GET /counteragents/delete/{id}] Delete agent by id")
    @Transactional
    void deleteAgentById() throws Exception {
        CounterAgent agent = CounterAgent.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .numberAccount("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(agent);
        agent = finderService.findByName("test123987");
        mvc.perform(get("/counteragents/delete/" + agent.getId().toString())
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().is3xxRedirection());
        CounterAgent finalAgent = agent;
        assertThrows(AgentNotFoundException.class, () -> crudService.deleteById(finalAgent.getId()));
    }

    @Test
    @DisplayName("[GET /counteragents/new] Return page with form-agent")
    void getFormCreate() throws Exception {
        mvc.perform(get("/counteragents/new")
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("[GET /counteragents/update] Return page with update form for agent")
    void getFormUpdate() throws Exception {
        mvc.perform(get("/counteragents/update")
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("[GET /counteragents/update/{id}] Return page with updating form-agent")
    @Transactional
    void getRedirectUpdateForm() throws Exception {
        CounterAgent agent = CounterAgent.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .numberAccount("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(agent);
        agent = finderService.findByName("test123987");

        mvc.perform(get("/counteragents/update/" + agent.getId())
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk());
        crudService.deleteByName("test123987");
    }

    @Test
    @DisplayName("[GET /find] Return finder page")
    void getFinderPage() throws Exception {
        mvc.perform(get("/find")
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk());
    }
}
