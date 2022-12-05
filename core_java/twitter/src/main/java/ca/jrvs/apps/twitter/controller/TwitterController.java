package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterController implements Controller {

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";

    private Service service;

    //@AutoWired
    public TwitterController(Service service){ this.service = service;}

    @Override
    public Tweet postTweet(String[] args) {
        if(args.length != 3){
            throw new IllegalArgumentException(
                    "USAGE: TwitterCLIApp post \"tweet_text\"\"latitude:longitude\"");
        }

        String tweet_txt = args[1];
        String [] coordinates = args[2].split(COORD_SEP);
        if (coordinates.length != 2){
            throw new IllegalArgumentException("Invalid number of coordinates. "+
                    "Usage: TwitterApp \"post\" \"tweet_text\" \"latitude:longitude\" ");
        }

        Tweet tweet;

        try {
            float longitude = Float.parseFloat(coordinates[0]);
            float latitude = Float.parseFloat(coordinates[1]);
            tweet = TweetUtil.buildTweet(tweet_txt, longitude, latitude);
        }catch(Exception e) {
            throw new IllegalArgumentException("Invalid coordinates (Must be float). " +
                    "Usage: TwitterApp \"post\" \"tweet_text\" \"latitude:longitude\"");
        }

        return service.postTweet(tweet);
    }

    @Override
    public Tweet showTweet(String[] args) {
        //USAGE: TwitterApp show tweet_id [field1,fields2]
        if(args.length > 3 ){
            throw new IllegalArgumentException(
                    "Usage: TwitterCLIApp show \"tweet_id \"field1, field2\"");
        }

        String id = args[1];
        // no field
        if (args.length==2){
            return service.showTweet(id, null);
        }

        String [] fields = args[2].split(COMMA);

        List<String> fieldsList = new ArrayList<String>( Arrays.asList(args[2].split(COMMA)));

        fieldsList.stream()
                //remove one or more whitespace
                .map(str -> str.replaceAll("\\s+",""))
                .map(str -> str.toLowerCase())
                .collect(Collectors.toList());
        String [] fieldsArray = fieldsList.toArray(new String[fieldsList.size()]);

        return service.showTweet(id,fieldsArray);
    }


    @Override
    public List<Tweet> deleteTweet(String[] args) {
       // USAGE: TwitterApp delete [id1,id2,..]
        if(args.length!=2) {
            throw new IllegalArgumentException("Invalid or " +
                    "Missing Arguments. Usage: TwitterApp delete \"id1,id2,..\"");
        }
        String [] ids = args[1].split(",");

        return service.deleteTweets(ids);

    }
}
