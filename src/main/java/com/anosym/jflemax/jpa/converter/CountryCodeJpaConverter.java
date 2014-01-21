/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.jpa.converter;

import com.anosym.utilities.Utility;
import com.anosym.utilities.geocode.CountryCode;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author marembo
 */
@Converter(autoApply = true)
public class CountryCodeJpaConverter implements AttributeConverter<CountryCode, String> {

  public String convertToDatabaseColumn(CountryCode attribute) {
    return attribute == null ? null : attribute.getIsoCode();
  }

  public CountryCode convertToEntityAttribute(String dbData) {
    return Utility.isNullOrEmpty(dbData) ? null : Utility.findCountryCodeFromCountryIsoCode(dbData);
  }
}
