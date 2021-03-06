/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.validation.constraint;

import com.anosym.jflemax.validation.constraint.validator.NotEmptyValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author marembo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmpty {

  String message() default "The list must not be empty";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
