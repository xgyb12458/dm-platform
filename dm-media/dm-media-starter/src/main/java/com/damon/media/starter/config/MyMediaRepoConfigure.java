package com.damon.media.starter.config;

import com.damon.media.domain.app.aggregate.AppAggregate;
import com.damon.media.domain.slot.aggregate.SlotAggregate;
import com.damon.media.domain.slot.layout.aggregate.LayoutAggregate;
import com.damon.media.domain.slot.spec.aggregate.SpecAggregate;
import org.axonframework.modelling.command.Repository;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/***
 * @author Damon S.
 */
//@Configuration
public class MyMediaRepoConfigure {
    private final EventStore eventStore;

    public MyMediaRepoConfigure(EventStore eventStore) {
        this.eventStore = eventStore;
    }


    @Bean
    @Scope("prototype")
    public AppAggregate appAggregate(){
        return new AppAggregate();
    }

    @Bean
    @Scope("prototype")
    public SlotAggregate slotAggregate(){
        return new SlotAggregate();
    }

    @Bean
    @Scope("prototype")
    public SpecAggregate specAggregate(){
        return new SpecAggregate();
    }

    @Bean
    @Scope("prototype")
    public LayoutAggregate layoutAggregate(){
        return new LayoutAggregate();
    }

//    @Bean
//    public AggregateFactory<AppAggregate> appAggregateFactory() {
//        SpringPrototypeAggregateFactory<AppAggregate> factory = new SpringPrototypeAggregateFactory<>();
//        factory.setPrototypeBeanName("appAggregate");
//        return factory;
//    }
//
//    @Bean
//    public AggregateFactory<SlotAggregate> slotAggregateFactory() {
//        SpringPrototypeAggregateFactory<SlotAggregate> factory = new SpringPrototypeAggregateFactory<>();
//        factory.setPrototypeBeanName("slotAggregate");
//        return factory;
//    }
//
//    @Bean
//    public AggregateFactory<SpecAggregate> specAggregateFactory() {
//        SpringPrototypeAggregateFactory<SpecAggregate> factory = new SpringPrototypeAggregateFactory<>();
//        factory.setPrototypeBeanName("specAggregate");
//        return factory;
//    }
//
//    @Bean
//    public AggregateFactory<LayoutAggregate> layoutAggregateFactory() {
//        SpringPrototypeAggregateFactory<LayoutAggregate> factory = new SpringPrototypeAggregateFactory<>();
//        factory.setPrototypeBeanName("layoutAggregate");
//        return factory;
//    }
//
//    @Bean
//    public Repository<AppAggregate> appRepository() {
//        return new EventSourcingRepository<>(appAggregateFactory(), eventStore);
//    }
//
//    @Bean
//    public Repository<SlotAggregate> slotRepository() {
//        return new EventSourcingRepository<>(slotAggregateFactory(), eventStore);
//    }
//
//    @Bean
//    public Repository<SpecAggregate> specRepository() {
//        return new EventSourcingRepository<>(specAggregateFactory(), eventStore);
//    }
//
//    @Bean
//    public Repository<LayoutAggregate> layoutRepository() {
//        return new EventSourcingRepository<>(layoutAggregateFactory(), eventStore);
//    }
}
