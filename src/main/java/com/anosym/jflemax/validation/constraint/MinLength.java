/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.validation.constraint;

import com.anosym.jflemax.validation.constraint.validator.MinLengthConstraintValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * can only be on a string field. The length of the string must not be lesser than the specified
 * value. The value cannot be null.
 *
 * @author marembo
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = MinLengthConstraintValidator.class)
public @interface MinLength {

  String message() default "Value character length is less than specified value";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  int value();
}
