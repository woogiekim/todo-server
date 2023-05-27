package com.woogie.todo.core.support;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public abstract class SelfValidating {

    private final Validator validator;

    public SelfValidating() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    protected void validate() {
        var violations = validator.validate(this);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
