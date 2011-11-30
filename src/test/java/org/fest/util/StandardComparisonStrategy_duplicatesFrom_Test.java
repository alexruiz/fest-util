/*
 * Created on Sep 23, 2006
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
 * Copyright @2006-2011 the original author or authors.
 */
package org.fest.util;

import static org.fest.util.Collections.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

/**
 * Tests for {@link StandardComparisonStrategy#standardComparisonStrategy.duplicatesFrom(java.util.Collection)}.<br>
 * 
 * @author Joel Costigliola
 */
public class StandardComparisonStrategy_duplicatesFrom_Test extends AbstractTest_StandardComparisonStrategy {
  
  @Test
  public void should_return_existing_duplicates() {
    Collection<?> duplicates = standardComparisonStrategy.duplicatesFrom(list("Merry", "Frodo", "Merry", "Sam", "Frodo"));
    assertEquals(2, duplicates.size());
    assertTrue(duplicates.contains("Frodo"));
    assertTrue(duplicates.contains("Merry"));
  }

  @Test
  public void should_not_return_any_duplicates() {
    Collection<?> duplicates = standardComparisonStrategy.duplicatesFrom(list("Frodo", "Sam", "Gandalf"));
    assertTrue(duplicates.isEmpty());
  }

  @Test
  public void should_not_return_any_duplicates_if_collection_is_empty() {
    Collection<?> duplicates = standardComparisonStrategy.duplicatesFrom(new ArrayList<String>());
    assertTrue(duplicates.isEmpty());
  }

  @Test
  public void should_not_return_any_duplicates_if_collection_is_null() {
    Collection<?> duplicates = standardComparisonStrategy.duplicatesFrom(null);
    assertTrue(duplicates.isEmpty());
  }

}
