package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ComposeActivity extends AppCompatActivity {

    public static final String TAG = "ComposeActivity";
    public static final int TWEET_CHAR_LIMIT = 140;

    TwitterClient client;

    EditText compose_text;
    Button compose_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        client = TwitterApp.getRestClient(this);

        compose_text = findViewById(R.id.COMPOSE_TEXT);
        compose_submit = findViewById(R.id.COMPOSE_SUBMIT);

        compose_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = compose_text.getText().toString();

                if(text.isEmpty()) {
                    Toast.makeText(ComposeActivity.this, "Sorry, your Tweet cannot be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(text.length() > TWEET_CHAR_LIMIT) {
                    Toast.makeText(ComposeActivity.this, "Sorry, your Tweet has too many characters.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    client.publishTweet(new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Headers headers, JSON json) {
                            Toast.makeText(ComposeActivity.this, "Tweet published successfully.", Toast.LENGTH_SHORT).show();
                            try {
                                Tweet t = Tweet.fromJson(json.jsonObject);
                                Log.i(TAG, "Tweet info: " + t.body);

                                Intent i = new Intent();
                                i.putExtra("tweet_published", Parcels.wrap(t));
                                setResult(RESULT_OK, i);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                            Log.e(TAG, "Failed to publish Tweet.", throwable);
                        }
                    }, text);
                }
            }
        });
    }
}