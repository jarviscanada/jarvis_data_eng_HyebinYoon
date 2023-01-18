//package ca.jrvs.apps.twitter.service;
//
//import ca.jrvs.apps.twitter.dao.CrdDao;
//import ca.jrvs.apps.twitter.model.Tweet;
//import ca.jrvs.apps.twitter.util.JsonUtil;
//import ca.jrvs.apps.twitter.util.TweetUtil;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.io.IOException;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doReturn;
//
//@RunWith(MockitoJUnitRunner.class)
//public class TwitterServiceUnitTest {
//
//    @Mock
//    CrdDao dao;
//
//    @InjectMocks
//    TwitterService service ;
//
//    String text = "testing test 123";
//    String text2 = "hello hyebin heather test "+ System.currentTimeMillis();
//    float longitude = 18.1f;
//    float latitude = -18.1f;
//
//    String tweetJsonStr = "{\n"
//            + "   \"created_at\":\"Mon Dec 5 20:34:29 +0000 2022\",\n"
//            + "   \"id\":1595893122168672256,\n"
//            + "   \"id_str\":\"1595893122168672256\",\n"
//            + "   \"text\":\"testing test 123\",\n"
//            + "   \"entities\":{\n"
//            + "      \"hashtags\":[],"
//            + "      \"user_mentions\":[]"
//            + "   },\n"
//            + "   \"coordinates\":null,"
//            + "   \"retweet_count\":0,\n"
//            + "   \"favorite_count\":0,\n"
//            + "   \"favorited\":false,\n"
//            + "   \"retweeted\":false\n"
//            + "}";
//
//    @Before
//    public void setUp() throws Exception {
//        service = new TwitterService(dao);
//    }
//
//    @Test
//    public void postTweet() throws Exception{
//        String invalidText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a enim at purus volutpat aliquet ac at orci. Etiam nec faucibus libero. Nullam nunc purus, mattis a nunc lacinia, blandit varius turpis. Nam in justo tincidunt, elementum sem a, mattis ipsum. Nullam eu odio nec ipsum sodales aliquet. ";
//        float invalidLong = 1818.1f;
//        float invalidLat = -1818.1f;
//        Tweet badTextTweet =  TweetUtil.buildTweet(invalidText, longitude, latitude);
//        Tweet badLongTweet =  TweetUtil.buildTweet(text, invalidLong, latitude);
//        Tweet badLatTweet =  TweetUtil.buildTweet(text, longitude, invalidLat);
//
//        //invalid text test
//        try{
//            service.postTweet(badTextTweet);
//            fail();
//        }catch(IllegalArgumentException e) {
//            assertEquals("Tweet text must not exceeds 140 characters.", e.getMessage());
//        }
//        //invalid longitude test
//        try{
//            service.postTweet(badLongTweet);
//            fail();
//        }
//        catch(IllegalArgumentException e){
//            assertEquals("Longitude coordinate is out of range.", e.getMessage());
//        }
//
//        //invalid latitude test
//        try{
//            service.postTweet(badLatTweet);
//            fail();
//        }
//        catch(IllegalArgumentException e){
//            assertEquals("Latitude coordinate is out of range.", e.getMessage());
//        }
//
//        Tweet expected = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
//        doReturn(expected).when(dao).create(any());
//        Tweet tweet = service.postTweet(TweetUtil.buildTweet(text, longitude, latitude));
//
//        assertEquals(text, tweet.getText());
//        assertEquals(null, tweet.getCoordinates());
//
//    }
//
//    @Test
//    public void showTweet() throws IOException {
//        String id = "1595893122168672256";
//        String invalidId = "THISISBADID1234";
//        String[] fields = {
//                "created_at",
//                "id",
//                "id_str",
//                "text",
//                "entities",
//                "coordinates",
//                "retweet_count",
//                "favorite_count",
//                "favorited",
//                "retweeted"
//        };
//        String[] invalidFields = {
//                "_a",
//                "idd",
//                "id_string",
//                "txt",
//                "entity"
//        };
//
//        //invalid id testing
//        try{
//            service.showTweet(invalidId, fields);
//            fail();
//        }
//        catch(IllegalArgumentException e){
//            final String expected = "Tweet id must be numerical values";
//            assertEquals(expected, e.getMessage());
//        }
//
//        //invalid field-testing
//        try{
//            service.showTweet(id, invalidFields);
//            fail();
//        }
//        catch(IllegalArgumentException e){
//            System.out.println("hello");
//            final String expected = "_a idd id_string txt entity ";
//            assertEquals(expected, e.getMessage());
//        }
//
//        Tweet expected = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
//        doReturn(expected).when(dao).findById(any());
//        Tweet tweet = service.showTweet(id, fields);
//
//        assertEquals(text, tweet.getText());
//        assertEquals(null, tweet.getCoordinates());
//
//    }
//
//    @Test
//    public void deleteTweets() throws IOException {
//        String[] ids = {"1595893122168672256", "159589312216867543534"};
//        String[] invalidIds = {"THISISBADID1234", "1595893122168672256"};
//
//        //invalid id testing
//        try{
//            service.deleteTweets(invalidIds);
//            fail();
//        }
//        catch(IllegalArgumentException e){
//            final String expected = "Tweet id must be numerical values.";
//            assertEquals(expected, e.getMessage());
//        }
//        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
//        doReturn(expectedTweet).when(dao).deleteById(any());
//        List<Tweet> tweets = service.deleteTweets(ids);
//        for(Tweet tweet : tweets) {
//            assertTrue((text.equals(tweet.getText())) || text2.equals(tweet.getText()));
//            assertEquals(null, tweet.getCoordinates());
//
//        }
//    }
//}