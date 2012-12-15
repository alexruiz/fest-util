/*
 * Created on Jun 28, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.fest.util.Introspection.getProperty;
import static org.junit.rules.ExpectedException.none;

import java.beans.PropertyDescriptor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link Introspection#getProperty(String, Object)}.
 * 
 * @author Joel Costigliola
 */
public class Introspection_getProperty_Test {
  @Rule
  public ExpectedException thrown = none();

  private Employee judy;

  @Before
  public void initData() {
    judy = new Employee(100000.0, 31);
  }

  @Test
  public void get_descriptor_for_property() {
    PropertyDescriptor propertyDescriptor = getProperty("age", judy);
    assertNotNull(propertyDescriptor);
    assertEquals("age", propertyDescriptor.getName());
  }

  @Test
  public void should_raise_an_error_because_of_missing_getter() {
    thrown.expect(IntrospectionError.class);
    thrown.expectMessage("No getter for property 'salary' in org.fest.util.Employee");
    getProperty("salary", judy);
  }

  @Test
  public void should_raise_an_error_because_of_non_public_getter() {
    thrown.expect(IntrospectionError.class);
    thrown.expectMessage("No public getter for property 'company' in org.fest.util.Employee");
    getProperty("company", judy);
  }
}
