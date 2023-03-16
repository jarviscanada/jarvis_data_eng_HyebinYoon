package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TwoSumTest extends TestCase {

        TwoSum twosum;
        @Before
        public void setUp() throws Exception{
            twosum = new TwoSum();
            super.setUp();
    }


       @Test
        public void testTwoSum() {

            int [] arr = new int [] {2,7,11,15};
            int target = 9;

           int [] arr1 = new int [] {3,2,4};
           int target1 = 6;

           int [] arr2 = new int [] {3,3};
           int target2 = 6;

           assertArrayEquals(new int [] {0,1}, twosum.twoSum(arr,target));
           assertArrayEquals(new int [] {1,2}, twosum.twoSum(arr1,target1));
           assertArrayEquals(new int [] {0,1}, twosum.twoSum(arr2,target2));
       }

    public void testTwoSumMap() {
        int [] arr = new int [] {2,7,11,15};
        int target = 9;

        int [] arr1 = new int [] {3,2,4};
        int target1 = 6;

        int [] arr2 = new int [] {3,3};
        int target2 = 6;

        assertArrayEquals(new int [] {0,1}, twosum.twoSum(arr,target));
        assertArrayEquals(new int [] {1,2}, twosum.twoSum(arr1,target1));
        assertArrayEquals(new int [] {0,1}, twosum.twoSum(arr2,target2));
    }
}