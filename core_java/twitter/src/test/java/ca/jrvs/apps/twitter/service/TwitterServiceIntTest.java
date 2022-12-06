package ca.jrvs.apps.twitter.service;


import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TwitterServiceIntTest {
    String hashtag = "#test";
    String text = "Hello text" + hashtag;
    float longitude = 18.18f;
    float latitude = -18.18f;

    public TwitterService service;


    @Before
    public void setup() throws Exception{
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        System.out.println(consumerKey+"|"+consumerSecret+"|"+accessToken+"|"+tokenSecret);
        //Create components
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        service = new TwitterService(new TwitterDao(httpHelper));
    }

    @Test
    public void PostTweetTest () throws JsonProcessingException{
        String invalidText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a enim at purus volutpat aliquet ac at orci. Etiam nec faucibus libero. Nullam nunc purus, mattis a nunc lacinia, blandit varius turpis. Nam in justo tincidunt, elementum sem a, mattis ipsum. Nullam eu odio nec ipsum sodales aliquet. ";
        float invalidLong = 1818.1f;
        float invalidLat = -1818.1f;

        Tweet badTextTweet =  TweetUtil.buildTweet(invalidText, longitude, latitude);
        Tweet badLongTweet =  TweetUtil.buildTweet(text, invalidLong, latitude);
        Tweet badLatTweet =  TweetUtil.buildTweet(text, longitude, invalidLat);

        //invalid text test
        try{
            service.postTweet(badTextTweet);
            fail();
        }catch(IllegalArgumentException e) {
            assertEquals("Tweet text must not exceeds 140 characters.", e.getMessage());
        }
        //invalid longitude test
        try{
            service.postTweet(badLongTweet);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Longitude coordinate is out of range.", e.getMessage());
        }

        //invalid latitude test
        try{
            service.postTweet(badLatTweet);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Latitude coordinate is out of range.", e.getMessage());
        }
        String newText = text+" "+System.currentTimeMillis();
        Tweet postTweet = TweetUtil.buildTweet(newText, longitude, latitude);
        Tweet tweet = service.postTweet(postTweet);

        //testing valid tweet
        assertEquals(newText, tweet.getText());
        int epsilon =(int) Math.abs(longitude - tweet.getCoordinates().getCoordinates()[0]); //required for float testing
        assertEquals(longitude, tweet.getCoordinates().getCoordinates()[0], epsilon);
        assertEquals(latitude, tweet.getCoordinates().getCoordinates()[1], epsilon);
    }
    @Test
    public void testShowTweet() throws JsonProcessingException {
        String id = "1184849393283498";
        String invalidId = "THISISBADID1234";
        String[] fields = {
                "created_at",
                "id",
                "id_str",
                "text",
                "entities",
                "coordinates",
                "retweet_count",
                "favorite_count",
                "favorited",
                "retweeted"
        };
        String[] invalidFields = {
                "_a",
                "idd",
                "id_string",
                "txt",
                "entity"
        };

        //invalid id testing
        try{
            service.showTweet(invalidId, fields);
            fail();
        }
        catch(IllegalArgumentException e){
            final String expected = "Tweet id must be numerical values";
            assertEquals(expected, e.getMessage());
        }

        //invalid field-testing
        try{
            service.showTweet(id, invalidFields);
        }
        catch(IllegalArgumentException e){
            final String expected = "_a idd id_string txt entity";
            assertEquals(expected, e.getMessage());
        }

        Tweet tweet = service.showTweet(id, fields);

        String expectedText = "@Hello Twitter Elmon Musk owns its #abc 39493853";

        assertEquals(expectedText, tweet.getText());
        int epsilon =(int) Math.abs(longitude - tweet.getCoordinates().getCoordinates()[0]);
        assertEquals(longitude, tweet.getCoordinates().getCoordinates()[0], epsilon);
        assertEquals(latitude, tweet.getCoordinates().getCoordinates()[1], epsilon);
        assertTrue(hashtag.contains(tweet.getEntities().getHashtags().get(0).getText()));
    }

    @Test
    public void testDeleteById() throws JsonProcessingException {
        String[] invalidIds = {"THISISBADID1234", "1595893122168672256"};

        //invalid id testing
        try{
            service.deleteTweets(invalidIds);
            fail();
        }
        catch(IllegalArgumentException e){
            final String expected = "Tweet id must be numerical values.";
            assertEquals(expected, e.getMessage());
        }

        long time = System.currentTimeMillis();
        Tweet twt = service.postTweet(TweetUtil.buildTweet(text+time, longitude, latitude));
        Tweet twt2 = service.postTweet(TweetUtil.buildTweet(text+time+"2", longitude, latitude));

        String [] ids = {twt.getIdStr(),twt2.getIdStr()};

        List<Tweet> deletedTweets = service.deleteTweets(ids);

        for(Tweet tweet : deletedTweets) {
            assertTrue(((text+time).equals(tweet.getText())) || (text+time+"2").equals(tweet.getText()));
            int epsilon = (int) Math.abs(longitude - tweet.getCoordinates().getCoordinates()[0]);
            assertEquals(longitude, tweet.getCoordinates().getCoordinates()[0], epsilon);
            assertEquals(latitude, tweet.getCoordinates().getCoordinates()[1], epsilon);
        }
    }


}