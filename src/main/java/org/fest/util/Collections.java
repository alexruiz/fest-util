/*
 * Created on Apr 29, 2007
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
 * Copyright @2007-2011 the original author or authors.
 */
package org.fest.util;

import static java.util.Collections.*;

import static org.fest.util.ToString.toStringOf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility methods related to collections.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public final class Collections {

  /**
   * Creates a list containing the given elements.
   * @param <T> the type of elements of the list to create.
   * @param elements the elements to store in the list.
   * @return the created list.
   */
  public static <T> List<T> list(T... elements) {
    if (elements == null) return null;
    List<T> list = new ArrayList<T>();
    for (T e : elements)
      list.add(e);
    return list;
  }

  /**
   * Creates a list containing the given element.
   * @param <T> the type of elements of the list to create.
   * @param element the element to store in the list.
   * @return the created list.
   * @since 1.2.2
   */
  public static <T> List<T> list(T element) {
    if (element == null) return null;
    List<T> list = new ArrayList<T>();
    list.add(element);
    return list;
  }

  /**
   * Creates a list containing the given elements.
   * @param <T> the type of elements of the list to create.
   * @param first the first element to store in the list.
   * @param second the second element to store in the list.
   * @return the created list.
   * @since 1.2.2
   */
  public static <T> List<T> list(T first, T second) {
    List<T> list = new ArrayList<T>();
    list.add(first);
    list.add(second);
    return list;
  }

  /**
   * Creates a set containing the given elements.
   * @param <T> the type of elements of the set to create.
   * @param elements the elements to store in the set.
   * @return the created set.
   * @since 1.1.5
   */
  public static <T> Set<T> set(T... elements) {
    if (elements == null) return null;
    Set<T> set = new LinkedHashSet<T>();
    for (T e : elements)
      set.add(e);
    return set;
  }

  /**
   * Creates a set containing the given element.
   * @param <T> the type of elements of the set to create.
   * @param element the element to store in the set.
   * @return the created set.
   * @since 1.2.2
   */
  public static <T> Set<T> set(T element) {
    if (element == null) return null;
    Set<T> set = new LinkedHashSet<T>();
    set.add(element);
    return set;
  }

  /**
   * Creates a set containing the given elements.
   * @param <T> the type of elements of the set to create.
   * @param first the first element to store in the set.
   * @param second the second element to store in the set.
   * @return the created set.
   * @since 1.2.2
   */
  public static <T> Set<T> set(T first, T second) {
    Set<T> set = new LinkedHashSet<T>();
    set.add(first);
    set.add(second);
    return set;
  }

  /**
   * Returns any duplicate elements from the given collection.
   * @param <T> the generic type of the given collection.
   * @param c the given collection that might have duplicate elements.
   * @return a collection containing the duplicate elements of the given one. If no duplicates are found, an empty
   *         collection is returned.
   */
  public static <T> Collection<T> duplicatesFrom(Collection<T> c) {
    Set<T> duplicates = new HashSet<T>();
    if (isEmpty(c)) return duplicates;
    Set<T> noDuplicates = new HashSet<T>();
    for (T e : c) {
      if (noDuplicates.contains(e)) {
        duplicates.add(e);
        continue;
      }
      noDuplicates.add(e);
    }
    return duplicates;
  }

  /**
   * Returns {@code true} if the given {@link Iterable} is {@code null} or empty.
   * @param iterable the {@link Iterable} to check.
   * @return {@code true} if the given {@link Iterable} is {@code null} or empty, otherwise {@code false}.
   */
  public static boolean isEmpty(Iterable<?> iterable) {
    return iterable == null || !iterable.iterator().hasNext();
  }

  /**
   * Returns the size of the given {@link Iterable}.
   * @param iterable the {@link Iterable} to get size.
   * @return the size of the given {@link Iterable}..
   * @throws IllegalArgumentException if given {@link Iterable} is null.
   */
  public static int sizeOf(Iterable<?> iterable) {
    if (iterable == null) throw new IllegalArgumentException("iterable parameter must not be null");
    int size = 0;
    for (@SuppressWarnings("unused") Object object : iterable) {
      size++;
    }
    return size;
  }
  
  public static <T> List<T> filter(Collection<?> target, CollectionFilter<T> filter) {
    return filter.filter(target);
  }

  /**
   * Returns the {@code String} representation of the given collection, or {@code null} if the given collection is
   * {@code null}.
   * @param c the collection to format.
   * @return the {@code String} representation of the given collection.
   */
  public static String format(Collection<?> c) {
    if (c == null) return null;
    Iterator<?> i = c.iterator();
    if (!i.hasNext()) return "[]";
    StringBuilder b = new StringBuilder();
    b.append('[');
    for (;;) {
      Object e = i.next();
      b.append(e == c ? "(this Collection)" : toStringOf(e));
      if (!i.hasNext()) return b.append(']').toString();
      b.append(", ");
    }
  }

  /**
   * Returns a new unmodifiable collection containing the non-null elements of the given collection. This method returns
   * an empty unmodifiable collection if the given collection has only {@code null} elements or if it is empty. This
   * method returns {@code null} if the given collection is {@code null}.
   * @param <T> the type of elements of the collection.
   * @param c the collection we want to extract non null elements from.
   * @return a new unmodifiable collection containing the non-null elements of the given collection, or {@code null} if
   *         the given collection is {@code null}.
   * @since 1.1.3
   */
  public static <T> Collection<T> nonNullElements(Collection<T> c) {
    if (c == null) return null;
    Collection<T> nonNullElements = new ArrayList<T>();
    for (T o : c)
      if (o != null) nonNullElements.add(o);
    return unmodifiableCollection(nonNullElements);
  }

  /**
   * Returns a new unmodifiable list containing the non-null elements of the given list. This method returns an empty
   * unmodifiable list if the given list has only {@code null} elements or if it is empty. This method returns
   * {@code null} if the given list is {@code null}.
   * @param <T> the type of elements of the list.
   * @param l the list we want to extract non null elements from.
   * @return a new unmodifiable list containing the non-null elements of the given list, or {@code null} if the given
   *         list is {@code null}.
   * @since 1.1.3
   */
  public static <T> List<T> nonNullElements(List<T> l) {
    Collection<T> nonNullElements = nonNullElements((Collection<T>) l);
    if (nonNullElements == null) return null;
    return unmodifiableList(new ArrayList<T>(nonNullElements));
  }

  /**
   * Returns {@code true} if the given {@link Iterable} has only {@code null} elements, {@code false} otherwise. If given
   * {@link Iterable} is empty, this method returns {@code true}.
   * @param iterable the given iterable. <b>It must not be null</b>.
   * @return {@code true} if the given iterable has only {@code null} elements or is empty, {@code false} otherwise.
   * @throws NullPointerException if the given iterable is {@code null}.
   * @since 1.1.3
   */
  public static boolean hasOnlyNullElements(Iterable<?> iterable) {
    if (iterable == null) throw new NullPointerException("The iterable to check should not be null");
    if (isEmpty(iterable)) return false;
    for (Object element : iterable)
      if (element != null) return false;
    return true;
  }

  /**
   * Creates a list containing the given {@link Iterable} elements.
   * @param <T> the type of elements of the list to create.
   * @param iterable the {@link Iterable} to get elements from to store in the list.
   * @return the created list.
   */
  public static <T> List<T> list(Iterable<T> iterable) {
    if (iterable == null) return null;
    List<T> list = new ArrayList<T>();
    for (T e : iterable)
      list.add(e);
    return list;
  }

  private Collections() {}
}
