/*
 * Created on Nov 1, 2007
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
 * Copyright @2007-2011 the original author or authors.
 */
package org.fest.util;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.fest.util.Preconditions.checkNotNull;

/**
 * Filters elements of a collection by their data type.
 *
 * @param <T> the generic type of the objects returned by the filter.
 * @author Yvonne Wang
 */
public class TypeFilter<T> implements CollectionFilter<T> {
  private final Class<T> type;

  TypeFilter(Class<T> type) {
    this.type = type;
  }

  /**
   * Creates a new {@link TypeFilter}.
   *
   * @param <T>  the generic type of the target type.
   * @param type the target type for this filter.
   * @return the created filter.
   */
  public static @Nonnull <T> TypeFilter<T> byType(@Nonnull Class<T> type) {
    return new TypeFilter<T>(type);
  }

  /**
   * Filters the given collection by the type specified in this filter.
   *
   * @param target the collection to filter.
   * @return a list containing the filtered elements.
   * @throws NullPointerException if the given collection is {@code null}.
   */
  @Override
  @SuppressWarnings("unchecked")
  public @Nonnull List<T> filter(@Nonnull Collection<?> target) {
    checkNotNull(target);
    List<Object> filtered = new ArrayList<Object>();
    for (Object o : target) {
      if (o == null) {
        continue;
      }
      if (type.isAssignableFrom(o.getClass())) {
        filtered.add(o);
      }
    }
    return (List<T>) filtered;
  }
}
