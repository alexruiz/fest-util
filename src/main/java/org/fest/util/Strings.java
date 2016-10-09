/*
 * Created on Jun 2, 2006
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static org.fest.util.Preconditions.checkNotNull;

/**
 * Utility methods related to {@code String}s.
 *
 * @author Alex Ruiz
 */
public final class Strings {
  private Strings() {
  }

  /**
   * Indicates whether the given {@code String} is {@code null} or empty.
   *
   * @param s the {@code String} to check.
   * @return {@code true} if the given {@code String} is {@code null} or empty, otherwise {@code false}.
   */
  public static boolean isNullOrEmpty(@Nullable String s) {
    return s == null || s.length() == 0;
  }

  @Deprecated
  public static boolean isEmpty(String s) {
    return isNullOrEmpty( s );
  }

  /**
   * Returns the given {@code String} surrounded by single quotes, or {@code null} if the given {@code String} is {@code
   * null}.
   *
   * @param s the given {@code String}.
   * @return the given {@code String} surrounded by single quotes, or {@code null} if the given {@code String} is {@code
   *         null}.
   */
  public static @Nullable String quote(@Nullable String s) {
    return s != null ? String.format("'%s'", s) : null;
  }

  /**
   * Returns the given object surrounded by single quotes, only if the object is a {@code String}.
   *
   * @param o the given object.
   * @return the given object surrounded by single quotes, only if the object is a {@code String}.
   * @see #quote(String)
   */
  public static @Nullable Object quote(@Nullable Object o) {
    return o instanceof String ? quote(o.toString()) : o;
  }

  /**
   * Concatenates the given objects into a single {@code String}. This method is more efficient than concatenating using
   * "+", since only one {@link StringBuilder} is created.
   *
   * @param objects the objects to concatenate.
   * @return a {@code String} containing the given objects, or empty {@code String} if the given array is empty.
   */
  public static @Nonnull String concat(@Nonnull Object... objects) {
    checkNotNull(objects);
    StringBuilder b = new StringBuilder();
    for (Object o : objects) {
      b.append(o);
    }
    return b.toString();
  }

  /**
   * Joins the given {@code String}s using a given delimiter. The following example illustrates proper usage of this
   * method:
   * <p/>
   * <pre>
   * Strings.join(&quot;a&quot;, &quot;b&quot;, &quot;c&quot;).with(&quot;|&quot;)
   * </pre>
   * <p/>
   * which will result in the {@code String "a|b|c"}.
   *
   * @param strings the {@code String}s to join.
   * @return an intermediate object that takes a given delimiter and knows how to join the given {@code String}s.
   * @see StringsToJoin#with(String)
   */
  public static @Nonnull StringsToJoin join(@Nonnull String... strings) {
    return new StringsToJoin(strings);
  }

  /**
   * Appends a given {@code String} to the given target, only if the target does not end with the given {@code String}
   * to append. The following example illustrates proper usage of this method:
   * <pre>
   * Strings.append(&quot;c&quot;).to(&quot;ab&quot;);
   * Strings.append(&quot;c&quot;).to(&quot;abc&quot;);
   * </pre>
   * resulting in the {@code String "abc"} for both cases.
   *
   * @param toAppend the {@code String} to append.
   * @return an intermediate object that takes the target {@code String} and knows to append the given {@code String}.
   * @see StringToAppend#to(String)
   */
  public static @Nonnull StringToAppend append(@Nonnull String toAppend) {
    return new StringToAppend(toAppend);
  }

  /**
   * Knows how to join {@code String}s using a given delimiter.
   *
   * @see Strings#join(String[])
   */
  public static class StringsToJoin {
    /**
     * The {@code String}s to join.
     */
    private final String[] strings;

    /**
     * Creates a new {@link StringsToJoin}.
     *
     * @param strings the {@code String}s to join.
     */
    StringsToJoin(@Nonnull String... strings) {
      this.strings = strings;
    }

    /**
     * Specifies the delimeter to use to join {@code String}s.
     *
     * @param delimeter the delimeter to use.
     * @return the {@code String}s joined using the given delimeter.
     * @throws NullPointerException if the given delimeter is {@code null}.
     */
    public @Nonnull String with(@Nonnull String delimeter) {
      checkNotNull(delimeter);
      if (Arrays.isNullOrEmpty(strings)) {
        return "";
      }
      StringBuilder b = new StringBuilder();
      int stringCount = strings.length;
      for (int i = 0; i < stringCount; i++) {
        String s = strings[i];
        b.append(s != null ? s : "");
        if (i < stringCount - 1) {
          b.append(delimeter);
        }
      }
      return b.toString();
    }
  }

  /**
   * Knows how to append a given {@code String} to the given target, only if the target does not end with the given
   * {@code String} to append.
   */
  public static class StringToAppend {
    private final String toAppend;

    StringToAppend(@Nonnull String toAppend) {
      this.toAppend = toAppend;
    }

    /**
     * Appends the {@code String} specified in the constructor to the {@code String} passed as argument.
     *
     * @param s the target {@code String}.
     * @return a {@code String} containing the target {@code String} with the given {@code String} to append added to
     *         the end.
     */
    public @Nonnull String to(@Nonnull String s) {
      if (!s.endsWith(toAppend)) {
        return concat(s, toAppend);
      }
      return s;
    }
  }
}
