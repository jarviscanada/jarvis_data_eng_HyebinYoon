package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TwitterCLIApp {

    public static final String USAGE = "USAGE TwitterCLIApp post|show|delete [options]";
    private Controller controller;

    @Autowired
    public TwitterCLIApp(Controller controller){this.controller = controller;}
    /*
     Declare and instantiate all components and call run method which calls controller methods and print tweet(s).
     */
    public static void main(String[] args) {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        System.out.println(consumerKey+"|"+consumerSecret+"|"+accessToken+"|"+tokenSecret);
        //Create components
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
//        CrdDao dao = new TwitterDao(httpHelper);
//        Service service = new TwitterService(dao);
//        Controller controller = new TwitterController(service);
//        TwitterCLIApp app = new TwitterCLIApp(controller);
        // Instead manually inserting dependency, I can now use spring and do the following instead :
        TwitterCLIApp app = new TwitterCLIApp(
                new TwitterController(new TwitterService(new TwitterDao(httpHelper))));

        //start the app
        app.run(args);
    }
    /*
    This method will be called by the `main` method. It parses `args` and calls the controller methods.
    It also prints tweet(s) returned by controller methods.
     */
    public void run(String[] args) {

        // if args.size = 0 or 1 : invalid
        // args[0] = post|show|delete
        // args[1] = [options] like fields1, fields2
        // Example :
        // postTweet : jrvs/twitter_app post "test post" "43:79"
        // showTweet : jrvs/twitter_app show 1276568976764686343
        // deleteTweet jrvs/twitter_app delete 1200145224103841792

        if (args.length == 0 || args.length == 1){
            throw new IllegalArgumentException(USAGE);
        }

        switch (args[0].toLowerCase()){
            case "post":
                printTweet(controller.postTweet(args)); // return Tweet obj
                break;
            case "show" :
                printTweet(controller.showTweet(args)); // return Tweet obj
                break;
            case "delete":
                printTweet(controller.deleteTweet(args)); // return List<Tweet>
                break;

            default :
                throw new IllegalArgumentException(USAGE);

        }
    }

    private void printTweet(Tweet tweet) {
        try {
            System.out.println(JsonUtil.toJson(tweet, true, true));
        }
        catch (JsonProcessingException e){
            throw new RuntimeException("Unable to convert tweet object to string", e);
        }
    }
    private void printTweet(List<Tweet> tweets){
        try {
            for (Tweet tweet : tweets){
                System.out.println(JsonUtil.toJson(tweet, true, true));
            }
        }
        catch (JsonProcessingException e){
            throw new RuntimeException("Unable to convert tweet object to string", e);
        }
    }
}
