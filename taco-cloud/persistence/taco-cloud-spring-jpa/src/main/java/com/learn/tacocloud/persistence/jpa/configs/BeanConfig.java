package com.learn.tacocloud.persistence.jpa.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.learn.tacocloud.persistence.jpa"})
@EntityScan(basePackages = {"com.learn.tacocloud.persistence.jpa"})
@Configuration
public class BeanConfig {
}
