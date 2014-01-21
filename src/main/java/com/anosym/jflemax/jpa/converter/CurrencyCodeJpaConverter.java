/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.jpa.converter;

import com.anosym.utilities.Utility;
import com.anosym.utilities.currency.CurrencyCode;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author marembo
 */
@Converter(autoApply = true)
public class CurrencyCodeJpaConverter implements AttributeConverter<CurrencyCode, String> {

  public String convertToDatabaseColumn(CurrencyCode attribute) {
    if (attribute == null) {
      return null;
    }
    return attribute.getCurrencySymbol() + ":" + attribute.getCountryCode().getIsoCode();
  }

  public CurrencyCode convertToEntityAttribute(String dbData) {
    if (Utility.isNullOrEmpty(dbData)) {
      return null;
    }
    String parts[] = dbData.split(":");
    return Utility.findCurrencyCodeFromCountryIsoCodeAndCurrencySymbol(parts[0], parts[1]);
  }

}
