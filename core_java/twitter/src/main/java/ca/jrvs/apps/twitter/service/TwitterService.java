package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TwitterService implements Service{

    private CrdDao dao;

    @Autowired
    public TwitterService(CrdDao dao){
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(Tweet tweet) {
        //Business Logic:
        // e.g. text length, lat/lon range, id format

        return (Tweet) dao.create(tweet);
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        return null;
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        return null;
    }
}
