package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CompareTwoMapsTest extends TestCase {

    CompareTwoMaps compareTwoMaps;
    @Before
    public void setUp() throws Exception {
        compareTwoMaps = new CompareTwoMaps();
        super.setUp();
    }

    @Test
    public void testCompareMaps() {
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "A");
        map1.put(2, "B");
        map1.put(3, "C");

        Map<Integer, String> map2 = new HashMap<>();
        map2.put(3, "C");
        map2.put(2, "B");
        map2.put(1, "A");

        Assert.assertEquals(true, compareTwoMaps.compareMaps(map1, map2));

    }
}