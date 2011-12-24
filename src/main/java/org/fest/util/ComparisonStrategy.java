/*
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

import java.util.Collection;

/**
 * 
 * Describes the contract to implement a <b>consistent</b> comparison strategy that covers :<br>
 * - comparing two objects<br>
 * - knowing if an object belongs to a group of objects (collection/array)<br>
 * 
 * @author Joel Costigliola
 * 
 */
public interface ComparisonStrategy {

  /**
   * Returns true if actual and other are equal according to the implemented comparison strategy.
   * 
   * @param actual the object to compare to other
   * @param other the object to compare to actual
   * @return true if actual and other are equal according to the underlying comparison strategy.
   */
  boolean areEqual(Object actual, Object other);

  /**
   * Returns true if actual is greater than other, false otherwise.
   * 
   * @param actual the object to compare to other
   * @param other the object to compare to actual
   * @return true if actual is greater than other, false otherwise.
   * @throws UnsupportedOperationException if operation is not supported by a concrete implementation.
   */
  boolean isGreaterThan(Object actual, Object other);
  
  /**
   * Returns true if actual is greater than or equal to other, false otherwise.
   * 
   * @param actual the object to compare to other
   * @param other the object to compare to actual
   * @return true if actual is greater than or equal to other, false otherwise.
   * @throws UnsupportedOperationException if operation is not supported by a concrete implementation.
   */
  boolean isGreaterThanOrEqualTo(Object actual, Object other);
  
  /**
   * Returns true if actual is less than other, false otherwise.
   * 
   * @param actual the object to compare to other
   * @param other the object to compare to actual
   * @return true if actual is less than other, false otherwise.
   * @throws UnsupportedOperationException if operation is not supported by a concrete implementation.
   */
  boolean isLessThan(Object actual, Object other);
  
  /**
   * Returns true if actual is less than or equal to other, false otherwise.
   * 
   * @param actual the object to compare to other
   * @param other the object to compare to actual
   * @return true if actual is less than or equal to other, false otherwise.
   * @throws UnsupportedOperationException if operation is not supported by a concrete implementation.
   */
  boolean isLessThanOrEqualTo(Object actual, Object other);
  
  /**
   * Returns true if given collection contains given value according to the implemented comparison strategy, false
   * otherwise.
   * 
   * @param collection the collection to search value in (must not be null)
   * @param value the object to look for in given collection
   * @return true if given collection contains given value according to the implemented comparison strategy, false
   *         otherwise.
   */
  boolean collectionContains(Collection<?> collection, Object value);

  /**
   * Look for given value in given collection according to the implemented comparison strategy, if value is found it is
   * removed from collection.
   * <p>
   * We can't simply write : <code>collection.remove(value)</code> if implemented comparison strategy is not based on
   * equals method.
   * @param collection the collection we want remove value from (must not be null)
   * @param value object to remove from actual collection
   */
  void collectionRemoves(Collection<?> collection, Object value);

  /**
   * Returns any duplicate elements from the given collection according to the implemented comparison strategy.
   * 
   * @param collection the given collection we want to extract duplicate elements.
   * @return a collection containing the duplicate elements of the given one. If no duplicates are found, an empty
   *         collection is returned.
   */
  Collection<?> duplicatesFrom(Collection<?> collection);

  /**
   * Returns true if given array contains given value according to the implemented comparison strategy, false otherwise.
   * 
   * @param array the array to search value in (must not be null)
   * @param value the object to look for in given array
   * @return true if given array contains given value according to the implemented comparison strategy, false otherwise.
   */
  boolean arrayContains(Object array, Object value);

  /**
   * Returns true if given string contains given sequence according to the implemented comparison strategy, false otherwise.
   * 
   * @param string the string to search sequence in (must not be null)
   * @param sequence the String to look for in given string
   * @return true if given string contains given sequence according to the implemented comparison strategy, false otherwise.
   */
  boolean stringContains(String string, String sequence);
  
  /**
   * Returns true if s starts with prefix according to the implemented comparison strategy, false otherwise.
   * 
   * @param string the String we want to look starting prefix 
   * @param prefix the prefix String to look for in s
   * @return true if s starts with prefix according to the implemented comparison strategy, false otherwise.
   */
  boolean stringStartsWithPrefix(String string, String prefix);

  /**
   * Returns true if s ends with suffix according to the implemented comparison strategy, false otherwise.
   * 
   * @param string the String we want to look starting suffix 
   * @param suffix the prefix String to look for in s
   * @return true if s ends with suffix according to the implemented comparison strategy, false otherwise.
   */
  boolean stringEndsWithPrefix(String string, String suffix);
  
}
