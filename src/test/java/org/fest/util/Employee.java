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

/**
 * Class used for testing {@link Introspection}.
 *
 * @author Joel Costigliola
 */
public class Employee implements Comparable<Employee> {
  // This is a property.
  private final int age;

  public int getAge() {
    return age;
  }

  // This is not a property (does not have a public getter.)
  private final String company = "google";

  String getCompany() {
    return company;
  }

  private boolean firstJob;

  boolean isFirstJob() {
    return firstJob;
  }

  // This is not a property (does not have a public getter.)
  private final double salary;

  public Employee(double salary, int age) {
    super();
    this.salary = salary;
    this.age = age;
  }

  @Override
  public int compareTo(Employee other) {
    return age - other.age;
  }
}
