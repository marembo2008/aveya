package com.anosym.jflemax.validation.constraint.validator;

/**
 *
 * @author marembo
 * @param <T>
 */
public interface PreferenceHandler<T> {

  boolean isValid(T value);
}
