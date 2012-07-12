/*
 * Created on Jan 2, 2011
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
 * Copyright @2011 the original author or authors.
 */
package org.fest.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.rules.ExpectedException.none;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for <code>{@link Arrays#copyOf(Object[], int)}</code>.
 * 
 * @author Alex Ruiz
 */
public class Arrays_copyOf_Test {

  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_copy_array() {
    String[] original = { "Yoda", "Luke" };
    String[] copy = Arrays.copyOf(original, original.length);
    assertArrayEquals(original, copy);
  }

  @Test
  public void should_throw_error_if_length_is_negative() {
    thrown.expect(NegativeArraySizeException.class);
    Arrays.copyOf(new Object[0], -1);
  }

  @Test
  public void should_throw_error_if_original_is_null() {
    thrown.expect(NullPointerException.class);
    Arrays.copyOf(null, 6);
  }
}
