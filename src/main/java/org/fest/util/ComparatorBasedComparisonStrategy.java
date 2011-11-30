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
package org.fest.util;

import static org.fest.util.Strings.quote;

import java.util.*;

/**
 * Implements {@link ComparisonStrategy} contract with a comparison strategy based on a {@link Comparator}.
 * 
 * @author Joel Costigliola
 * 
 *         TODO FEST 64 unit test
 */
public class ComparatorBasedComparisonStrategy extends AbstractComparisonStrategy {

  // A raw type is necessary because we can't make assumptions on object to be compared.
  @SuppressWarnings("rawtypes")
  private Comparator comparator;

  /**
   * Creates a new </code>{@link ComparatorBasedComparisonStrategy}</code> specifying the comparison strategy with given
   * comparator.
   * @param comparator the comparison strategy to use.
   */
  public ComparatorBasedComparisonStrategy(@SuppressWarnings("rawtypes") Comparator comparator) {
    this.comparator = comparator;
  }

  /**
   * Returns true if given collection contains given value according to {@link #comparator}, false otherwise.
   * @param collection the collection to search value in
   * @param value the object to look for in given collection
   * @return true if given collection contains given value according to {@link #comparator}, false otherwise.
   */
  public boolean collectionContains(Collection<?> collection, Object value) {
    return Collections.collectionContains(collection, value, comparator);
  }

  /**
   * Look for given value in given collection according to the {@link Comparator}, if value is found it is removed from
   * collection.
   * @param collection the collection we want remove value from (must not be null)
   * @param value object to remove from actual collection
   */
  @SuppressWarnings("unchecked")
  public void collectionRemoves(Collection<?> collection, Object value) {
    for (Iterator<?> iterator = collection.iterator(); iterator.hasNext();) {
      if (comparator.compare(iterator.next(), value) == 0) {
        iterator.remove();
        return;
      }
    }
  }

  /**
   * Returns true if actual and other are equal according to {@link #comparator}, false otherwise.
   * 
   * @param actual the object to compare to other
   * @param other the object to compare to actual
   * @return true if actual and other are equal according to {@link #comparator}, false otherwise.
   */
  @SuppressWarnings("unchecked")
  public boolean areEqual(Object actual, Object other) {
    return comparator.compare(actual, other) == 0;
  }

  /**
   * Returns any duplicate elements from the given collection according to {@link #comparator}.
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

  @Override
  public String toString() {
    String comparatorSimpleClassName = comparator.getClass().getSimpleName();
    return quote(comparatorSimpleClassName.length() > 0 ? comparatorSimpleClassName : "anonymous comparator class");
  }

  public Comparator<?> getComparator() {
    return comparator;
  }

  @SuppressWarnings("unchecked")
  public boolean stringStartsWithPrefix(String string, String prefix) {
    if (string.length() < prefix.length()) return false;
    String stringPrefix = string.substring(0, prefix.length());
    return comparator.compare(stringPrefix, prefix) == 0;
  }

  @SuppressWarnings("unchecked")
  public boolean stringEndsWithPrefix(String string, String suffix) {
    if (string.length() < suffix.length()) return false;
    String stringSuffix = string.substring(suffix.length() - 1);
    return comparator.compare(stringSuffix, suffix) == 0;
  }

  public boolean stringContains(String string, String sequence) {
    int sequenceLength = sequence.length();
    for (int i = 0; i < string.length(); i++) {
      String subString = string.substring(i);
      if (subString.length() < sequenceLength) return false;
      if (stringStartsWithPrefix(subString, sequence)) return true;
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public boolean isGreaterThan(Object actual, Object other) {
    return comparator.compare(actual, other) > 0;
  }

}
