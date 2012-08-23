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

import static junit.framework.Assert.assertEquals;
import static org.fest.util.Collections.sizeOf;
import static org.fest.util.Lists.newArrayList;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections#sizeOf(Iterable)}</code>.
 * 
 * @author Joel Costigliola
 */
public class Collections_sizeOf_Test {

  @Test
  public void should_return_zero_if_iterable_is_empty() {
    assertEquals(0, sizeOf(new ArrayList<String>()));
  }

  @Test
  public void should_throws_exception_if_iterable_is_null() {
    try {
      sizeOf(null);
      throw new AssertionError("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
      assertEquals("iterable parameter must not be null", e.getMessage());
    }
  }

  @Test
  public void should_return_iterable_size() {
    assertEquals(2, sizeOf(newArrayList("Frodo", "Sam")));
  }
}
