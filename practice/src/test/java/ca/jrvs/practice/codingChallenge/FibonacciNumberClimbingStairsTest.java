package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;
import org.junit.*;


public class FibonacciNumberClimbingStairsTest extends TestCase {

    FibonacciNumberClimbingStairs fnc;

    @Before
    public void setUp() throws Exception{
        fnc = new FibonacciNumberClimbingStairs();

    }
    // @DisplayName("No Recursion ")
    @Test
    public void testClimbStairs() {

        assertEquals(2,fnc.climbStairs(3) );
        assertEquals(21, fnc.climbStairs(8) );

    }
    //@DisplayName("Recursion")
    @Test
    public void testClimbStairsRec(){
        assertEquals(2,fnc.climbStairsRec(3));
        assertEquals(21,fnc.climbStairsRec(8) );

    }
}