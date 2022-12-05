package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwitterService implements Service{

    private CrdDao dao;

    //Text length
    private static final int TEXT_LENGTH_LIMIT = 140;
    // Latitude and Longitude Max/Mini Range
    private static final float LATITUDE_MAX_LIMIT = 180f;
    private static final float LATITUDE_MINI_LIMIT = -180f;
    private static final float LONGITUDE_MAX_LIMIT = 90f;
    private static final float LONGITUDE_MINI_LIMIT = -90f;

    @Autowired
    public TwitterService(CrdDao dao){
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(Tweet tweet) {
        //Business Logic:
        // e.g. text length, lat/lon range, id format
        int tweetLen =tweet.getText().length();
        if(tweetLen > TEXT_LENGTH_LIMIT){
            throw new IllegalArgumentException("Tweet text must not exceeds 140 characters.");
        }

        float [] coordinates = tweet.getCoordinates().getCoordinates();
        if (coordinates[0] > LONGITUDE_MAX_LIMIT || coordinates[0]< LONGITUDE_MINI_LIMIT){
            throw new IllegalArgumentException("Longitude coordinate is out of range.");
        } else if (coordinates[1] > LATITUDE_MAX_LIMIT || coordinates[1]< LATITUDE_MINI_LIMIT){
            throw new IllegalArgumentException("Latitude coordinate is out of range.");
        }

        return (Tweet) dao.create(tweet);
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        ArrayList<String> incorrectFields = new ArrayList<String>();
        ArrayList<String> correctFields = new ArrayList<String>(
                Arrays.asList("created_at",
                        "id",
                        "id_str",
                        "text",
                        "entities",
                        "coordinates",
                        "retweet_count",
                        "favorite_count",
                        "favorited",
                        "retweeted"));
        // Checking tweet id
        if (!id.matches("[0-9]+")){
            throw new IllegalArgumentException("Tweet id must be numerical values");
        }

        if (fields!=null){return null;}

        for (String field : fields){
            if (correctFields.contains(field)){continue;}
            else{
                incorrectFields.add(field);
            }
        }

        if (incorrectFields.size()!=0){
            StringBuilder exceptionMessage = new StringBuilder();
            for (String incorrectField : incorrectFields){
                exceptionMessage.append(" " +incorrectField);

            }
            throw new IllegalArgumentException(exceptionMessage.toString());
        }
        return (Tweet) dao.findById(id);
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        // Checking tweet ids
        for (String id : ids){
            if (!id.matches("[0-9]+")){
                throw new IllegalArgumentException("Tweet id must be numerical values.");
            }
        }


        List<Tweet> deletedTweets = new ArrayList<Tweet>();
        for(String id : ids){
            deletedTweets.add((Tweet)dao.deleteById(id));
        }

        return deletedTweets;
    }
}
