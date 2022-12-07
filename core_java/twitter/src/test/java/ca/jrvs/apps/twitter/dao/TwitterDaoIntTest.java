//package ca.jrvs.apps.twitter.dao;
//
//import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
//import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
//import ca.jrvs.apps.twitter.model.Tweet;
//import ca.jrvs.apps.twitter.util.JsonUtil;
//import ca.jrvs.apps.twitter.util.TweetUtil;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class TwitterDaoIntTest {
//
//    TwitterDao dao;
//    String hashTag = "#abc";
//    String text = "@someone Hello World " + hashTag + " " + System.currentTimeMillis();
//    float lon = 40.0f;
//    float lat = -49.0f;
//
//    @Before
//    public void setUp() {
//        String consumerKey = System.getenv("consumerKey");
//        String consumerSecret = System.getenv("consumerSecret");
//        String accessToken = System.getenv("accessToken");
//        String tokenSecret = System.getenv("tokenSecret");
//        System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
//        //Create components
//        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
//
//        //passing dependency
//        this.dao = new TwitterDao(httpHelper);
//    }
//
//    @Test
//    public void create() throws Exception {
//
//        Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
//        Tweet tweet = dao.create(postTweet);
//        System.out.println(JsonUtil.toJson(tweet, true, true));
//
//        assertEquals(text, tweet.getText());
//        assertNotNull(tweet.getCoordinates());
//        assertEquals(2, tweet.getCoordinates().getCoordinates().length);
//        assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0.01d);
//        assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0.01d);
//        assertTrue(hashTag.contains(tweet.getEntities().getHashtags().get(0).getText()));
//    }
//
//      @Test
//      public void findByID() {
//        String id = "1598460514589450241";
//        String expected = "@someone Hello World #abc 1669937658489";
//
//
//        Tweet tweet = dao.findById(id);
//
//        assertEquals(expected, tweet.getText());
//        assertNotNull(tweet.getCoordinates());
//        assertEquals(2, tweet.getCoordinates().getCoordinates().length);
//        assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0.01d);
//        assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0.01d);
//        assertTrue(hashTag.contains(tweet.getEntities().getHashtags().get(0).getText()));
//
//    }
//    @Test
//    public void deleteById(){
//        Tweet postTweet = dao.create(TweetUtil.buildTweet(text+" COPY", lon, lat));
//        Tweet tweet = dao.deleteById(postTweet.getIdStr());
//
//        assertEquals(text+" COPY", tweet.getText());
//        assertNotNull(tweet.getCoordinates());
//        assertEquals(2, tweet.getCoordinates().getCoordinates().length);
//        assertEquals(lon, tweet.getCoordinates().getCoordinates()[0],0.01d);
//        assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0.01d);
//        assertTrue(hashTag.contains(tweet.getEntities().getHashtags().get(0).getText()));
//    }
//
//}
//
//
