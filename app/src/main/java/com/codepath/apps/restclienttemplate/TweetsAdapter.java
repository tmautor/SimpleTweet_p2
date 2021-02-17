package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context c;
    List<Tweet> tweets;

    public TweetsAdapter(Context c, List<Tweet> t) {
        this.c = c;
        this.tweets = t;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet t = tweets.get(position);
        holder.bind(t);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView author_profile_image;
        TextView author_name;
        TextView tweet_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            author_profile_image = itemView.findViewById(R.id.TWEET_IV_AUTHOR_PICTURE);
            author_name = itemView.findViewById(R.id.TWEET_TV_AUTHOR_NAME);
            tweet_content = itemView.findViewById(R.id.TWEET_TV_CONTENT);
        }

        public void bind(Tweet t) {
            author_name.setText(t.author.screen_name);
            tweet_content.setText(t.body);
            Glide.with(c).load(t.author.profile_picture_url).into(author_profile_image);
        }
    }
}
