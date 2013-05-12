/*
 * Created on Sep 22, 2006
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
 * Copyright @2006-2013 the original author or authors.
 */
package org.fest.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link Strings#quote(Object)}.
 *
 * @author Alex Ruiz
 */
public class Strings_quoteObject_Test {
  @Test
  public void should_not_quote_Object_that_is_not_String() {
    assertEquals(9, Strings.quote(9));
  }

  @Test
  public void should_quote_Object_that_is_String() {
    Object o = "Hello";
    assertEquals("'Hello'", Strings.quote(o));
  }
}
