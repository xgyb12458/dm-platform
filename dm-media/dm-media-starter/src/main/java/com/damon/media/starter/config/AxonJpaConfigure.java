//package com.damon.media.starter.config;
//
//import org.axonframework.commandhandling.CommandBus;
//import org.axonframework.commandhandling.SimpleCommandBus;
//import org.axonframework.commandhandling.gateway.CommandGateway;
//import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
//import org.axonframework.common.jpa.EntityManagerProvider;
//import org.axonframework.common.transaction.TransactionManager;
//import org.axonframework.eventhandling.EventBus;
//import org.axonframework.eventhandling.SimpleEventBus;
//import org.axonframework.eventhandling.tokenstore.TokenStore;
//import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
//import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
//import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
//import org.axonframework.eventsourcing.eventstore.EventStore;
//import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
//import org.axonframework.modelling.saga.repository.jpa.JpaSagaStore;
//import org.axonframework.queryhandling.DefaultQueryGateway;
//import org.axonframework.queryhandling.QueryGateway;
//import org.axonframework.serialization.Serializer;
//import org.axonframework.serialization.json.JacksonSerializer;
//import org.axonframework.springboot.util.jpa.ContainerManagedEntityManagerProvider;
//import org.springframework.context.annotation.Bean;
//
///***
// * 基于JPA的配置
// * @author Damon S.
// */
////@Configuration
//public class AxonJpaConfigure {
//    private EntityManagerProvider entityManagerProvider;
//    private final TransactionManager transactionManager;
//
//
//    public AxonJpaConfigure(TransactionManager transactionManager) {
//        this.transactionManager = transactionManager;
//    }
//
//    @Bean
//    public CommandBus commandBus() {
//        return SimpleCommandBus.builder().build();
//    }
//
//    @Bean
//    public QueryGateway queryGateway() {
//        return DefaultQueryGateway.builder().build();//(SimpleQueryBus.builder().build());
//    }
//
//    @Bean
//    public EventBus eventBus() {
//        return SimpleEventBus.builder().build();
//    }
//
//    @Bean
//    public CommandGateway commandGateway(CommandBus commandBus) {
//        return DefaultCommandGateway.builder().build();
//    }
//
//    @Bean
//    public EventStorageEngine eventStorageEngine(Serializer serializer) {
//        return JpaEventStorageEngine.builder().build();
//    }
//
//    @Bean
//    public Serializer serializer() {
//        return JacksonSerializer.builder().build();
//    }
//
//    @Bean
//    public EntityManagerProvider entityManagerProvider() {
//        this.entityManagerProvider = new ContainerManagedEntityManagerProvider();
//        return this.entityManagerProvider;
//    }
//
//    @Bean
//    public EventStore eventStore(EventStorageEngine eventStorageEngine) {
//        return EmbeddedEventStore.builder().build();
//    }
//
//    @Bean
//    public TokenStore tokenStore(Serializer serializer) {
//        return JpaTokenStore.builder().build();
//    }
//
//    @Bean
//    public JpaSagaStore sagaStore(Serializer serializer) {
//        return JpaSagaStore.builder().build();
//    }
//}
