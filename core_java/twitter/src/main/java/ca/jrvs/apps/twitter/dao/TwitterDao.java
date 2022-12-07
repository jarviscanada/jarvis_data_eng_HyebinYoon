package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Repository
public class TwitterDao implements CrdDao<Tweet, String>{

    //URI constants
    private static final String API_BASE_URI="https://api.twitter.com";
    private static final String POST_PATH="/1.1/statuses/update.json";
    private static final String SHOW_PATH="/1.1/statuses/show.json";
    private static final String DELETE_PATH="/1.1/statuses/destroy";
    //URI symbols
    private static final String QUERY_SYM="?";
    private static final String AMPERSAND="&";
    private static final String EQUAL="=";

    //Response code
    private static final int HTTP_OK=200;

    private HttpHelper httpHelper;

    final Logger logger = LoggerFactory.getLogger(TwitterDao.class);

    @Autowired
    public TwitterDao (HttpHelper httpHelper){
        this.httpHelper = httpHelper;
    }

    @Override
    public Tweet create(Tweet tweet) {
        //Construct URI
        URI uri;
        try{
            uri = getPostUri(tweet);//| UnsupportedEncodingException
        }catch(URISyntaxException e){
            throw new IllegalArgumentException("Invalid tweet input", e);
        }

        //Execute HTTP Request
        HttpResponse response = httpHelper.httpPost(uri);

        //Validate
        return parseResponseBody(response, HTTP_OK);
    }

    /*
     * Usage : TwitterApp "post" "tweet_text" "latitude:longitude"
     * post : https://api.twitter.com/1.1/statuses/update.json
     * tweet_text :  parameter : status -> the text of the status update
     * Coordinate contains the longitude and latitude of the Tweet's location, as a collection in the form [longitude, latitude]
     * example : https://api.twitter.com/1.1/statuses/update.json?status=HelloWorld&long=37.78217.9&lat=-122.40062
     */
    public URI getPostUri(Tweet tweet) throws URISyntaxException {
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        // tweet -> coordinate instance -> get coordinate float []
        String coordinateLong = String.valueOf(tweet.getCoordinates().getCoordinates()[0]);
        String coordinateLan = String.valueOf(tweet.getCoordinates().getCoordinates()[1]);
        String tweetText = tweet.getText();


        return new URI(API_BASE_URI+POST_PATH+QUERY_SYM+"status"+EQUAL
                +percentEscaper.escape(tweetText)+AMPERSAND+"long"+EQUAL+coordinateLong
                +AMPERSAND+"lat"+EQUAL+coordinateLan);

    }
    public Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
        Tweet tweet = null;

        int status = response.getStatusLine().getStatusCode();
        if (status != expectedStatusCode){
            try{
                logger.debug(EntityUtils.toString(response.getEntity()));

            }catch (IOException e){
                logger.error("Response has no entity");
            }
            throw new RuntimeException("Unexpected HTTP status: "+status);
        }
        if (response.getEntity() == null){
            throw new RuntimeException("Empty response body");
        }

        //converting response Entity to string
        String jsonStr;
        try {
            jsonStr = EntityUtils.toString(response.getEntity());
        }catch (IOException e){
            throw new RuntimeException("Failed to convert entity to String", e);
        }

        //descending json to tweet object
        try{
            tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
        }
        catch(IOException e){
            throw new RuntimeException("Unable to convert JSON str to Object", e);
        }

        return tweet;
    }


    @Override
    public Tweet findById(String s) {
        //Construct URI
        URI uri;
        try{
            uri = getFindUri(s);
        }catch(URISyntaxException e){
            throw new IllegalArgumentException("Invalid tweet id", e);
        }

        //Execute HTTP Request
        HttpResponse response = httpHelper.httpGet(uri);

        //Validate
        return parseResponseBody(response, HTTP_OK);
    }

    public URI getFindUri(String id) throws URISyntaxException {
        /*
         https://api.twitter.com/1.1/statuses/show.json
         id : the numeric ID of the desired tweet
         example : GET https://api.twitter.com/1.1/statuses/show.json?id=210462857140252672
         */
        return new URI(API_BASE_URI+SHOW_PATH+QUERY_SYM+"id"+EQUAL+id);

    }

    @Override
    public Tweet deleteById(String s) {
        URI uri;
        try{
            uri = getDeleteUri(s);
        }
        catch(URISyntaxException e){
            throw new IllegalArgumentException("Invalid tweet input", e);
        }
        HttpResponse response = httpHelper.httpPost(uri);

        return parseResponseBody(response, HTTP_OK);
    }


    /*
    https://api.twitter.com/1.1/statuses/destroy/:id.json
    id : the numerical ID of the desired status
    example: POST https://api.twitter.com/1.1/statuses/destroy/240854986559455234.json
     */
    public URI getDeleteUri(String id) throws URISyntaxException{
        return new URI(API_BASE_URI+DELETE_PATH+"/"+id+".json");

    }





}
