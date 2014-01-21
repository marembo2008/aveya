/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.jpa.converter;

import com.anosym.utilities.Utility;
import com.anosym.utilities.currency.Amount;
import java.math.BigDecimal;
import java.util.Currency;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author marembo
 */
@Converter(autoApply = true)
public class AmountJpaConverter implements AttributeConverter<Amount, String> {

  public String convertToDatabaseColumn(Amount attribute) {
    return attribute == null ? (attribute.getCurrency().getCurrencyCode() + ":" + attribute.getValue()) : null;
  }

  public Amount convertToEntityAttribute(String dbData) {
    if (Utility.isNullOrEmpty(dbData)) {
      return null;
    }
    String parts[] = dbData.split(":");
    Currency c = Currency.getInstance(parts[0]);
    BigDecimal value = new BigDecimal(parts[1]);
    return new Amount(value, c);
  }

}
