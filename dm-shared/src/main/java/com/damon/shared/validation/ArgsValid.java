package com.damon.shared.validation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

@Mapping
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ArgsValid {
}