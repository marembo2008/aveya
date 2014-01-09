/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.validation.constraint.validator;

import com.anosym.jflemax.validation.constraint.MaxLength;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author marembo
 */
public class MaxLengthConstraintValidator implements ConstraintValidator<MaxLength, String> {

  private MaxLength length;

  public void initialize(MaxLength a) {
    length = a;
  }

  public boolean isValid(String t, ConstraintValidatorContext cvc) {
    return t == null || t.length() < length.value();
  }

}
