package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringToIntAtoiTest {

    StringToIntAtoi stia;
    @Before
    public void setUp() throws Exception {
        stia = new StringToIntAtoi();
    }

    @Test
    public void myAtoiTest() {
        String s1, s2, s3;
        s1 = "42";
        s2 =  "   -42";
        s3 = "4193 with words";

        assertEquals(42,stia.myAtoi(s1));
        assertEquals(-42,stia.myAtoi(s2));
        assertEquals(4193,stia.myAtoi(s3));

    }
}