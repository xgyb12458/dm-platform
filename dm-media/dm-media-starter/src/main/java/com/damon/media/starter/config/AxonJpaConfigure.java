package com.damon.media.starter.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.saga.repository.jpa.JpaSagaStore;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.queryhandling.DefaultQueryGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SimpleQueryBus;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;

/***
 * 基于JPA的配置
 * @author Damon S.
 */
//@Configuration
public class AxonJpaConfigure {
    private EntityManagerProvider entityManagerProvider;
    private final TransactionManager transactionManager;


    public AxonJpaConfigure(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Bean
    public CommandBus commandBus() {
        return new SimpleCommandBus();
    }

    @Bean
    public QueryGateway queryGateway() {
        return new DefaultQueryGateway(new SimpleQueryBus());
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    public CommandGateway commandGateway(CommandBus commandBus) {
        return new DefaultCommandGateway(commandBus);
    }

    @Bean
    public EventStorageEngine eventStorageEngine(Serializer serializer) {
        return new JpaEventStorageEngine(
                serializer,
                null,
                null,
                serializer,
                null,
                this.entityManagerProvider,
                this.transactionManager,
                null,
                null,
                true
        );
    }

    @Bean
    public Serializer serializer() {
        return new JacksonSerializer();
    }

    @Bean
    public EntityManagerProvider entityManagerProvider() {
        this.entityManagerProvider = new ContainerManagedEntityManagerProvider();
        return this.entityManagerProvider;
    }

    @Bean
    public EventStore eventStore(EventStorageEngine eventStorageEngine) {
        return new EmbeddedEventStore(eventStorageEngine);
    }

    @Bean
    public TokenStore tokenStore(Serializer serializer) {
        return new JpaTokenStore(this.entityManagerProvider, serializer);
    }

    @Bean
    public JpaSagaStore sagaStore(Serializer serializer) {
        return new JpaSagaStore(serializer, this.entityManagerProvider);
    }
}
