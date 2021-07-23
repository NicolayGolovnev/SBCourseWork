package ru.golovnev.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Конфигурация для подключения JPA-репозитория и сущности
 */
@Configuration
@EnableJpaRepositories(value = "ru.golovnev.dao")
@EntityScan(value = "ru.golovnev.entity")
public class ServiceConfig {
}
