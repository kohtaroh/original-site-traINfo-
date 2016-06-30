/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jums;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import twitter4j.Twitter;
import java.util.Calendar;
import java.util.Date;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author kotaroh
 */
public class AllAPI {

    private static final String CONSUMERKEY = "4cupQLnkbitJyP3nPmhHEgwwr";
    private static final String CONSUMERSECRET = "Li21BMITEvw2wIu3nqDPAQbkUGB92Tv78CF3ljxJSyYKSDPp8W";
    private static final String TOKEN = "2185854571-mAV37V48s4WtQ4TV4LRMPQC2KiKVOtBAa2Fznda";
    private static final String TOKENSECRET = "fviyYK8iLcBeEWRVqtD2XyHx0CPxfE9loDGwNDTiC1rvN";
    private static String APP_ID = "dj0zaiZpPVJKMkVNTTdOckZTQSZzPWNvbnN1bWVyc2VjcmV0Jng9ODg-";
    private static String BASE_URI_GEO = "http://search.olp.yahooapis.jp/OpenLocalPlatform/V1/localSearch";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    Twitter tw = new TwitterFactory(this.TwitterConnect()).getInstance();

    public static AllAPI getInstance() {
        return new AllAPI();
    }

    public String twitter(String word) {
        int i = 0;
        String answer = word + "線:平常運転中";
        try {
            i = i + TweetCount(word + "&事故 -RT -https://t.co/C64lp22qsm");
            i = i + TweetCount(word + "&遅延 -RT -https://t.co/C64lp22qsm");
            i = i + TweetCount(word + "&停止 -RT -https://t.co/C64lp22qsm");
            if (i > 50) {
                answer = word + "線が遅れているかも";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return answer;
    }

    public Date SetDate() {
        this.c.add(Calendar.HOUR_OF_DAY, -1);
        Date date = this.c.getTime();
        return date;
    }

    public String SetSDF() {
        this.c.add(Calendar.HOUR_OF_DAY, -1);
        return this.sdf.format(c.getTime());
    }

    public static Configuration TwitterConnect() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey(CONSUMERKEY);
        cb.setOAuthConsumerSecret(CONSUMERSECRET);
        cb.setOAuthAccessToken(TOKEN);
        cb.setOAuthAccessTokenSecret(TOKENSECRET);
        return cb.build();
    }

    public int TweetCount(String word) {
        int i = 0;
        try {
            Query query = new Query(word);
            query.setCount(100);
            Date date = this.SetDate();
            query.setSince(this.SetSDF());
            QueryResult result = this.tw.search(query);
            for (Status status : result.getTweets()) {
                if (date.compareTo(status.getCreatedAt()) < 0) {
                    i++;
                }
            }
        } catch (TwitterException te) {
            System.out.println(te.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    public URL YahooSearchGEO(String word) {
        String result = "";
        JsonNode head = null;
        URL url = null;
        try {
            String urlString = URLEncoder.encode(word, "utf-8");
            url = new URL(BASE_URI_GEO + "?appid=" + APP_ID + "&query=" + urlString + "&output=json&sort=hybrid&results=100&gc=0205001");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String tmp = "";
            while ((tmp = in.readLine()) != null) {
                result += tmp;
            }

            JsonFactory jfactory = new JsonFactory();
            JsonParser parser = jfactory.createJsonParser(result);
            ObjectMapper mapper = new ObjectMapper();
            head = mapper.readTree(parser);
            search:
            for (int i = 0; i < 20; i++) {

            }
            head.get("Feature");
            in.close();
            con.disconnect();

            head.get("Feature").get(0).get("Name").textValue();
            head.get("Feature").get(0).get("Geometry").get("Coordinates").textValue();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;

    }

    public String YahooSearchRoute(String word) {
        String result = "";
        JsonNode head = null;
        URL url = null;
        try {
            String urlString = URLEncoder.encode(word, "utf-8");
            url = new URL(BASE_URI_GEO + "?appid=" + APP_ID + "&route=" + urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String tmp = "";
            while ((tmp = in.readLine()) != null) {
                result += tmp;
            }
            JsonFactory jfactory = new JsonFactory();
            JsonParser parser = jfactory.createJsonParser(result);
            ObjectMapper mapper = new ObjectMapper();
            head = mapper.readTree(parser);
            search:
            for (int i = 0; i < 20; i++) {

            }
            head.get("Feature");
            in.close();
            con.disconnect();

            head.get("Feature").get(0).get("Name").textValue();
            head.get("Feature").get(0).get("Geometry").get("Coordinates").textValue();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return head.get("Feature").get(0).get("Geometry").get("Coordinates").textValue();

    }

}
