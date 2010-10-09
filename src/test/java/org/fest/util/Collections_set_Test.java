/*
 * Created on Oct 8, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.util;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

/**
 * Tests for <code>{@link Collections#set(Object...)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Collections_set_Test {

  @Test
  public void should_return_Set_containing_all_elements_in_array() {
    Set<String> set = Collections.set("One", "Two");
    assertTrue(set.containsAll(asList("One", "Two")));
  }

  @Test
  public void should_return_null_if_array_is_null() {
    assertNull(Collections.set((Object[]) null));
  }

  @Test
  public void should_return_empty_Set_if_array_is_empty() {
    assertTrue(Collections.set(new Object[0]).isEmpty());
  }
}
