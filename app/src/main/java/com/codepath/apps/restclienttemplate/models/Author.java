package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Author {

    public int id;
    public String name;
    public String screen_name;
    public String profile_picture_url;

    public static Author fromJson(JSONObject j) throws JSONException {
        Author a = new Author();

        a.name = j.getString("name");
        a.screen_name = j.getString("screen_name");
        a.profile_picture_url = j.getString("profile_image_url_https");

        return a;
    }
}
