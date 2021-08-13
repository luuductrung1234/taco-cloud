package com.learn.tacocloud.persistence.springjdbc.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@EnableJdbcRepositories(basePackages = {"com.learn.tacocloud.persistence.springjdbc"})
@EntityScan(basePackages = {"com.learn.tacocloud.persistence.springjdbc"})
@Configuration
public class BeanConfig {
}
