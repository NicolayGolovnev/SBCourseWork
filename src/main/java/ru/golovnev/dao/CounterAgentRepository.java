package ru.golovnev.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.golovnev.entity.CounterAgentEntity;

import java.util.Optional;

public interface CounterAgentRepository extends JpaRepository<CounterAgentEntity, Long> {
    void deleteByName(String name);
    Optional<CounterAgentEntity> findFirstByName(String name);
    Optional<CounterAgentEntity> findFirstByBikAndNumberAccount(String bik, String numberAccount);
}
