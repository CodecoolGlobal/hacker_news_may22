package com.codecool.hackernews.news;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class News {

    private String title;
    @SerializedName("user")
    private String author;
    @SerializedName("time_ago")
    private String timeAgo;
    private String url;
}
