/*
 * Created on Apr 29, 2007
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2007-2011 the original author or authors.
 */
package org.fest.util;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.fest.util.Collections.isEmpty;
import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Collections.nonNullElements;
import static org.fest.util.Collections.sizeOf;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections#nonNullElements(Collection)}</code>.
 * 
 * @author Joel Costigliola
 * @author Alex Ruiz
 * @author Florent Biville
 */
public class Collections_nonNullElements_Iterable_Test {

  @Test
  public void should_return_null_if_given_iterable_is_null() {
    Iterable<?> iterable = null;
    assertNull(nonNullElements(iterable));
  }

  @Test
  public void should_return_an_empty_iterable_if_given_iterable_has_only_null_elements() {
    Collection<String> c = new ArrayList<String>();
    c.add(null);
    Iterable<String> iterable = c;
    assertTrue(isEmpty(nonNullElements(iterable)));
  }

  @Test
  public void should_return_an_empty_iterable_if_given_iterable_is_empty() {
    Iterable<String> iterable = new ArrayList<String>();
    assertEquals(0, sizeOf(nonNullElements(iterable)));
  }

  @Test
  public void should_return_an_iterable_without_null_elements() {
    Iterable<String> iterable = asList("Frodo", null, "Sam", null);
    Iterable<String> actual = nonNullElements(iterable);
    assertEquals(2, sizeOf(actual));
    List<String> actualAsList = newArrayList(actual);
    assertTrue(actualAsList.contains("Frodo"));
    assertTrue(actualAsList.contains("Sam"));
  }
}
