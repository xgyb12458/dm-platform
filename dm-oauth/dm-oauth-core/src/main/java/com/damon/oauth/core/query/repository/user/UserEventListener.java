package com.damon.oauth.core.query.repository.user;

import com.damon.oauth.domain.user.entity.QUserEntry;
import com.damon.oauth.domain.user.entity.UserEntry;
import com.damon.oauth.domain.user.entity.UserRepository;
import com.damon.oauth.domain.user.event.UserCreatedEvent;
import com.damon.oauth.domain.user.event.UserUpdatedEvent;
import com.damon.shared.common.Constants;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;

/**
 * 用户事件侦听
 * @author Damon S.
 */
@Slf4j
@Component
public class UserEventListener {

    private final JPAQueryFactory jpaQueryFactory;
    private final UserRepository userRepository;
    private final QUserEntry qUserEntry;


    public UserEventListener(EntityManagerProvider managerProvider,
                             UserRepository userRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.userRepository = userRepository;
        this.qUserEntry = QUserEntry.userEntry;
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(UserCreatedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling UserCreatedEvent persistence process, parameters：{}", event.toString());

        UserEntry userEntry = UserEntry.builder()
                .userId(event.getUserId().getValue())
                .userName(event.getUserName())
                .nickName(event.getNickName())
                .password(event.getPassword())
                .phoneNo(event.getPhoneNo())
                .email(event.getEmail())
                .type(event.getType().name())
                .state(event.getState().name())
                .salt(event.getSalt())
                .tenantId(event.getTenantId().getValue())
                .createdBy(event.getCreatedBy())
                .createdAt(event.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .build();

        this.userRepository.saveAndFlush(userEntry);
        log.info(Constants.PREFIX_PRODUCT + "========>>User aggregate[Id:{}] created by User[Id:{}] at {} is successfully stored-[DB].",
                event.getUserId().getValue(), event.getCreatedBy(), event.getCreatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(UserUpdatedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling UserUpdatedEvent persistence process, parameters：{}", event.toString());

        this.jpaQueryFactory.update(qUserEntry)
                .set(qUserEntry.nickName, event.getNickName())
                .set(qUserEntry.phoneNo, event.getPhoneNo())
                .set(qUserEntry.email, event.getEmail())
                .set(qUserEntry.tenantId, event.getTenantId().getValue())
                .set(qUserEntry.rolesJson, event.getRolesJson())
                .set(qUserEntry.updatedBy, event.getUpdatedBy())
                .set(qUserEntry.updatedAt, event.getUpdatedAt().toEpochSecond(ZoneOffset.UTC))
                .where(qUserEntry.userId.eq(event.getUserId().getValue()))
                .execute();

        log.info(Constants.PREFIX_PRODUCT + "========>>User aggregate[Id:{}] updated by User[Id:{}] at {} is successfully stored-[DB].",
                event.getUserId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }
}
