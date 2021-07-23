package ru.golovnev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.golovnev.exception.AgentNotFoundException;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.service.CounterAgentCrudService;
import ru.golovnev.service.CounterAgentFinderService;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тестирование эндпоинтов ПОСТ-запросов приложения
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PostEndpointTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CounterAgentCrudService crudService;

    @Autowired
    private CounterAgentFinderService finderService;

    @Test
    @DisplayName("[POST /new] Create a full validated counteragent")
    @Transactional
    void createPage() throws Exception {
        CounterAgent agent = CounterAgent.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .numberAccount("40817810902003859041")
                .bik("040173604")
                .build();

        mvc.perform(post("/counteragents/new")
                .contentType("application/x-www-form-urlencoded")
                .param("name", agent.getName())
                .param("inn", agent.getInn())
                .param("kpp", agent.getKpp())
                .param("numberAccount", agent.getNumberAccount())
                .param("bik", agent.getBik()))
                .andExpect(status().is3xxRedirection());
        crudService.deleteByName("test123987");
    }

    @Test
    @DisplayName("[POST /new] Failed validation by name and number account")
    @Transactional
    void failValidOnCreatePage() throws Exception {
        CounterAgent agent = CounterAgent.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .numberAccount("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(agent);

        MvcResult mvcResult = mvc.perform(post("/counteragents/new")
                .contentType("application/x-www-form-urlencoded")
                .param("name", agent.getName())
                .param("inn", agent.getInn())
                .param("kpp", agent.getKpp())
                .param("numberAccount", agent.getNumberAccount())
                .param("bik", agent.getBik()))
                .andReturn();

        assertEquals("/counteragents/new", mvcResult.getModelAndView().getViewName());
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        crudService.deleteByName("test123987");
    }

    @Test
    @DisplayName("[POST /update] Update a full validated counteragent")
    @Transactional
    void updatePage() throws Exception {
        CounterAgent agent = CounterAgent.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .numberAccount("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(agent);
        CounterAgent finderAgent = finderService.findByName("test123987");

        mvc.perform(post("/counteragents/update")
                .contentType("application/x-www-form-urlencoded")
                .param("id", finderAgent.getId().toString())
                .param("name", finderAgent.getName())
                .param("inn", finderAgent.getInn())
                .param("kpp", finderAgent.getKpp())
                .param("numberAccount", finderAgent.getNumberAccount())
                .param("bik", finderAgent.getBik()))
                .andExpect(status().is3xxRedirection());
        crudService.deleteByName("test123987");
    }

    @Test
    @DisplayName("[POST /update] Failed validation by kpp")
    @Transactional
    void failValidOnUpdatePage() throws Exception {
        CounterAgent agent = CounterAgent.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .numberAccount("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(agent);
        CounterAgent finderAgent = finderService.findByName("test123987");
        agent.setKpp("12345678");

        MvcResult mvcResult = mvc.perform(post("/counteragents/update")
                .contentType("application/x-www-form-urlencoded")
                .param("id", agent.getId().toString())
                .param("name", agent.getName())
                .param("inn", agent.getInn())
                .param("kpp", agent.getKpp())
                .param("numberAccount", agent.getNumberAccount())
                .param("bik", agent.getBik()))
                .andReturn();

        assertEquals("/counteragents/update", mvcResult.getModelAndView().getViewName());
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        crudService.deleteByName("test123987");
    }

    @Test
    @DisplayName("[POST /deleteByName] Delete a existing counteragent")
    @Transactional
    void deleteByNamePage() throws Exception {
        CounterAgent agent = CounterAgent.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .numberAccount("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(agent);
        CounterAgent finderAgent = finderService.findByName("test123987");

        mvc.perform(post("/deleteByName")
                .contentType("application/x-www-form-urlencoded")
                .param("name", finderAgent.getName()))
                .andExpect(status().is3xxRedirection());
        assertThrows(AgentNotFoundException.class, () -> finderService.findByName("test123987"));
    }

    @Test
    @DisplayName("[POST /find/{field}] Find a counteragent by first field")
    @Transactional
    void findByFieldName() throws Exception {
        CounterAgent agent = CounterAgent.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .numberAccount("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(agent);
        CounterAgent finderAgent = finderService.findByName("test123987");

        mvc.perform(post("/find/" + "byName")
                .contentType("application/x-www-form-urlencoded")
                .param("name", finderAgent.getName()))
                .andExpect(status().isOk());
        crudService.deleteByName("test123987");
    }

    @Test
    @DisplayName("[POST /find/{field}] Find a counteragent by second field")
    @Transactional
    void findByFieldBikAndAccount() throws Exception {
        CounterAgent agent = CounterAgent.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .numberAccount("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(agent);
        CounterAgent finderAgent = finderService.findByName("test123987");

        mvc.perform(post("/find/" + "byBikAndNumberAccount")
                .contentType("application/x-www-form-urlencoded")
                .param("bik", finderAgent.getBik())
                .param("numberAccount", finderAgent.getNumberAccount()))
                .andExpect(status().isOk());
        crudService.deleteByName("test123987");
    }

    @Test
    @DisplayName("[POST /find/{field}] Failed find a counteragent by field")
    @Transactional
    void failFindByFieldName() throws Exception {
        CounterAgent agent = CounterAgent.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .numberAccount("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(agent);
        CounterAgent finderAgent = finderService.findByName("test123987");

        mvc.perform(post("/find/" + "byName")
                .contentType("application/x-www-form-urlencoded")
                .param("name", finderAgent.getName() + "test"))
                .andExpect(status().isNotFound());
        crudService.deleteByName("test123987");
    }
}
