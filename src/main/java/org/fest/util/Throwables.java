/*
 * Created on Dec 13, 2008
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
 * Copyright @2008-2011-2010 the original author or authors.
 */
package org.fest.util;

import static org.fest.util.Collections.list;

import java.util.*;

/**
 * Utility methods related to <code>{@link Throwable}</code>s.
 * 
 * @author Alex Ruiz
 */
public final class Throwables {

  /**
   * Appends the stack trace of the current thread to the one in the given <code>{@link Throwable}</code>.
   * @param t the given {@code Throwable}.
   * @param methodToStartFrom the name of the method used as the starting point of the current thread's stack trace.
   */
  public static void appendCurrentThreadStackTraceToThrowable(Throwable t, String methodToStartFrom) {
    List<StackTraceElement> stackTrace = list(t.getStackTrace());
    stackTrace.addAll(currentThreadStackTrace(methodToStartFrom));
    t.setStackTrace(stackTrace.toArray(new StackTraceElement[stackTrace.size()]));
  }

/**
   * Removes the Fest elements from the <code>{@link Throwable}</code> stack trace that have little value for end user.
   * <pre>So instead of seeing this:

org.junit.ComparisonFailure: expected:<'[Ronaldo]'> but was:<'[Messi]'>
  at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
  at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:39)
  at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:27)
  at java.lang.reflect.Constructor.newInstance(Constructor.java:501)
  at org.fest.assertions.error.ConstructorInvoker.newInstance(ConstructorInvoker.java:34)
  at org.fest.assertions.error.ShouldBeEqual.newComparisonFailure(ShouldBeEqual.java:111)
  at org.fest.assertions.error.ShouldBeEqual.comparisonFailure(ShouldBeEqual.java:103)
  at org.fest.assertions.error.ShouldBeEqual.newAssertionError(ShouldBeEqual.java:81)
  at org.fest.assertions.internal.Failures.failure(Failures.java:76)
  at org.fest.assertions.internal.Objects.assertEqual(Objects.java:116)
  at org.fest.assertions.api.AbstractAssert.isEqualTo(AbstractAssert.java:74)
  at examples.StackTraceFilterExample.main(StackTraceFilterExample.java:13)
  
We get this:

org.junit.ComparisonFailure: expected:<'[Ronaldo]'> but was:<'[Messi]'>
  at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
  at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:39)
  at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:27)
  at examples.StackTraceFilterExample.main(StackTraceFilterExample.java:20)
   * </pre>
   * @param throwable the {@code Throwable} to filter stack trace.
   */
  public static void removeFestRelatedElementsFromStackTrace(Throwable throwable) {
    List<StackTraceElement> filteredStackTrace = list(throwable.getStackTrace());
    StackTraceElement previousStackTraceElement = null;
    for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
      if (stackTraceElement.getClassName().contains("org.fest")) {
        filteredStackTrace.remove(stackTraceElement);
        // handle the case when Fest builds a ComparisonFailure by reflection (see ShouldBeEqual.newAssertionError
        // method), the stack trace looks like :
        // java.lang.reflect.Constructor.newInstance(Constructor.java:501),
        // org.fest.assertions.error.ConstructorInvoker.newInstance(ConstructorInvoker.java:34),
        // we want to get rid of java.lang.reflect.Constructor.newInstance element because it is related to Fest.
        if (stackTraceElementClassNameIs(previousStackTraceElement, "java.lang.reflect.Constructor")
            && stackTraceElement.getClassName().contains("org.fest.assertions.error.ConstructorInvoker")) {
          filteredStackTrace.remove(previousStackTraceElement);
        }
      }
      previousStackTraceElement = stackTraceElement;
    }
    throwable.setStackTrace(filteredStackTrace.toArray(new StackTraceElement[filteredStackTrace.size()]));
  }

  private static boolean stackTraceElementClassNameIs(StackTraceElement stackTraceElement, String className) {
    if (stackTraceElement == null) return false;
    return stackTraceElement.getClassName().equals(className);
  }

  private static List<StackTraceElement> currentThreadStackTrace(String methodToStartFrom) {
    List<StackTraceElement> filtered = stackTraceInCurrentThread();
    List<StackTraceElement> toRemove = new ArrayList<StackTraceElement>();
    for (StackTraceElement e : filtered) {
      if (methodToStartFrom.equals(e.getMethodName())) break;
      toRemove.add(e);
    }
    filtered.removeAll(toRemove);
    return filtered;
  }

  private static List<StackTraceElement> stackTraceInCurrentThread() {
    return list(StackTraces.instance().stackTraceInCurrentThread());
  }

  private Throwables() {}
}
