/*
 * Created on Apr 29, 2007
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

import static org.fest.util.Collections.*;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections}</code>.
 * 
 * @author Joel Costigliola
 */
public class Collections_contains_Test {

  @Test
  public void should_return_true_if_collections_contains_value_according_to_given_comparator() {
    List<String> hobbits = list("Merry", "Frodo", "Merry", "Sam");
    assertTrue(collectionContains(hobbits, "Sam", String.CASE_INSENSITIVE_ORDER));
    assertTrue(collectionContains(hobbits, "SAM", String.CASE_INSENSITIVE_ORDER));
    assertTrue(collectionContains(hobbits, "sAm", String.CASE_INSENSITIVE_ORDER));
    assertTrue(collectionContains(hobbits, "sam", String.CASE_INSENSITIVE_ORDER));
  }

  @Test
  public void should_return_false_if_collections_does_not_contain_value_according_to_given_comparator() {
    List<String> hobbits = list("Merry", "Frodo", "Merry", "Sam");
    assertFalse(collectionContains(hobbits, "Pippin", String.CASE_INSENSITIVE_ORDER));
    assertFalse(collectionContains(hobbits, "SAM ", String.CASE_INSENSITIVE_ORDER));
    assertFalse(collectionContains(hobbits, "Sam ", String.CASE_INSENSITIVE_ORDER));
  }
  
  @Test
  public void should_return_false_if_collections_is_empty_whatever_given_comparator_is() {
    assertFalse(collectionContains(list(), "anyone", String.CASE_INSENSITIVE_ORDER));
  }
  
}
