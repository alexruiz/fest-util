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
 * Copyright @2006 the original author or authors.
 */
package org.fest.util;

import static org.fest.util.Arrays.array;
import static org.fest.util.Collections.list;
import static org.fest.util.ToString.toStringOf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
    assertNull(toStringOf(null));
  }

  @Test public void should_quote_String() {
    String o = "Hello";
    assertEquals("'Hello'", toStringOf(o));
  }

  @Test public void should_quote_empty_String() {
    assertEquals("''", Strings.quote(""));
  }

  @Test public void should_describe_Dimension_within_parenthesis_separating_width_and_length_by_comma_and_space() {
    Dimension o = new Dimension(10, 20);
    assertEquals("(10, 20)", toStringOf(o));
  }

  @Test public void should_describe_File_with_its_absolute_path() {
    final String path = "/someFile.txt";
    File o = new File(path) {
      private static final long serialVersionUID = 1L;

      @Override public String getAbsolutePath() {
        return path;
      }
    };
    assertEquals(path, toStringOf(o));
  }

  @Test public void should_describe_Class_with_its_name() {
    assertEquals("java.lang.Object", toStringOf(Object.class));
  }

  @Test public void should_describe_Collection_of_String_within_brackets_separating_quoted_strings_by_comma_and_space() {
    Collection<String> collection = list("s1", "s2");
    assertEquals("['s1', 's2']", toStringOf(collection));
  }

  @Test public void should_describe_Collection_of_boolean_array_within_brackets_separating_boolean_arrays_by_comma_and_space() {
    List<Boolean[]> collection = list(array(true, false), array(true, false, true));
    assertEquals("[[true, false], [true, false, true]]", toStringOf(collection));
  }

  @Test public void should_describe_Collection_of_string_list_within_brackets_separating_string_lists_by_comma_and_space() {
    Collection<List<String>> collection = new ArrayList<List<String>>();
    collection.add(list("s1", "s2"));
    collection.add(list("s3", "s4", "s5"));
    assertEquals("[['s1', 's2'], ['s3', 's4', 's5']]", toStringOf(collection));
  }

  @Test public void should_describe_Array_of_String_within_brackets_separating_quoted_strings_by_comma_and_space() {
    assertEquals("['s1', 's2']", toStringOf(array("s1", "s2")));
  }

  @Test public void should_describe_Array_of_string_array_within_brackets_separating_string_arrays_by_comma_and_space() {
    String[][] array = array(array("s1", "s2"), array("s3", "s4", "s5"));
    assertEquals("[['s1', 's2'], ['s3', 's4', 's5']]", toStringOf(array));
  }

  @Test public void should_describe_Map_of_string_string_within_brackets_separating_entries_by_comma_and_space() {
    Map<String, String> map = new LinkedHashMap<String, String>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    assertEquals("{'key1'='value1', 'key2'='value2'}", toStringOf(map));
  }

}
