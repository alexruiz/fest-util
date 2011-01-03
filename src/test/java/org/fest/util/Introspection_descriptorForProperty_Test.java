/*
 * Created on Jun 28, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import static org.fest.util.Introspection.descriptorForProperty;

import java.beans.PropertyDescriptor;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link Introspection#descriptorForProperty(String, Object)}</code>.
 *
 * @author Joel Costigliola
 */
public class Introspection_descriptorForProperty_Test {

  private Employee judy;

  @Before
  public void initData() {
    judy = new Employee(100000.0, 31);
  }
  
  @Test
  public void get_descriptor_for_property() {
    PropertyDescriptor propertyDescriptor = descriptorForProperty("age", judy);
    assertNotNull(propertyDescriptor);
    assertEquals("age",propertyDescriptor.getName());
  }

  @Test
  public void should_raise_an_error_because_of_missing_getter() {
    try {
      descriptorForProperty("salary", judy);
    } catch (IntrospectionError error) {
      assertEquals("No getter for property 'salary' in org.fest.util.Employee", error.getMessage());
    }
  }
  
  @Test
  public void should_raise_an_error_because_of_non_public_getter() {
    try {
      descriptorForProperty("company", judy);
    } catch (IntrospectionError error) {
      assertEquals("No public getter for property 'company' in org.fest.util.Employee", error.getMessage());
    }
    try {
      descriptorForProperty("firstJob", judy);
    } catch (IntrospectionError error) {
      assertEquals("No public getter for property 'firstJob' in org.fest.util.Employee", error.getMessage());
    }
  }
  
}
