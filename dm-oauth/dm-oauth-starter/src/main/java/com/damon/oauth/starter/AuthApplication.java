package com.damon.oauth.starter;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.axonframework.springboot.autoconfig.AxonServerAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 权限管理系统
 * @author Damon S.
 */
@EnableJpaAuditing
@EnableSwagger2Doc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@EntityScan(basePackages = {"com.damon.oauth",
        "org.axonframework.eventsourcing.eventstore.jpa",
        "org.axonframework.modelling.saga.repository.jpa",
        "org.axonframework.eventhandling.tokenstore.jpa"})
@EnableJpaRepositories(basePackages = {"com.damon.oauth"})
@SpringBootApplication(exclude = AxonServerAutoConfiguration.class,
        scanBasePackages = {"com.damon.oauth", "com.damon.shared"})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
