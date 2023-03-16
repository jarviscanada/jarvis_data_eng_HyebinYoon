package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RotateStringTest {
    RotateString rs;

    @Before
    public void setUp() throws Exception {
        rs = new RotateString();
    }
    @Test
    public void rsTest (){
        String s = "abcde";
        String goal = "cdeab";

        String s1 = "abcde";
        String goal1 = "abced";

        assertEquals(true, rs.rotateString(s,goal));
        assertEquals(false, rs.rotateString(s1,goal1));

        assertEquals(true, rs.rotateStringSimple(s,goal));
        assertEquals(false, rs.rotateStringSimple(s1,goal1));
    }
}