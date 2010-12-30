/*
 * Created on Jun 28, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2010 the original author or authors.
 */
package org.fest.util;

import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.isEmpty;
import static org.fest.util.Strings.quote;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Understands utility methods related to
 * <a href="http://java.sun.com/docs/books/tutorial/javabeans/introspection/index.html">JavaBeans Introspection</a>.
 *
 * @author Alex Ruiz
 */
public final class Introspection {

  /**
   * Returns a <code>{@link PropertyDescriptor}</code> for a property matching the given name in the given object.
   * @param propertyName the given property name.
   * @param target the given object.
   * @return a {@code PropertyDescriptor} for a property matching the given name in the given object.
   * @throws NullPointerException if the given property name is <code>null</code>.
   * @throws IllegalArgumentException if the given property name is empty.
   * @throws NullPointerException if the given object is <code>null</code>.
   * @throws IntrospectionError if a matching property cannot be found or accessed.
   */
  public static PropertyDescriptor descriptorForProperty(String propertyName, Object target) {
    validate(propertyName, target);
    BeanInfo beanInfo = null;
    Class<?> type = target.getClass();
    try {
      beanInfo = Introspector.getBeanInfo(type);
    } catch (Exception e) {
      throw new IntrospectionError(concat("Unable to get BeanInfo for type ", type.getName()), e);
    }
    for (PropertyDescriptor d : beanInfo.getPropertyDescriptors())
      if (propertyName.equals(d.getName())) return d;
    throw buildIntrospectionErrorForMissingProperty(propertyName, target);
  }

  private static IntrospectionError buildIntrospectionErrorForMissingProperty(String propertyName, Object target) {
    // no PropertyDescriptor found, try to give user a precise error message
    if (!fieldHasGetter(propertyName, target))
      return new IntrospectionError(concat("No getter for property ", quote(propertyName), " in ", target.getClass().getName()));
    if (!fieldHasPublicGetter(propertyName, target))
      return new IntrospectionError(concat("No public getter for property ", quote(propertyName), " in ", target.getClass().getName()));
    // generic message
    return new IntrospectionError(concat("Unable to find property ", quote(propertyName), " in ", target.getClass().getName()));
  }

  private static void validate(String propertyName, Object target) {
    if (propertyName == null) throw new NullPointerException("The property name should not be null");
    if (isEmpty(propertyName)) throw new IllegalArgumentException("The property name should not be empty");
    if (target == null) throw new NullPointerException("The target object should not be null");
  }

  /**
   * Return <code>true</code> if the specified bean class has a getter for the given propertyName;
   * otherwise, return <code>false</code>.
   * @param propertyName Property name to be evaluated
   * @param target Bean to be examined
   * 
   * @return <code>true</code> if the specified bean class has a getter for the given propertyName, <code>false</code> otherwise.
   */
  private static boolean fieldHasGetter(String propertyName, Object target) {
    if (beanGetter(propertyName, target) != null) return true;
    return false;
  }

  /**
   * Return <code>true</code> if the specified bean class has a <b>public</b> getter for the given propertyName;
   * otherwise, return <code>false</code>.
   * @param propertyName Property name to be evaluated
   * @param target Bean to be examined
   * 
   * @return <code>true</code> if the specified bean class has a <b>public</b> getter for the given propertyName, <code>false</code> otherwise.
   */
  private static boolean fieldHasPublicGetter(String propertyName, Object target) {
    if (!fieldHasGetter(propertyName, target)) return false;
    return Modifier.isPublic(beanGetter(propertyName, target).getModifiers());
  }

  /**
   * Returns the getter method for the given property of target object class.
   * @param propertyName the name of property to look for getter
   * @param target an object to introspect
   * @return the getter method for the given property of target object class or null if nothing found.
   */
  private static Method beanGetter(String propertyName, Object target) {
    validate(propertyName, target);
    Method getterMethod = null;
    String propertyWithFirstLetterUppercased = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    try {
      // try to find getProperty
      getterMethod = target.getClass().getDeclaredMethod("get" + propertyWithFirstLetterUppercased);
      if (getterMethod != null) return getterMethod;
    } catch (SecurityException e) {
      // nothing to do
    } catch (NoSuchMethodException e) {
      // nothing to do
    }
    try {
      // try to find isProperty for boolean properties
      getterMethod = target.getClass().getDeclaredMethod("is" + propertyWithFirstLetterUppercased);
    } catch (SecurityException e) {
      return null;
    } catch (NoSuchMethodException e) {
      return null;
    }
    return getterMethod;
  }

  
  private Introspection() {}
}
