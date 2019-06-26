package com.damon.shared.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义切面注解，用于定义审计点和操作日志点
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年06月09日 13:41
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditOperation {
    String title() default "";
    String type();
}