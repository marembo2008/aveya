/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.jpa.converter;

import com.anosym.utilities.Utility;
import com.anosym.utilities.currency.CurrencyCode;
import com.anosym.utilities.currency.Money;
import java.math.BigDecimal;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author marembo
 */
@Converter(autoApply = true)
public class MoneyJpaConverter implements AttributeConverter<Money, String> {

  public String convertToDatabaseColumn(Money attribute) {
    return attribute != null
            ? attribute.getCurrency().getCountryCode().getIsoCode() + ":" + attribute.getCurrency().getCurrencySymbol() + ":" + attribute.getValue()
            : null;
  }

  public Money convertToEntityAttribute(String dbData) {
    if (Utility.isNullOrEmpty(dbData)) {
      return null;
    }
    String parts[] = dbData.split(":");
    CurrencyCode c = Utility.findCurrencyCodeFromCountryIsoCodeAndCurrencySymbol(parts[0], parts[1]);
    BigDecimal value = new BigDecimal(parts[2]);
    return new Money(value, c);
  }

}
