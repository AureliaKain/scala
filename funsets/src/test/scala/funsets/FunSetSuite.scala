package funsets

import org.junit._

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite {

  import FunSets._

  @Test def `contains is implemented`: Unit = {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = Set(2, 3, 4)
  }

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remvoe the
   * @Ignore annotation.
   */
   @Test def `singleton set one contains one`: Unit = {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  @Test def `union contains all elements of each set`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  @Test def `intersection contains all elements in common in each set`: Unit = {
    new TestSets {
      val s = intersect(s1, s2)
      val sbis = intersect(s2, s4)
      assert(!contains(s, 1), "Intersect 1")
      assert(contains(sbis, 2), "Intersect 2")
    }
  }

  @Test def `diff contains all elements in one set which are not in the other set`: Unit = {
    new TestSets {
      val s = diff(s4, s2)
      assert(contains(s, 3), "Diff 1")
      assert(!contains(s, 2), "Diff 2")
    }
  }

  @Test def `filter returns elements of a set on which a predicat holds`: Unit = {
    new TestSets {
      def p(x:Int) = (x>=3==true)
      val s = filter(s4, p)
      assert(contains(s, 3), "Filter 1")
      assert(!contains(s, 2), "Filter 2")
      assert(!contains(s, 1), "Filter 3")
    }
  }

  @Test def `check if all bounded integers within a set satisfy a predicat`: Unit = {
    new TestSets {
      def p(x:Int) = (x>=1)
          assert(forall(s4, p)==true, "forall 1")
    }
  }

  @Test def `check if a bounded integer exists in a set`: Unit = {
    new TestSets {
      def p(x:Int) = (x>=0)
      assert(exists(s4, p)==true, "exists 1")
    }
  }
  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
