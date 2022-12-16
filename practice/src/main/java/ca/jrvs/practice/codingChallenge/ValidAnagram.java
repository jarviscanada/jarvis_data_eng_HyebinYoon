package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * Coding Challenge: Valid Anagram
 * https://www.notion.so/jarvisdev/Valid-Anagram-9303aa27f0384455a4840e8007a42cc8
 */
public class ValidAnagram {
    /**
     * First approach is simple, using sort Array .
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramSimple(String s, String t) {
        char [] sText = s.toCharArray();
        char [] tText = t.toCharArray();

        Arrays.sort(sText);
        Arrays.sort(tText);
        if (Arrays.equals(sText,tText)) {
            return true;
        }
        return false;


    }
    /**
     * Second aprroach is finding pattern!. map.add(svalue, true or false)
     * then loop t, if map contains t change value to true.
     * at the end if map has false value that means its invalid anagram.
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramMap(String s, String t) {
    return true;

    }

    /**
     * Check whether two strings are anagrams of each other by counting frequency.
     * assumption that the set of possible characters in both strings is small. that the characters are stored using 8 bit and there can be 256 possible characters.
     *
     * So count the frequency of the characters and if the frequency of characters in both strings are the same, they are anagram of each other.
     * @param s
     * @param s
     * @return
     */
    public boolean isAnagramFrequency(String s , String t){
        return false;
    }
}
