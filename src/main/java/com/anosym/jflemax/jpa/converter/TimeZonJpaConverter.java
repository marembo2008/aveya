/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.jpa.converter;

import com.anosym.utilities.Utility;
import java.util.TimeZone;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author marembo
 */
@Converter(autoApply = true)
public class TimeZonJpaConverter implements AttributeConverter<TimeZone, String> {

  public String convertToDatabaseColumn(TimeZone attribute) {
    return attribute == null ? null : attribute.getID();
  }

  public TimeZone convertToEntityAttribute(String dbData) {
    return Utility.isNullOrEmpty(dbData) ? null : TimeZone.getTimeZone(dbData);
  }

}
