package com.learn.tacocloud.persistence.mongodb.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = {"com.learn.tacocloud.persistence.mongodb"})
@EntityScan(basePackages = {"com.learn.tacocloud.persistence.mongodb"})
@Configuration
public class BeanConfig {
}
