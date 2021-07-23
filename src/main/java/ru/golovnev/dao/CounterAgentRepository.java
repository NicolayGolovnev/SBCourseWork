package ru.golovnev.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.golovnev.entity.CounterAgentEntity;

import java.util.Optional;

/**
 * JPA-репозиторий, взаимодействующий с контрагентами
 */
public interface CounterAgentRepository extends JpaRepository<CounterAgentEntity, Long> {
    /**
     * Метод для удаления контрагента по имени
     * @param name имя контрагента
     */
    void deleteByName(String name);

    /**
     * Метод поиска контрагента по имени
     * @param name имя контрагента
     * @return объект найденного контрагента в обертке {@link Optional}
     */
    Optional<CounterAgentEntity> findFirstByName(String name);

    /**
     * Метод поиска контрагента по БИКу и номеру счета
     * @param bik БИК банка контрагента
     * @param numberAccount номер счета контрагента
     * @return объект найденного контрагента в обертке {@link Optional}
     */
    Optional<CounterAgentEntity> findFirstByBikAndNumberAccount(String bik, String numberAccount);
}
