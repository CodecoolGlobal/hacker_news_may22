package com.codecool.hackernews.news;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class NewsApiServiceImpl implements NewsApiService {

    private final String baseUrl = "https://api.hnpwa.com/v0/";

    @Override
    public List<News> getNews(NewsTypes newsTypes, int pageNumber) throws MalformedURLException, IOException {
        URL url = new URL(String.format("%s%s/%s.json", baseUrl, newsTypes.getNewsType(), pageNumber));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        byte[] bytes = connection.getInputStream().readAllBytes();
        connection.disconnect();
        Gson gson = new Gson();
        News[] news = gson.fromJson(new String(bytes), News[].class);
        return Arrays.asList(news);
    }
}
