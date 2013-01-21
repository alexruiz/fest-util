/*
 * Created on Oct 7, 2009
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

import static org.fest.util.Arrays.isArray;
import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.Strings.quote;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Obtains the {@code toString} representation of an object.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Yvonne Wang
 */
public final class ToString {
  /**
   * Returns the {@code toString} representation of the given object. It may or not the object's own implementation of
   * {@code toString}.
   *
   * @param o the given object.
   * @return the {@code toString} representation of the given object.
   */
  public static @Nullable String toStringOf(@Nullable Object o) {
    if (isArray(o)) {
      return Arrays.format(o);
    }
    if (o instanceof Calendar) {
      return toStringOf(o);
    }
    if (o instanceof Class<?>) {
      return toStringOf((Class<?>) o);
    }
    if (o instanceof Collection<?>) {
      return toStringOf((Collection<?>) o);
    }
    if (o instanceof Date) {
      return toStringOf(o);
    }
    if (o instanceof Float) {
      return toStringOf((Float) o);
    }
    if (o instanceof Long) {
      return toStringOf((Long) o);
    }
    if (o instanceof File) {
      return toStringOf((File) o);
    }
    if (o instanceof Map<?, ?>) {
      return toStringOf((Map<?, ?>) o);
    }
    if (o instanceof String) {
      return quote((String) o);
    }
    if (o instanceof Comparator) {
      return toStringOf((Comparator<?>) o);
    }
    return o == null ? null : o.toString();
  }

  private static @Nonnull String toStringOf(@Nonnull Comparator<?> comparator) {
    String typeName = comparator.getClass().getSimpleName();
    String toString = quote(!typeName.isEmpty() ? typeName : "Anonymous Comparator class");
    return checkNotNull(toString);
  }

  private static @Nonnull String toStringOf(@Nonnull Class<?> c) {
    return checkNotNull(c.getCanonicalName());
  }

  private static @Nonnull String toStringOf(@Nonnull Collection<?> c) {
    return checkNotNull(Collections.format(c));
  }

  private static @Nonnull String toStringOf(@Nonnull Float f) {
    if (f.isNaN()) {
      return "NaN";
    }
    return checkNotNull(String.format("%sf", f));
  }

  private static @Nonnull String toStringOf(@Nonnull Long l) {
    return checkNotNull(String.format("%sL", l));
  }

  private static @Nullable String toStringOf(@Nonnull File f) {
    return f.getAbsolutePath();
  }

  private static @Nonnull String toStringOf(@Nonnull Map<?, ?> m) {
    return checkNotNull(Maps.format(m));
  }

  private ToString() {}
}
