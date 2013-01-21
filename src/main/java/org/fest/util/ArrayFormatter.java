/*
 * Created on Mar 29, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.util;

import static java.lang.reflect.Array.getLength;
import static org.fest.util.Arrays.isArray;
import static org.fest.util.Preconditions.checkNotNullOrEmpty;
import static org.fest.util.ToString.toStringOf;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Creates a {@code String} representation of an array.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
final class ArrayFormatter {
  private static final String NULL = "null";

  @Nullable String format(@Nullable Object o) {
    if (o == null || !isArray(o)) {
      return null;
    }
    return isObjectArray(o) ? formatObjectArray(o) : formatPrimitiveArray(o);
  }

  private @Nonnull String formatObjectArray(@Nonnull Object o) {
    Object[] array = (Object[]) o;
    int size = array.length;
    if (size == 0) {
      return "[]";
    }
    StringBuilder buffer = new StringBuilder((20 * (size - 1)));
    deepToString(array, buffer, new HashSet<Object[]>());
    return checkNotNullOrEmpty(buffer.toString());
  }

  private void deepToString(@Nullable Object[] array, @Nonnull StringBuilder buffer,
      @Nonnull Set<Object[]> alreadyFormatted) {
    if (array == null) {
      buffer.append(NULL);
      return;
    }
    alreadyFormatted.add(array);
    buffer.append('[');
    int size = array.length;
    for (int i = 0; i < size; i++) {
      if (i != 0) {
        buffer.append(", ");
      }
      Object element = array[i];
      if (!isArray(element)) {
        buffer.append(element == null ? NULL : toStringOf(element));
        continue;
      }
      if (!isObjectArray(element)) {
        buffer.append(formatPrimitiveArray(element));
        continue;
      }
      if (alreadyFormatted.contains(element)) {
        buffer.append("[...]");
        continue;
      }
      deepToString((Object[]) element, buffer, alreadyFormatted);
    }
    buffer.append(']');
    alreadyFormatted.remove(array);
  }

  private boolean isObjectArray(@Nullable Object o) {
    return o != null && isArray(o) && !isArrayTypePrimitive(o);
  }

  private String formatPrimitiveArray(@Nullable Object o) {
    if (o == null || !isArray(o)) {
      return null;
    }
    if (!isArrayTypePrimitive(o)) {
      String msg = String.format("<%s> is not an array of primitives", o);
      throw new IllegalArgumentException(msg);
    }
    int size = getLength(o);
    if (size == 0) {
      return "[]";
    }
    StringBuilder buffer = new StringBuilder();
    buffer.append('[');
    buffer.append(toStringOf(Array.get(o, 0)));
    for (int i = 1; i < size; i++) {
      buffer.append(", ");
      buffer.append(toStringOf(Array.get(o, i)));
    }
    buffer.append("]");
    return buffer.toString();
  }

  private boolean isArrayTypePrimitive(@Nonnull Object o) {
    return o.getClass().getComponentType().isPrimitive();
  }
}
