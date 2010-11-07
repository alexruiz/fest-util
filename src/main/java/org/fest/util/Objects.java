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
 * Copyright @2006 the original author or authors.
 */
package org.fest.util;

import static org.fest.util.Arrays.isEmpty;

/**
 * Understands utility methods related to objects.
 *
 * @author Alex Ruiz
 */
public final class Objects {

  /** Prime number used to calculate the hash code of objects. */
  public static final int HASH_CODE_PRIME = 31;

  /**
   * Returns <code>true</code> if the given objects are equal or if both objects are <code>null</code>.
   * @param o1 one of the objects to compare.
   * @param o2 one of the objects to compare.
   * @return <code>true</code> if the given objects are equal or if both objects are <code>null</code>.
   */
  public static boolean areEqual(Object o1, Object o2) {
    if (o1 == null) return o2 == null;
    if (o1.equals(o2)) { return true; }
    if (o1.getClass().isArray() && o2.getClass().isArray()) {
      if (o1 instanceof Object[] && o2 instanceof Object[]) { return java.util.Arrays.equals((Object[]) o1, (Object[]) o2); }
      if (o1 instanceof boolean[] && o2 instanceof boolean[]) { return java.util.Arrays.equals((boolean[]) o1, (boolean[]) o2); }
      if (o1 instanceof byte[] && o2 instanceof byte[]) { return java.util.Arrays.equals((byte[]) o1, (byte[]) o2); }
      if (o1 instanceof char[] && o2 instanceof char[]) { return java.util.Arrays.equals((char[]) o1, (char[]) o2); }
      if (o1 instanceof double[] && o2 instanceof double[]) { return java.util.Arrays.equals((double[]) o1, (double[]) o2); }
      if (o1 instanceof float[] && o2 instanceof float[]) { return java.util.Arrays.equals((float[]) o1, (float[]) o2); }
      if (o1 instanceof int[] && o2 instanceof int[]) { return java.util.Arrays.equals((int[]) o1, (int[]) o2); }
      if (o1 instanceof long[] && o2 instanceof long[]) { return java.util.Arrays.equals((long[]) o1, (long[]) o2); }
      if (o1 instanceof short[] && o2 instanceof short[]) { return java.util.Arrays.equals((short[]) o1, (short[]) o2); }
    }
    return false;
  }
  
  /**
   * Returns an array containing the names of the given types.
   * @param types the given types.
   * @return the names of the given types stored in an array.
   */
  public static String[] namesOf(Class<?>... types) {
    if (isEmpty(types)) return new String[0];
    String[] names = new String[types.length];
    for (int i = 0; i < types.length; i++) names[i] = types[i].getName();
    return names;
  }

  /**
   * Returns the hash code for the given object. If the object is <code>null</code>, this method returns zero. Otherwise
   * calls the method <code>hashCode</code> of the given object.
   * @param o the given object.
   * @return the hash code for the given object
   */
  public static int hashCodeFor(Object o) {
    return o != null ? o.hashCode() : 0;
  }

  /**
   * Casts the given object to the given type only if the object is of the given type. If the object is not of the given
   * type, this method returns <code>null</code>.
   * @param <T> the generic type to cast the given object to.
   * @param o the object to cast.
   * @param type the given type.
   * @return the casted object, or <code>null</code> if the given object is not to the given type.
   */
  public static <T> T castIfBelongsToType(Object o, Class<T> type) {
    if (o != null && type.isAssignableFrom(o.getClass())) return type.cast(o);
    return null;
  }

  private Objects() {}
}
