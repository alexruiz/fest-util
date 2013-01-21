/*
 * Created on Aug 23, 2012
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
 * Copyright @2012-2013 the original author or authors.
 */
package org.fest.util;

import static org.fest.util.Preconditions.checkNotNull;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 * Utility methods related to {@link Set}s.
 *
 * @author Alex Ruiz
 */
public final class Sets {
  /**
   * Creates a <em>mutable</em> {@link LinkedHashSet}.
   *
   * @param <T> the generic type of the {@code LinkedHashSet} to create.
   * @return the created {@code LinkedHashSet}.
   * @since 1.2.3
   */
  public static @Nonnull <T> LinkedHashSet<T> newLinkedHashSet() {
    return new LinkedHashSet<T>();
  }

  /**
   * Creates a <em>mutable</em> {@link LinkedHashSet} containing the given elements.
   *
   * @param <T> the generic type of the {@code LinkedHashSet} to create.
   * @param elements the elements to store in the {@code LinkedHashSet}.
   * @return the created {@code LinkedHashSet}.
   * @throws NullPointerException if the given array is {@code null}.
   * @since 1.2.3
   */
  public static @Nonnull <T> LinkedHashSet<T> newLinkedHashSet(@Nonnull T... elements) {
    checkNotNull(elements);
    LinkedHashSet<T> set = new LinkedHashSet<T>();
    for (T e : elements) {
      set.add(e);
    }
    return set;
  }

  private Sets() {}
}
