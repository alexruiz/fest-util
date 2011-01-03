/*
 * Created on Jun 28, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010 the original author or authors.
 */
package org.fest.util;

import static java.lang.String.format;
import static java.lang.reflect.Modifier.isPublic;
import static org.fest.util.Strings.*;

import java.beans.*;
import java.lang.reflect.Method;

/**
 * Understands utility methods related to <a
 * href="http://java.sun.com/docs/books/tutorial/javabeans/introspection/index.html">JavaBeans Introspection</a>.
 *
 * @author Alex Ruiz
 */
public final class Introspection {

  /**
   * Returns a <code>{@link PropertyDescriptor}</code> for a property matching the given name in the given object.
   * @param propertyName the given property name.
   * @param target the given object.
   * @return a {@code PropertyDescriptor} for a property matching the given name in the given object.
   * @throws NullPointerException if the given property name is {@code null}.
   * @throws IllegalArgumentException if the given property name is empty.
   * @throws NullPointerException if the given object is {@code null}.
   * @throws IntrospectionError if a matching property cannot be found or accessed.
   */
  public static PropertyDescriptor descriptorForProperty(String propertyName, Object target) {
    validate(propertyName, target);
    BeanInfo beanInfo = null;
    Class<?> type = target.getClass();
    try {
      beanInfo = Introspector.getBeanInfo(type);
    } catch (Exception e) {
      throw new IntrospectionError(format("Unable to get BeanInfo for type %s", type.getName()), e);
    }
    for (PropertyDescriptor d : beanInfo.getPropertyDescriptors())
      if (propertyName.equals(d.getName())) return d;
    throw new IntrospectionError(propertyNotFoundErrorMessage(propertyName, target));
  }

  private static String propertyNotFoundErrorMessage(String propertyName, Object target) {
    String targetTypeName = target.getClass().getName();
    String property = quote(propertyName);
    // no PropertyDescriptor found, try to give user a precise error message
    if (!fieldHasGetter(propertyName, target))
      return format("No getter for property %s in %s", property, targetTypeName);
    if (!fieldHasPublicGetter(propertyName, target))
      return format("No public getter for property %s in %s", property, targetTypeName);
    // generic message
    return format("Unable to find property %s in %s", property, targetTypeName);
  }

  private static boolean fieldHasGetter(String propertyName, Object target) {
    return beanGetter(propertyName, target) != null;
  }

  private static boolean fieldHasPublicGetter(String propertyName, Object target) {
    Method getter = beanGetter(propertyName, target);
    return getter != null && isPublic(getter.getModifiers());
  }

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

  private static void validate(String propertyName, Object target) {
    if (propertyName == null) throw new NullPointerException("The property name should not be null");
    if (isEmpty(propertyName)) throw new IllegalArgumentException("The property name should not be empty");
    if (target == null) throw new NullPointerException("The target object should not be null");
  }

  private Introspection() {}
}
