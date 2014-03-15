/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.jpa.converter;

import com.anosym.utilities.Utility;
import com.anosym.utilities.currency.Amount;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Currency;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

/**
 *
 * @author marembo
 */
@Converter(autoApply = true)
public class AmountJpaConverter implements AttributeConverter<Amount, String>, UserType {

  @SuppressWarnings("null")
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

  public int[] sqlTypes() {
    return new int[]{Types.VARCHAR};
  }

  public Class returnedClass() {
    return Amount.class;
  }

  public boolean equals(Object x, Object y) throws HibernateException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public int hashCode(Object x) throws HibernateException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public Object deepCopy(Object value) throws HibernateException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public boolean isMutable() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public Serializable disassemble(Object value) throws HibernateException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
