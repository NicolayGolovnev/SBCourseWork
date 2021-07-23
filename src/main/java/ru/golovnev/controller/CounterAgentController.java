package ru.golovnev.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.service.CounterAgentCrudService;
import ru.golovnev.service.CounterAgentFinderService;
import ru.golovnev.validation.group.OnCreate;
import ru.golovnev.validation.group.OnUpdate;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Контроллер для добавления, удаления, редактирования и поиска контрагентов
 */
@RestController
@Slf4j
@Api(tags = "Контроллер контрагента")
public class CounterAgentController {

    @Autowired
    private CounterAgentCrudService crudService;
    @Autowired
    private CounterAgentFinderService finderService;

    /**
     * GET-запрос загрузки страницы контрагентов
     * @param model модель страницы
     * @return модель для отображения конечной страницы с переданной информацией
     */
    @ApiOperation(value = "Загрузка страницы со списком контрагентов",
            notes = "Этот метод загружает страницы с таблицей контрагентов")
    @GetMapping("/counteragents")
    public ModelAndView getAllUsers(Model model) {
        List<CounterAgent> agentList = finderService.findAll();
        model.addAttribute("counterAgentsFromServer", agentList);
        model.addAttribute("deleteAgent", new CounterAgent());
        log.info("[GET /counteragents]\tReturn page with two model: CounterAgent and List<CounterAgent>");
        return new ModelAndView("/counteragents");
    }

    /**
     * GET-запрос добавления нового контрагента
     * @param counterAgent объект контрагента
     * @return модель для отображения конечной страницы с переданной информацией
     */
    @ApiOperation(value = "Загружает страницу с формой для добавления нового контрагента",
            notes = "Этот метод загружает страницу с формой создания")
    @GetMapping("/counteragents/new")
    public ModelAndView newAgent(@ModelAttribute("agentForm") CounterAgent counterAgent) {
        log.info("[GET /counteragents/new]\tReturn page with model CounterAgent");
        return new ModelAndView("/counteragents/new");
    }

    /**
     * POST-запрос сохранения нового контрагента
     * @param agentForm переданный контрагент для сохранения в БД
     * @param bindingResult ошибки валидации
     * @param model модель страницы
     * @return модель для отображения конечной страницы с переданной информацией
     */
    @ApiOperation(value = "Сохранение нового контрагента",
            notes = "Этот метод сохраняет нового контрагента")
    @PostMapping("/counteragents/new")
    public ModelAndView addAgent(@ModelAttribute("agentForm") @Validated(OnCreate.class) CounterAgent agentForm,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()){
            log.error("[POST /counteragents/new]\tBindingResult: errors of validation CounterAgent");
            for (var err: bindingResult.getAllErrors()) {
                log.error(err.toString());
            }
            model.addAttribute("agentForm", agentForm);
            return new ModelAndView("/counteragents/new");
        }

