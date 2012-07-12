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

import static org.fest.util.Collections.*;

import static org.junit.Assert.*;
import static org.junit.rules.ExpectedException.none;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for <code>{@link Collections#hasOnlyNullElements(java.util.Collection)}</code>
 * 
 * @author Joel Costigliola
 */
public class Collections_hasOnlyNullElements_Test {

  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_return_truee_if_given_collection_has_only_null_elements() {
    List<String> list = new ArrayList<String>();
    list.add(null);
    assertTrue(hasOnlyNullElements(list));
    list.add(null);
    assertTrue(hasOnlyNullElements(list));
  }

  @Test
  public void should_return_false_if_given_collection_is_empty() {
    assertFalse(hasOnlyNullElements(new ArrayList<String>()));
  }

  @Test
  public void should_return_false_if_given_collection_has_non_null_elements() {
    assertFalse(hasOnlyNullElements(list("Frodo")));
  }

  @Test
  public void should_throw_NullPointerException_if_given_collection_is_null() {
    thrown.expect(NullPointerException.class);
    hasOnlyNullElements(null);
  }
}
