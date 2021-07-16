package sber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sber.entity.CounterAgent;

public interface CounterAgentRepository extends JpaRepository<CounterAgent, Long> {
    void deleteByName(String name);
}
