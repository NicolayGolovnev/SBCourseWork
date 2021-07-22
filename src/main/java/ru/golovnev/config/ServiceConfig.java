package ru.golovnev.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(value = "ru.golovnev.dao")
@EntityScan(value = "ru.golovnev.entity")
public class ServiceConfig {
}
