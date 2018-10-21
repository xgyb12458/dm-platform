package com.damon.shared.validation;

import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.exception.SystemException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Damon S.
 */
@Aspect
@Component
public final class ArgsValidator {
    private static Validator validator =
            Validation.buildDefaultValidatorFactory().getValidator();


    @Before("@annotation(com.damon.shared.validation.ArgsValid)")
    public void validateArgs(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (ObjectUtils.isEmpty(args)) { return; }

        List<String> messages = Arrays.stream(args).map(ArgsValidator::validate).flatMap(List::stream).collect(Collectors.toList());
        if (!ObjectUtils.isEmpty(messages)) {
            throw new SystemException(ResponseCodeEnum.BAD_REQUEST.getCode(), messages.toString());
        }
    }

    private static <T> List<String> validate(T t) {
        return validator.validate(t).stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }

}
