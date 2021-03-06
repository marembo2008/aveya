package com.anosym.jflemax.validation.constraint;

import com.anosym.jflemax.validation.constraint.validator.PreferenceHandler;
import com.anosym.jflemax.validation.constraint.validator.PreferenceConstraintValidator;
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
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = PreferenceConstraintValidator.class)
public @interface Preference {

    String message() default "Value is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends PreferenceHandler> handler();
}
