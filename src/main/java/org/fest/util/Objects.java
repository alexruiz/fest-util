/*
 * Created on Jun 2, 2006
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
 * Copyright @2006-2013 the original author or authors.
 */
package org.fest.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.lang.reflect.Array;

import static org.fest.util.Arrays.isArray;
import static org.fest.util.Preconditions.checkNotNull;

/**
 * Utility methods related to objects.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public final class Objects {
  /**
   * Prime number used to calculate the hash code of objects.
   */
  public static final int HASH_CODE_PRIME = 31;

  private Objects() {
  }

  /**
   * Indicates whether the given objects are equal.
   *
   * @param o1 one of the objects to compare.
   * @param o2 one of the objects to compare.
   * @return {@code true} if the given objects are equal or if both objects are {@code null}.
   */
  public static boolean areEqual(@Nullable Object o1, @Nullable Object o2) {
    if (o1 == null) {
      return o2 == null;
    }
    if (o1.equals(o2)) {
      return true;
    }
    return areEqualArrays(o1, o2);
  }

  private static boolean areEqualArrays(@Nullable Object o1, @Nullable Object o2) {
    if (!isArray(o1) || !isArray(o2)) {
      return false;
    }
    if (o1 == o2) {
      return true;
    }
    int size = Array.getLength(o1);
    if (Array.getLength(o2) != size) {
      return false;
    }
    for (int i = 0; i < size; i++) {
      Object e1 = Array.get(o1, i);
      Object e2 = Array.get(o2, i);
      if (!areEqual(e1, e2)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns an array containing the names of the given types.
   *
   * @param types the given types.
   * @return the names of the given types stored in an array.
   * @throws NullPointerException if the given array of types is {@code null}.
   */
  public static @NotNull String[] namesOf(@NotNull Class<?>... types) {
    checkNotNull(types);
    String[] names = new String[types.length];
    for (int i = 0; i < types.length; i++) {
      names[i] = types[i].getName();
    }
    return names;
  }

  /**
   * Returns the hash code for the given object. If the object is {@code null}, this method returns zero. Otherwise
   * calls the method {@code hashCode} of the given object.
   *
   * @param o the given object.
   * @return the hash code for the given object
   */
  public static int hashCodeFor(@Nullable Object o) {
    return o != null ? o.hashCode() : 0;
  }

  /**
   * Casts the given object to the given type only if the object is of the given type. If the object is not of the given
   * type, this method returns {@code null}.
   *
   * @param <T>  the generic type to cast the given object to.
   * @param o    the object to cast.
   * @param type the given type.
   * @return the casted object, or {@code null} if the given object is not to the given type.
   */
  public static @Nullable <T> T castIfBelongsToType(@NotNull Object o, @NotNull Class<T> type) {
    if (o != null && type.isAssignableFrom(o.getClass())) {
      return type.cast(o);
    }
    return null;
  }
}
