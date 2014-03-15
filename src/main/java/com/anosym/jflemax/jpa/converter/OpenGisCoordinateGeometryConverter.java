/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.jpa.converter;

import com.anosym.jflemax.geometry.Coordinate;
import com.anosym.utilities.Utility;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author marembo
 */
@Converter(autoApply = true)
public class OpenGisCoordinateGeometryConverter implements AttributeConverter<Coordinate, String> {

  public String convertToDatabaseColumn(Coordinate attribute) {
    return attribute != null ? attribute.toPointString() : null;
  }

  public Coordinate convertToEntityAttribute(String dbData) {
    return !Utility.isNullOrEmpty(dbData) ? Coordinate.fromPointString(dbData) : null;
  }

}
