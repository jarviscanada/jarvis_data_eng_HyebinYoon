package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * Challenge: How to compare two maps?
 * https://www.notion.so/jarvisdev/How-to-compare-two-maps-7aa248d1298546d487d187806aecdfa3
 * https://github.com/frohoff/jdk8u-jdk/blob/da0da73ab82ed714dc5be94acd2f0d00fbdfe2e9/src/share/classes/java/util/HashMap.java#L305
 */
public class CompareTwoMaps {
    /**
     * Time complexity : O(n)
     * @param m1
     * @param m2
     * @return
     * @param <K>
     * @param <V>
     */
    public <K,V> boolean compareMaps(Map<K,V> m1, Map<K,V> m2){
        // different size == not equal
        if (m1.size() != m2.size()){
            return false;
        }
        for (K key : m1.keySet()){
            if (!m1.get(key).equals(m2.get(key)) ){
                return false;
            }
        }
        return true;
    }
}
