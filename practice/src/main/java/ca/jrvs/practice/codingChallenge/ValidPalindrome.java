package ca.jrvs.practice.codingChallenge;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Coding Challenge: Valid Palindrome
 * https://www.notion.so/jarvisdev/Valid-Palindrome-c4a697bae43b4cdfaccf292fb0dfbc42
 */
public class ValidPalindrome {

    /**
     *
     * @param s includes both upper and lower case. special chars + number included
     * @return boolean
     */
    public boolean isPalindrome(String s) {
        String text = s.toLowerCase();
        text = text.replaceAll("[^a-zA-Z0-9]","");
        int frontCounter = 0;
        int backCounter = text.length()-1;

        while (frontCounter < backCounter){
            if (text.charAt(frontCounter) !=text.charAt(backCounter)){
                return false;
            }else {
                frontCounter ++;
                backCounter --;
            }
        }
        return true;
    }

    public boolean isPalindromeBetter(String s) {

        /**
         * char(): get an IntStream of the characters (you may want to also look at codePoints())
         * map each 'character' value to Character (you need to cast to actually say that its really a char, and then Java will box it automatically to Character)
         */

        List<Character> text = s.toLowerCase()
                .replaceAll("[^0-9a-z]", "")
                .chars()
                .mapToObj(e->(char)e)
                .collect(Collectors.toList());

        int i = 0;
        int j = text.size()-1;

        while (i<j){
            if (text.get(i) != text.get(j)) return false;
            i++;
            j--;
        }
        return true;
    }

}
