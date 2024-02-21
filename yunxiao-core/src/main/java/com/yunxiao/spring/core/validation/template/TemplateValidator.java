package com.yunxiao.spring.core.validation.template;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author LuoYunXiao
 * @since 2023/9/14 11:19
 */
public class TemplateValidator implements ConstraintValidator<TemplateValidation, Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
