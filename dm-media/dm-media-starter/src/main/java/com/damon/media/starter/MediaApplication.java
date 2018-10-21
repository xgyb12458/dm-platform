package com.damon.media.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 媒体管理系统启动类
 * @author Damon S.
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@EntityScan(basePackages = {"com.damon.media",
        "org.axonframework.eventsourcing.eventstore.jpa",
        "org.axonframework.eventhandling.saga.repository.jpa",
        "org.axonframework.eventhandling.tokenstore.jpa"})
@EnableJpaRepositories(basePackages = {"com.damon.media"})
@SpringBootApplication(scanBasePackages = {"com.damon.media", "com.damon.shared"})
public class MediaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MediaApplication.class, args);
    }
}
