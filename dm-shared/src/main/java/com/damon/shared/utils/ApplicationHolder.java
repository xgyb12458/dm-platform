package com.damon.shared.utils;

import com.damon.shared.exception.SystemException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.NotNull;

/**
 * @author Damon S.
 */
@Component
public class ApplicationHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext context) throws BeansException {
        ApplicationHolder.context = context;
    }

    public static <T> T getBean(Class<T> clazz) {
        if (ObjectUtils.isEmpty(context)) {
            throw new SystemException("context is null");
        }
        return context.getBean(clazz);
    }
}
