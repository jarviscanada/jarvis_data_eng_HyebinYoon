package ca.jrvs.apps.twitter.cli;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;

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
        CrdDao dao = new TwitterDao(httpHelper);
        Service service = new TwitterService(dao);
        Controller controller = new TwitterController(service);
        TwitterCLIApp app = new TwitterCLIApp(controller);

        //start the app
        app.run(args);
    }
    /*
    This method will be called by the `main` method. It parses `args` and calls the controller methods.
    It also prints tweet(s) returned by controller methods.
     */
    public void run(String[] args){

    }
}
