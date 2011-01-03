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
 * Copyright @2006-2011-2010 the original author or authors.
 */
package org.fest.util;

import static org.fest.util.Arrays.array;
import static org.fest.util.Collections.list;
import static org.junit.Assert.*;

import java.awt.Dimension;
import java.io.File;
import java.util.*;

import org.junit.Test;

/**
 * Tests for <code>{@link ToString#toStringOf(Object)}</code>.
 *
 * @author Joel Costigliola
 */
public class ToString_toStringOf_Test {

  @Test public void should_return_null_if_object_is_null() {
    assertNull(ToString.toStringOf(null));
  }

  @Test public void should_quote_String() {
    assertEquals("'Hello'", ToString.toStringOf("Hello"));
  }

  @Test public void should_quote_empty_String() {
    assertEquals("''", ToString.toStringOf(""));
  }

  @Test public void should_return_toString_of_Dimension() {
    Dimension o = new Dimension(10, 20);
    assertEquals("(w=10, h=20)", ToString.toStringOf(o));
  }

  @Test public void should_return_toString_of_File() {
    final String path = "/someFile.txt";
    File o = new File(path) {
      private static final long serialVersionUID = 1L;

      @Override public String getAbsolutePath() {
        return path;
      }
    };
    assertEquals(path, ToString.toStringOf(o));
  }

  @Test public void should_return_toString_of_Class_with_its_name() {
    assertEquals("java.lang.Object", ToString.toStringOf(Object.class));
  }

  @Test public void should_return_toString_of_Collection_of_String() {
    Collection<String> collection = list("s1", "s2");
    assertEquals("['s1', 's2']", ToString.toStringOf(collection));
  }

  @Test public void should_return_toString_of_Collection_of_arrays() {
    List<Boolean[]> collection = list(array(true, false), array(true, false, true));
    assertEquals("[[true, false], [true, false, true]]", ToString.toStringOf(collection));
  }

  @Test public void should_return_toString_of_Collection_of_Collections() {
    Collection<List<String>> collection = new ArrayList<List<String>>();
    collection.add(list("s1", "s2"));
    collection.add(list("s3", "s4", "s5"));
    assertEquals("[['s1', 's2'], ['s3', 's4', 's5']]", ToString.toStringOf(collection));
  }

  @Test public void should_return_toString_of_Map() {
    Map<String, String> map = new LinkedHashMap<String, String>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    assertEquals("{'key1'='value1', 'key2'='value2'}", ToString.toStringOf(map));
  }

  @Test public void should_return_toString_of_array() {
    assertEquals("['s1', 's2']", ToString.toStringOf(array("s1", "s2")));
  }

  @Test public void should_return_toString_of_array_of_arrays() {
    String[][] array = array(array("s1", "s2"), array("s3", "s4", "s5"));
    assertEquals("[['s1', 's2'], ['s3', 's4', 's5']]", ToString.toStringOf(array));
  }

  @Test public void should_return_toString_of_array_of_Class() {
    Class<?>[] array = { String.class, File.class };
    assertEquals("[java.lang.String, java.io.File]", ToString.toStringOf(array));
  }
}