        crudService.save(agentForm);
        log.info("[POST /counteragents/new]\tRedirect to page /counteragents");
        return new ModelAndView("redirect:/counteragents");
    }

    /**
     * GET-запрос загрузки страницы обновления контрагента по ИД
     * @param id ИД контрагента
     * @param model модель страницы
     * @return модель для отображения конечной страницы с переданной информацией
     */
    @ApiOperation(value = "Перенаправляет на страницу обновления с моделью найденного контрагента",
            notes = "Этот метод перенаправляет на страницу обновления с моделью найденного контрагента")
    @GetMapping("/counteragents/update/{id}")
    public ModelAndView createAgentUpdateForm(
            @PathVariable("id") @Parameter(description = "ИД контрагента") Long id,
            Model model) {
        CounterAgent agent = finderService.findById(id);
        model.addAttribute("updateAgent", agent);
        log.info("[GET /counteragents/update/" + id + "]\tReturn page /update with CounterAgent");
        return new ModelAndView("/counteragents/update");
    }

    /**
     * GET-запрос загрузки страницы обновления контрагента
     * @param agent переданный контрагент для обновления
     * @return модель для отображения конечной страницы с переданной информацией
     */
    @ApiOperation(value = "Загружает страницу с формой обновления для контрагента",
            notes = "Этот метод загружает страницу с формой обновления для контрагента")
    @GetMapping("/counteragents/update")
    public ModelAndView updateAgent(@ModelAttribute("updateAgent") CounterAgent agent) {
        log.info("[GET /counteragents/update]\tReturn page with model CounterAgent");
        return new ModelAndView("/counteragents/update");
    }

    /**
     * POST-запрос обновления контрагент
     * @param agent переданный контрагент для обновления
     * @param bindingResult ошибки валидации
     * @param model модель страницы
     * @return модель для отображения конечной страницы с переданной информацией
     */
    @ApiOperation(value = "Обновление контрагента",
            notes = "Этот метод находит контрагента по ИД и обновляет информацию о нем")
    @PostMapping("/counteragents/update")
    public ModelAndView updateAgentPost(
            @ModelAttribute("updateAgent") @Validated(OnUpdate.class) CounterAgent agent,
            BindingResult bindingResult,
            Model model) {
        try {
            CounterAgent agentByName = finderService.findByName(agent.getName());
            if (!agentByName.getId().equals(agent.getId()))
                bindingResult.addError(new FieldError("updateAgent",
                        "name", "Такое наименование контрагента уже существует"));
        }
        catch (Exception ignored) { }
        if (bindingResult.hasErrors()) {
            log.error("[POST /counteragents/update]\tBindingResult: errors of validation CounterAgent");
            for (var err: bindingResult.getAllErrors()) {
                log.error(err.toString());
            }
            model.addAttribute("updateAgent", agent);
            return new ModelAndView("/counteragents/update");
        }
        crudService.update(agent);
        log.info("[POST /counteragents/update]\tRedirect to page /counteragents");
        return new ModelAndView("redirect:/counteragents");
    }

    /**
     * GET-запрос загрузки страницы контрагентов с удалением контрагента по ИД
     * @param id переданный ИД контрагента
     * @return модель для отображения конечной страницы с переданной информацией
     */
    @ApiOperation(value = "Удаляет контрагента по ИД и перенаправляет на страницу /counteragents",
            notes = "Этот метод удаляет контрагента по ИД и перенаправляет на страницу /counteragents")
    @GetMapping("/counteragents/delete/{id}")
    public ModelAndView deleteAgent(@PathVariable("id") @Parameter(description = "ИД контрагента") Long id) {
        crudService.deleteById(id);
        log.info("[GET /counteragents/delete/" + id + "]\tRedirect to page /counteragents");
        return new ModelAndView("redirect:/counteragents");
    }

    /**
     * GET-запрос загрузки страницы поиска контрагентов
     * @param model модель страницы
     * @return модель для отображения конечной страницы с переданной информацией
     */
    @ApiOperation(value = "Загружает страницу с формой для поиска контрагента",
            notes = "Этот метод загружает страницу с формой для поиска контрагента")
    @GetMapping("/find")
    public ModelAndView goToFindPage(Model model) {
        model.addAttribute("findByName", new CounterAgent());
        model.addAttribute("findByBikAndNumberAccount", new CounterAgent());
        log.info("[GET /find]\tReturn page with two model of CounterAgent");
        return new ModelAndView("/counteragents/find");
    }

    /**
     * POST-запрос поиска контрагенту по заданному полю
     * @param agent найденный контрагент по признаку
     * @param field признак для поиска контрагента
     * @param model модель страницы
     * @return модель для отображения конечной страницы с переданной информацией (результат поиска)
     */
    @ApiOperation(value = "Поиск контрагента по БИКу и номеру счета или по наименованию",
            notes = "Этот метод ищет контрагента и показывает на странице информацию о нем")
    @PostMapping("/find/{search}")
    public ModelAndView findAgent(
            @ModelAttribute("findByName") CounterAgent agent,
            @PathVariable("search") @Parameter(description = "Поле для идентификации поиска",
                    example = "byName или byBikAndNumberAccount") String field,
            Model model) {
        CounterAgent finder = new CounterAgent();
        if (field.equals("byName")) {
            finder = finderService.findByName(agent.getName());
        }
        else if (field.equals("byBikAndNumberAccount")) {
            finder = finderService.findByBikAndNumberAccount(agent.getBik(), agent.getNumberAccount());
        }

        model.addAttribute("finderAgent", finder);
        log.info("[POST /find/" + field + "]\tReturn page /showResult");
        return new ModelAndView("/counteragents/showResult");
    }

    /**
     * POST-запрос удаления контрагента по имени
     * @param agent удаляемый контрагент
     * @return модель для отображения конечной страницы с переданной информацией
     */
    @ApiOperation(value = "Удаляет контрагента по имени",
            notes = "Этот метод удаляет контрагента по имени")
    @PostMapping("/deleteByName")
    @Transactional
    public ModelAndView deleteAgentByName(@ModelAttribute("deleteAgent") CounterAgent agent) {
        crudService.deleteByName(agent.getName());
        log.info("[POST /counteragents/deleteByName]\tRedirect to page /counteragents");
        return new ModelAndView("redirect:/counteragents");
    }
}
