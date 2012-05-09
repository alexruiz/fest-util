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

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections#list(Object...)}</code>.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Collections_list_Test {

  @Test public void should_return_List_containing_all_elements_in_array() {
    List<String> list = Collections.list("One", "Two");
    assertEquals(2, list.size());
    assertEquals("One", list.get(0));
    assertEquals("Two", list.get(1));
  }

  @Test public void should_return_null_if_array_is_null() {
    assertNull(Collections.list((Object[]) null));
  }

  @Test public void should_return_null_if_element_is_null() {
	String nullString = null;
	assertNull(Collections.list(nullString));
  }

  @Test public void should_return_empty_List_if_array_is_empty() {
    assertTrue(Collections.list(new Object[0]).isEmpty());
  }
}
