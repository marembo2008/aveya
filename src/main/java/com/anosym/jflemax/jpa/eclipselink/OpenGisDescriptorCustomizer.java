/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.jflemax.jpa.eclipselink;

import com.anosym.utilities.Utility;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;

/**
 *
 * @author marembo
 */
public class OpenGisDescriptorCustomizer implements DescriptorCustomizer {

  private static final Logger LOG = Logger.getLogger(OpenGisDescriptorCustomizer.class.getName());

  public void customize(ClassDescriptor descriptor) throws Exception {
    //just handle location class only.
    //get the field or method of this class or its supperclass.
    //get all the entity fields.
    //we do not support inheritance currently.
    String tableName = descriptor.getTableName();
    String insertSql = "INSERT INTO " + tableName + "(";
    String updateSql = "UPDATE " + tableName + " SET ";
    String selectSql = "SELECT ";
    String insertFields = "";
    String insertValues = "";
    String selectFields = "";
    String updatevalues = "";
    Class cl = descriptor.getJavaClass();
    LOG.info(cl.getCanonicalName());
    for (Field f : cl.getDeclaredFields()) {
      int m = f.getModifiers();
      if (Modifier.isFinal(m) || Modifier.isStatic(m)) {
        continue;
      }
      boolean isTransient = f.getAnnotation(Transient.class) != null;
      if (isTransient) {
        continue;
      }
      Column column = f.getAnnotation(Column.class);
      String colName = column != null && !Utility.isNullOrEmpty(column.name()) ? column.name() : f.getName().toUpperCase();
      if (colName.trim().isEmpty() || colName.trim().startsWith("_PERSISTENCE")) {
        continue;
      }
      boolean isId = f.getAnnotation(Id.class) != null;
      if (!insertFields.isEmpty()) {
        insertFields += ",";
      }
      if (!insertValues.isEmpty()) {
        insertValues += ",";
      }
      if (!isId) {
        if (!updatevalues.isEmpty()) {
          updatevalues += ",";
        }
      }
      if (!selectFields.isEmpty()) {
        selectFields += ",";
      }
      insertFields += colName;
      if (!isId) {
        updatevalues += (colName + "=");
      }
      String colDef = column != null ? column.columnDefinition() : "";
      if (colDef.equalsIgnoreCase("point")) {
        insertValues += ("GeomFromText(#" + colName + ")");
        updatevalues += ("GeomFromText(#" + colName + ")");
        selectFields += ("AsText(" + colName + ")");
      } else {
        insertValues += ("#" + colName);
        selectFields += colName;
        if (!isId) {
          updatevalues += ("#" + colName);
        }
      }
    }
    insertSql += (insertFields + ") VALUES(" + insertValues + ")");
    updateSql += updatevalues;
    selectSql += (selectFields + " FROM " + tableName);
    descriptor.getQueryManager().setInsertSQLString(insertSql);
    descriptor.getQueryManager().setUpdateSQLString(updateSql);
    descriptor.getQueryManager().setReadAllSQLString(selectSql);
    LOG.info(insertSql);
    LOG.info(updateSql);
    LOG.info(selectSql);
  }

}
