package com.learn.tacocloud.persistence.cassandra.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableCassandraRepositories(basePackages = {"com.learn.tacocloud.persistence.cassandra"})
@EntityScan(basePackages = {"com.learn.tacocloud.persistence.cassandra"})
@Configuration
public class BeanConfig {
}
