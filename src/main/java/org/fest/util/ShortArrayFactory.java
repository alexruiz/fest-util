/*
 * Created on Dec 20, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.util;

/**
 * @author Alex Ruiz
 */
public final class ShortArrayFactory {

  private static final short[] EMPTY = new short[0];

  public static short[] array(int... values) {
    int size = values.length;
    short[] array = new short[size];
    for (int i = 0; i < size; i++)
      array[i] = (short) values[i];
    return array;
  }

  public static short[] emptyArray() {
    return EMPTY;
  }

  private ShortArrayFactory() {}
}
