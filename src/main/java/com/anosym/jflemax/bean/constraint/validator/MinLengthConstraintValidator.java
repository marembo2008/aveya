/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.bean.constraint.validator;

import com.anosym.jflemax.bean.constraint.MinLength;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author marembo
 */
public class MinLengthConstraintValidator implements ConstraintValidator<MinLength, String> {

  private MinLength length;

  public void initialize(MinLength a) {
    length = a;
  }

  public boolean isValid(String t, ConstraintValidatorContext cvc) {
    return t != null && t.length() > length.value();
  }

}
