package com.anosym.jflemax.validation.constraint.validator;

import com.anosym.jflemax.validation.constraint.Preference;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author marembo
 */
public class PreferenceConstraintValidator implements ConstraintValidator<Preference, Object> {

    private Class<? extends PreferenceHandler> validator;

    public void initialize(Preference constraintAnnotation) {
        validator = constraintAnnotation.handler();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            PreferenceHandler pv = validator.newInstance();
            return pv.isValid(value);
        } catch (Exception ex) {
            throw new RuntimeException("Erro validating value: " + value, ex);
        }
    }
}
