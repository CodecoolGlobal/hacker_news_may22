package com.codecool.hackernews.news;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface NewsApiService {

    List<News> getNews(NewsTypes newsTypes, int pageNumber) throws MalformedURLException, IOException;

}
