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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import static java.lang.reflect.Modifier.isPublic;
import static java.util.Locale.ENGLISH;
import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.Preconditions.checkNotNullOrEmpty;
import static org.fest.util.Strings.quote;

/**
 * Utility methods related to
 * <a href="http://java.sun.com/docs/books/tutorial/javabeans/introspection/index.html">JavaBeans Introspection</a>.
 *
 * @author Alex Ruiz
 */
public final class Introspection {
  private Introspection() {
  }

  /**
   * Returns a {@link PropertyDescriptor} for a property matching the given name in the given object.
   *
   * @param propertyName the given property name.
   * @param target       the given object.
   * @return a {@code PropertyDescriptor} for a property matching the given name in the given object.
   * @throws NullPointerException     if the given property name is {@code null}.
   * @throws IllegalArgumentException if the given property name is empty.
   * @throws NullPointerException     if the given object is {@code null}.
   * @throws IntrospectionError       if a matching property cannot be found or accessed.
   */
  public static @NotNull PropertyDescriptor getProperty(@NotNull String propertyName, @NotNull Object target) {
    checkNotNullOrEmpty(propertyName);
    checkNotNull(target);
    BeanInfo beanInfo;
    Class<?> type = target.getClass();
    try {
      beanInfo = Introspector.getBeanInfo(type);
    } catch (Throwable t) {
      String msg = String.format("Unable to get BeanInfo for type %s", type.getName());
      throw new IntrospectionError(checkNotNull(msg), t);
    }
    for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
      if (propertyName.equals(descriptor.getName())) {
        return descriptor;
      }
    }
    throw propertyNotFoundError(propertyName, target);
  }

  private static @NotNull IntrospectionError propertyNotFoundError(@NotNull String propertyName,
                                                                   @NotNull Object target) {
    Method getter = findGetter(propertyName, target);
    String format;
    if (getter == null) {
      format = "No getter for property %s in %s";
    } else if (!isPublic(getter.getModifiers())) {
      format = "No public getter for property %s in %s";
    } else {
      format = "Unable to find property %s in %s";
    }
    String msg = String.format(format, quote(propertyName), target.getClass().getName());
    return new IntrospectionError(checkNotNull(msg));
  }

  private static Method findGetter(@NotNull String propertyName, @NotNull Object target) {
    String capitalized = propertyName.substring(0, 1).toUpperCase(ENGLISH) + propertyName.substring(1);
    // try to find getProperty
    Method getter = findMethod("get" + capitalized, target);
    if (getter != null) {
      return getter;
    }
    // try to find isProperty for boolean properties
    return findMethod("is" + capitalized, target);
  }

  private static @Nullable
  Method findMethod(@NotNull String name, @NotNull Object target) {
    // TODO walk class hierarchy to check if any superclass declares the method we are looking for.
    try {
      return target.getClass().getDeclaredMethod(name);
    } catch (Throwable t) {
      return null;
    }
  }
}
