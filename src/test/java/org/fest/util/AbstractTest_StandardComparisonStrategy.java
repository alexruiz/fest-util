package org.fest.util;

import static org.junit.rules.ExpectedException.none;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class AbstractTest_StandardComparisonStrategy {

  @Rule
  public ExpectedException thrown = none();
  protected static StandardComparisonStrategy standardComparisonStrategy = StandardComparisonStrategy.instance(); 

}