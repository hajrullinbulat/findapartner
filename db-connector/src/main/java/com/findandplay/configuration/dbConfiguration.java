package com.findandplay.configuration;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.findandplay.entity"})
@EnableJpaRepositories(basePackages = {"com.findandplay.repository"}, repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)
@EnableTransactionManagement
public class dbConfiguration {
}
