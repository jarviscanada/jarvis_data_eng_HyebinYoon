package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidParenthesesTest {

        ValidParentheses parentheses;
    @Before
    public void setUp() throws Exception {
        parentheses = new ValidParentheses();

    }

    @Test
    public void isValid() {

        Assert.assertEquals(true, parentheses.isValid("()"));
        Assert.assertEquals(true, parentheses.isValid("()[]{}"));
        Assert.assertEquals(false, parentheses.isValid("(][]"));
        Assert.assertEquals(false, parentheses.isValid("([)]"));
        Assert.assertEquals(true, parentheses.isValid("{[]}"));
    }
}