package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
    public String body;
    public String createdAt;
    public Author author;

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
}
