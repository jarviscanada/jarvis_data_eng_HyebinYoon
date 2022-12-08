package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidPalindromeTest {

    ValidPalindrome palindrome ;
    @Before
    public void setUp() throws Exception {
        palindrome = new ValidPalindrome();

    }

    @Test
    public void isPalindrome(){
        assertEquals(true, palindrome.isPalindrome("A man, a plan, a canal: Panama"));
        assertEquals(false, palindrome.isPalindrome("race a car"));
    }

}