package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;

public class TweetUtil {
    
    public static Tweet buildTweet(String text, float longitude, float latitude){
        Tweet tweet = new Tweet ();
        Coordinates coordinates = new Coordinates();
        
        // set instance of Coordinate 
        float [] coordinateArray = {longitude, latitude};

        coordinates.setCoordinates(coordinateArray);
        coordinates.setType("Point");
        
        // set text and instance of coordinate to tweet attribute
        tweet.setText(text);
        tweet.setCoordinates(coordinates);
        return tweet;
        
        
    }
}
