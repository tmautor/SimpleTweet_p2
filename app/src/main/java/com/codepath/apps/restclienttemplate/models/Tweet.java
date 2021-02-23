package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {
    public String body;
    public String createdAt;
    public Author author;

    public Tweet() {}

    public static Tweet fromJson(JSONObject j) throws JSONException {
        Tweet t = new Tweet();

        t.body = j.getString("text");
        t.createdAt = j.getString("created_at");
        t.author = Author.fromJson(j.getJSONObject("user"));

        return t;
    }

    public static List<Tweet> fromJsonArray(JSONArray j) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();

        for(int i = 0; i < j.length(); ++i) {
            tweets.add(Tweet.fromJson(j.getJSONObject(i)));
        }

        return tweets;
    }

    public String getTimePostReference() {
        return TimeFormatter.getTimeDifference(this.createdAt);
    }
}
