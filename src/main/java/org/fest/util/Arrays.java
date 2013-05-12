/*
 * Created on May 13, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static org.fest.util.Preconditions.checkNotNull;

/**
 * Utility methods related to arrays.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class Arrays {
  private static final ArrayFormatter FORMATTER = new ArrayFormatter();

  private Arrays() {
  }

  /**
   * Indicates whether the given object is not {@code null} and is an array.
   *
   * @param o the given object.
   * @return {@code true} if the given object is not {@code null} and is an array, otherwise {@code false}.
   */
  public static boolean isArray(@Nullable Object o) {
    return o != null && o.getClass().isArray();
  }

  /**
   * Indicates whether the given array is {@code null} or empty.
   *
   * @param <T>   the type of elements of the array.
   * @param array the array to check.
   * @return {@code true} if the given array is {@code null} or empty, otherwise {@code false}.
   */
  public static <T> boolean isNullOrEmpty(@Nullable T[] array) {
    return array == null || !hasElements(array);
  }

  /**
   * Returns an array containing the given arguments.
   *
   * @param <T>    the type of the array to return.
   * @param values the values to store in the array.
   * @return an array containing the given arguments.
   */
  public static @Nonnull <T> T[] array(@Nonnull T... values) {
    return checkNotNull(values);
  }

  /**
   * Returns the {@code String} representation of the given array, or {@code null} if the given object is either {@code
   * null} or not an array. This method supports arrays having other arrays as elements.
   *
   * @param array the object that is expected to be an array.
   * @return the {@code String} representation of the given array.
   */
  public static @Nullable String format(@Nullable Object array) {
    return FORMATTER.format(array);
  }

  /**
   * Returns all the non-{@code null} elements in the given array.
   *
   * @param <T>   the type of elements of the array.
   * @param array the given array.
   * @return all the non-{@code null} elements in the given array.
   * @throws NullPointerException if the given array is {@code null}.
   * @since 1.1.3
   */
  public static <T> List<T> nonNullElementsIn(@Nonnull T[] array) {
    checkNotNull(array);
    List<T> nonNullElements = new ArrayList<T>();
    for (T o : array) {
      if (o != null) {
        nonNullElements.add(o);
      }
    }
    return nonNullElements;
  }

  /**
   * Returns {@code true} if the given array has only {@code null} elements, {@code false} otherwise. If given array is
   * empty, this method returns {@code true}.
   *
   * @param <T>   the type of elements of the array.
   * @param array the given array. <b>It must not be null</b>.
   * @return {@code true} if the given array has only {@code null} elements or is empty, {@code false} otherwise.
   * @throws NullPointerException if the given array is {@code null}.
   * @since 1.1.3
   */
  public static <T> boolean hasOnlyNullElements(@Nonnull T[] array) {
    checkNotNull(array);
    if (!hasElements(array)) {
      return false;
    }
    for (T o : array) {
      if (o != null) {
        return false;
      }
    }
    return true;
  }

  private static <T> boolean hasElements(@Nonnull T[] array) {
    return array.length > 0;
  }
}
