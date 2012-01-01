package org.fest.util;

/*
 * Created on Sep 17, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */

import static java.lang.String.format;

import java.util.Collection;

/**
 * Implements {@link ComparisonStrategy} contract with a comparison strategy based on {@link Object#equals(Object)}
 * method, it is also based on {@link Comparable#compareTo(Object)} when Object are {@link Comparable} method.
 * 
 * @author Joel Costigliola
 * 
 *         TODO FEST-64 unit test
 */
public class StandardComparisonStrategy extends AbstractComparisonStrategy {

  private static final StandardComparisonStrategy INSTANCE = new StandardComparisonStrategy();

  /**
   * Returns the singleton instance of this class.
   * @return the singleton instance of this class.
   */
  public static StandardComparisonStrategy instance() {
    return INSTANCE;
  }

  /**
   * Creates a new </code>{@link StandardComparisonStrategy}</code>, comparison strategy being based on
   * {@link Object#equals(Object)}.
   */
  private StandardComparisonStrategy() {}

  /**
   * Returns true if actual and other are equal based on {@link Object#equals(Object)}, false otherwise.
   * 
   * @param actual the object to compare to other
   * @param other the object to compare to actual
   * @return true if actual and other are equal based on {@link Object#equals(Object)}, false otherwise.
   */
  public boolean areEqual(Object actual, Object other) {
    return Objects.areEqual(actual, other);
  }

  /**
   * Returns true if given collection contains given value based on {@link Object#equals(Object)}, false otherwise.
   * 
   * @param collection the collection to search value in
   * @param value the object to look for in given collection
   * @return true if given collection contains given value based on {@link Object#equals(Object)}, false otherwise.
   */
  public boolean collectionContains(Collection<?> collection, Object value) {
    return collection.contains(value);
  }

  /**
   * {@inheritDoc}
   */
  public void collectionRemoves(Collection<?> collection, Object value) {
    collection.remove(value);
  }

  /**
   * Returns any duplicate elements from the given collection according to {@link Object#equals(Object)} comparison
   * strategy.
   * 
   * @param collection the given collection we want to extract duplicate elements.
   * @return a collection containing the duplicate elements of the given one. If no duplicates are found, an empty
   *         collection is returned.
   */
  // overidden to write javadoc.
  @Override
  public Collection<?> duplicatesFrom(Collection<?> collection) {
    return super.duplicatesFrom(collection);
  }

  public boolean stringStartsWith(String s, String prefix) {
    return s.startsWith(prefix);
  }

  public boolean stringEndsWith(String s, String suffix) {
    return s.endsWith(suffix);
  }

  public boolean stringContains(String string, String sequence) {
    return string.contains(sequence);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public boolean isGreaterThan(Object actual, Object other) {
    if (!Comparable.class.isAssignableFrom(actual.getClass()))
      throw new IllegalArgumentException(format("argument '%s' should be Comparable but is not", actual));
    if (!Comparable.class.isAssignableFrom(other.getClass()))
      throw new IllegalArgumentException(format("argument '%s' should be Comparable but is not", other));
    Comparable comparableActual =  (Comparable) actual;
    Comparable comparableOther =  (Comparable) other;
    return comparableActual.compareTo(comparableOther) > 0;
  }

}
