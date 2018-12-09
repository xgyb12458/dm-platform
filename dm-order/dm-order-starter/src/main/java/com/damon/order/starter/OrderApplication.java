package com.damon.order.starter;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.axonframework.springboot.autoconfig.AxonServerAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 订单管理系统
 * @author Damon S.
 */
@EnableSwagger2Doc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@EntityScan(basePackages = {"com.damon.order",
        "org.axonframework.eventsourcing.eventstore.jpa",
        "org.axonframework.modelling.saga.repository.jpa",
        "org.axonframework.eventhandling.tokenstore.jpa"})
@EnableJpaRepositories(basePackages = {"com.damon.order"})
@SpringBootApplication(exclude = AxonServerAutoConfiguration.class,
        scanBasePackages = {"com.damon.order", "com.damon.shared"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
