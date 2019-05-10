package com.damon.oauth.starter.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

/**
 * 获取当前用户ID，用于数据表的审计
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年05月02日 22:57
 */
@Configuration
public class CurrentAuditorConfig implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        SecurityContext context = SecurityContextHolder.getContext();

        Optional<Long> currentAuditor = Optional.empty();
        if (Objects.isNull(context)
                || Objects.isNull(context.getAuthentication())
                || Objects.isNull(context.getAuthentication().getPrincipal())) {
            return currentAuditor;
        }
        Object principal = context.getAuthentication().getPrincipal();
        if (principal.getClass().isAssignableFrom(Long.class)) {
            currentAuditor = Optional.of((Long) principal);
        }
        return currentAuditor;
    }
}
