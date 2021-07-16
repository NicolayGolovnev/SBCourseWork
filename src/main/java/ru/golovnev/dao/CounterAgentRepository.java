package ru.golovnev.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.golovnev.entity.CounterAgentEntity;

public interface CounterAgentRepository extends JpaRepository<CounterAgentEntity, Long> {
    void deleteByName(String name);
}
