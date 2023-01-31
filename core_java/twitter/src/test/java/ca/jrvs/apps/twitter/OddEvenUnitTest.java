package ca.jrvs.apps.twitter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OddEvenUnitTest {
    @BeforeClass
    public static void clasSetUp() throws Exception {
        System.out.println("--@BeforeClass static method runs once before class it instantiate");
    }
    @AfterClass
    public static void classTearDown() throws Exception {
        System.out.println("--@AfterClass static method runs once");
    }
    private OddEven oddEven;

    @Before
    public void setUp() throws Exception {
        System.out.println("--@Before method runs before each @Test method");
        //instantiate a test class for each @Test method
        oddEven = new OddEven();
    }

    @After
    public void tearDown() throws Exception{
        System.out.println("--@After method runs after each @Test method");
    }
    @Test
    public void OddEvenMod() {
        System.out.println("Test case: test oddEvenMod method from the test class");
        String expected = "odd";
        Assert.assertEquals(expected, oddEven.oddEvenMod(3));
        expected = "even";
        Assert.assertEquals(expected,oddEven.oddEvenMod(4));
    }

    @Test
    public void oddEvenBit(){
        System.out.println("Test case: test oddEvenBit method from the test class");
    }

}
