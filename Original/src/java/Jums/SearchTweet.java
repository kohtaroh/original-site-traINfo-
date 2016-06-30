/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jums;

/**
 *
 * @author kotaroh
 */
import java.io.PrintWriter;
import java.util.List;
import twitter4j.*;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.User;
import twitter4j.ResponseList;
import twitter4j.Paging;
import java.util.*;
import twitter4j.conf.ConfigurationBuilder;

public class SearchTweet {
    // Consumer と AccessToken をここで指定

    public static final String CONSUMERKEY = "4cupQLnkbitJyP3nPmhHEgwwr";
    public static final String CONSUMERSECRET = "Li21BMITEvw2wIu3nqDPAQbkUGB92Tv78CF3ljxJSyYKSDPp8W";
    public static final String TOKEN = "2185854571-mAV37V48s4WtQ4TV4LRMPQC2KiKVOtBAa2Fznda";
    public static final String TOKENSECRET = "fviyYK8iLcBeEWRVqtD2XyHx0CPxfE9loDGwNDTiC1rvN";

    public void main(PrintWriter out) {
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true);
            cb.setOAuthConsumerKey(CONSUMERKEY);
            cb.setOAuthConsumerSecret(CONSUMERSECRET);
            cb.setOAuthAccessToken(TOKEN);
            cb.setOAuthAccessTokenSecret(TOKENSECRET);
            Twitter tw = new TwitterFactory(cb.build()).getInstance();
            User user = tw.verifyCredentials();
            List<Status> statuses = tw.getHomeTimeline();

            for (Status s : statuses) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
